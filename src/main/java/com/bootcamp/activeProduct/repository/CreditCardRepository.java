package com.bootcamp.activeProduct.repository;

import com.bootcamp.activeProduct.domain.Client;
import com.bootcamp.activeProduct.domain.CreditCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CreditCardRepository extends ReactiveMongoRepository<CreditCard, String> {
    public Mono<CreditCard> findTop1ByClientIdentityNumber(String identityNumber);
}
