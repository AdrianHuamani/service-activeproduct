package com.bootcamp.activeProduct.repository;

import com.bootcamp.activeProduct.domain.MovementCreditCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MovementCreditCardRepository extends ReactiveMongoRepository<MovementCreditCard, String> {
    public Flux<MovementCreditCard> findByCreditCardId(String creditCardId);
}
