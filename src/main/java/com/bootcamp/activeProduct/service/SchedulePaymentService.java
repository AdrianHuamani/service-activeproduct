package com.bootcamp.activeProduct.service;

import com.bootcamp.activeProduct.domain.BankLoan;
import com.bootcamp.activeProduct.domain.SchedulePayment;
import com.bootcamp.activeProduct.repository.SchedulePaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class SchedulePaymentService {
    @Autowired
    private SchedulePaymentRepository schedulePaymentRepository;

    public Mono<BankLoan> create(BankLoan bankLoan){
        log.debug("create schedule {}", bankLoan);

        Double installment = bankLoan.getLoanAmount()/((1-Math.pow((1+bankLoan.getInterestRate()/12), bankLoan.getPaymentinstallments()))/(bankLoan.getInterestRate()/12));

        for (int i = 1; i<=bankLoan.getPaymentinstallments();i++){
            SchedulePayment record = new SchedulePayment();
            record.setBankLoanId(bankLoan.getId());
            record.setAmountInstallment(installment);
            record.setInstallmentNumber(bankLoan.getPaymentinstallments());
            record.setInstallmentStatus(true);
            record.setCreationDate(LocalDate.now());
            record.setCreationUser(System.getProperty("user.name"));
            record.setInstallmentNumber(i);
            record.setExpiryDate(bankLoan.getLoanDate().plusMonths(i));

            schedulePaymentRepository.save(record);
        }
        return Mono.just(bankLoan);
    }
}
