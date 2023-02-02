package com.bootcamp.activeProduct.web;

import com.bootcamp.activeProduct.common.FunctionalException;
import com.bootcamp.activeProduct.domain.BankLoan;
import com.bootcamp.activeProduct.service.BankLoanService;
import com.bootcamp.activeProduct.web.mapper.BankLoanMapper;
import com.bootcamp.activeProduct.web.model.BankLoanModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/bankloan")
public class BankLoanController {
    @Value("${spring.application.name}")
    String name;

    @Value("${server.port}")
    String port;

    @Autowired
    private BankLoanService bankLoanService;

    @Autowired
    private BankLoanMapper bankLoanMapper;


    @GetMapping()
    public Mono<ResponseEntity<Flux<BankLoanModel>>> getAll(){
        log.info("getAll executed");
        return Mono.just(ResponseEntity.ok()
                .body(bankLoanService.findAll()
                        .map(cc -> bankLoanMapper.entityToModel(cc))));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<BankLoanModel>> getById(@PathVariable String id){
        log.info("getById executed {}", id);
        Mono<BankLoan> response = bankLoanService.findById(id);
        return response
                .map(cc -> bankLoanMapper.entityToModel(cc))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<BankLoanModel>> create(@Valid @RequestBody BankLoanModel request){
            log.info("create executed {}", request);
            return bankLoanService.create(bankLoanMapper.modelToEntity(request))
                    .map(cc -> bankLoanMapper.entityToModel((BankLoan) cc))
                    .flatMap(c -> Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name, port, "Client", c.getId())))
                            .body(c)));

    }
}
