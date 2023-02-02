package com.bootcamp.activeProduct.web;

import com.bootcamp.activeProduct.service.CreditCardService;
import com.bootcamp.activeProduct.service.MovementCreditCardService;
import com.bootcamp.activeProduct.web.mapper.CreditCardMapper;
import com.bootcamp.activeProduct.web.mapper.MovementCreditCardMapper;
import com.bootcamp.activeProduct.web.model.CreditCardModel;
import com.bootcamp.activeProduct.web.model.MovementCreditCardModel;
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
@RequestMapping("/v1/movementCC")
public class MovementCreditCardController {
    @Value("${spring.application.name}")
    String name;

    @Value("${server.port}")
    String port;

    @Autowired
    private MovementCreditCardService movementCreditCardService;


    @Autowired
    private MovementCreditCardMapper movementCreditCardMapper;


    @GetMapping()
    public Mono<ResponseEntity<Flux<MovementCreditCardModel>>> getAll(){
        log.info("getAll executed");
        return Mono.just(ResponseEntity.ok()
                .body(movementCreditCardService.findAll()
                        .map(m -> movementCreditCardMapper.entityToModel(m))));
    }

    @PostMapping
    public Mono<ResponseEntity<MovementCreditCardModel>> create(@Valid @RequestBody MovementCreditCardModel request){
        log.info("create executed {}", request);
        return movementCreditCardService.create(movementCreditCardMapper.modelToEntity(request))
                .map(m -> movementCreditCardMapper.entityToModel(m))
                .flatMap(c -> Mono.just(ResponseEntity.created(URI.create(String.format("http://%s:%s/%s/%s", name, port, "Movement", c.getId())))
                        .body(c)));
    }
}
