package com.bootcamp.activeProduct.repository;

import com.bootcamp.activeProduct.domain.BankLoan;
import com.bootcamp.activeProduct.domain.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface BankLoanRepository extends ReactiveMongoRepository<BankLoan, String> {
    public Mono<Client> findTop1ByClientAndPaymentStatus(Client client, String paymentStatus);
}
