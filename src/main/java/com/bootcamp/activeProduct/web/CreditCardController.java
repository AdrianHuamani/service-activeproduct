package com.bootcamp.activeProduct.web;

import com.bootcamp.activeProduct.domain.CreditCard;
import com.bootcamp.activeProduct.service.CreditCardService;
import com.bootcamp.activeProduct.web.model.CreditCardModel;
import com.bootcamp.activeProduct.web.mapper.CreditCardMapper;
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
@RequestMapping("/v1/creditcard")
public class CreditCardController {
    @Value("${spring.application.name}")
    String name;

    @Value("${server.port}")
    String port;

    @Autowired
    private CreditCardService creditCardService;


    @Autowired
    private CreditCardMapper creditCardMapper;


    @GetMapping()
    public Mono<ResponseEntity<Flux<CreditCardModel>>> getAll(){
        log.info("getAll executed");
        return Mono.just(ResponseEntity.ok()
                .body(creditCardService.findAll()
                        .map(cc -> creditCardMapper.entityToModel(cc))));
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<CreditCardModel>> getById(@PathVariable String id){
        log.info("getById executed {}", id);
        Mono<CreditCard> response = creditCardService.findById(id);
        return response
                .map(cc -> creditCardMapper.entityToModel(cc))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<CreditCardModel>> create(@Valid @RequestBody CreditCardModel request){
        log.info("create executed {}", request);
        return creditCardService.create(creditCardMapper.modelToEntity(request))
                .map(cc -> creditCardMapper.entityToModel(cc))
                .flatMap(c -> Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name, port, "Client", c.getId())))
                        .body(c)));
        //.defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<CreditCardModel>> updateById(@PathVariable String id, @Valid @RequestBody CreditCardModel request){
        log.info("updateById executed {}:{}", id, request);
        return creditCardService.update(id, creditCardMapper.modelToEntity(request))
                .map(cc -> creditCardMapper.entityToModel(cc))
                .flatMap(c -> Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name, port, "Client", c.getId())))
                        .body(c)))
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteById(@PathVariable String id){
        log.info("deleteById executed {}", id);
        return creditCardService.delete(id)
                .map( r -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/findByClient/{identityNumber}")
    public Mono<ResponseEntity<Mono<CreditCardModel>>> findByClient(@PathVariable String identityNumber){
        return Mono.just(ResponseEntity.ok()
                .body(creditCardService.findByClient(identityNumber)
                        .map(cc -> creditCardMapper.entityToModel(cc))));
    }
}
