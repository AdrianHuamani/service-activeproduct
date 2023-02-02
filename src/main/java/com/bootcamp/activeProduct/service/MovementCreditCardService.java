package com.bootcamp.activeProduct.service;

import com.bootcamp.activeProduct.common.FunctionalException;
import com.bootcamp.activeProduct.domain.MovementCreditCard;
import com.bootcamp.activeProduct.repository.CreditCardRepository;
import com.bootcamp.activeProduct.repository.MovementCreditCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

import static com.bootcamp.activeProduct.common.ErrorMessage.CREDITCARD_NOT_FOUND;
import static com.bootcamp.activeProduct.common.ErrorMessage.LIMIT_CREDITCARD;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MovementCreditCardService {
    @Autowired
    MovementCreditCardRepository movementCreditCardRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    public Flux<MovementCreditCard> findAll(){
        log.debug("findAll executed");
        return movementCreditCardRepository.findAll();
    }

    public Mono<MovementCreditCard> create(MovementCreditCard movementCreditCard){
        log.debug("create executed {}", movementCreditCard);
        return creditCardRepository.findById(movementCreditCard.getCreditCardId())
                .flatMap(x-> movementCreditCardRepository.findByCreditCardId(movementCreditCard.getCreditCardId())
                            .collect(Collectors.summingDouble(p -> p.getAmount()*p.getExchangeRate()))
                            .flatMap(a-> {
                                if(a + movementCreditCard.getAmount() <= x.getCreditLimit())
                                    return movementCreditCardRepository.save(movementCreditCard);
                                else
                                    return Mono.error(new FunctionalException(LIMIT_CREDITCARD.getValue()));
                            }))
                .switchIfEmpty(Mono.error(new FunctionalException(CREDITCARD_NOT_FOUND.getValue())));
    }
}
