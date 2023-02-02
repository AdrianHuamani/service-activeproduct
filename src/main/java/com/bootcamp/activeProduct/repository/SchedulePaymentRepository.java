package com.bootcamp.activeProduct.repository;

import com.bootcamp.activeProduct.domain.SchedulePayment;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchedulePaymentRepository extends ReactiveMongoRepository<SchedulePayment, String > {
}
