package com.bootcamp.activeProduct.service;

import com.bootcamp.activeProduct.common.ErrorMessage;
import com.bootcamp.activeProduct.common.FunctionalException;
import com.bootcamp.activeProduct.domain.BankLoan;
import com.bootcamp.activeProduct.domain.Client;
import com.bootcamp.activeProduct.repository.BankLoanRepository;
import com.bootcamp.activeProduct.repository.SchedulePaymentRepository;
import com.bootcamp.activeProduct.web.mapper.CreditCardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Supplier;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BankLoanService {
    @Autowired
    private BankLoanRepository bankLoanRepository;

    @Autowired
    private SchedulePaymentService schedulePaymentService;

    @Autowired
    private CreditCardMapper creditCardMapper;

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081/v1/client").defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

    public Mono<Client> getClientByIdentityNumber(String identityNumber) {
        return this.webClient.get().uri("/findByIdentityNumber/{identityNumber}", identityNumber)
                .retrieve().bodyToMono(Client.class);
    }

    public Flux<BankLoan> findAll(){
        log.debug("findAll executed");
        return bankLoanRepository.findAll();
    }

    public Mono<BankLoan> findById(String id){
        log.debug("findById executed {}", id);
        return bankLoanRepository.findById(id);
    }

    public Mono<Object> create(BankLoan bankLoan){
        log.debug("create executed {}", bankLoan);
        return getClientByIdentityNumber(bankLoan.getClient().getIdentityNumber())
                .flatMap(x-> {
                    //bankLoan.setClient(x);
                    //bankLoan.setPaymentStatus("Pendiente");
                    //if(x.getClientType().getDescription().toUpperCase().equals("EMPRESARIAL")){
                       // return bankLoanRepository.save(bankLoan)
                     //           .doOnNext(aa -> schedulePaymentService.create(aa));
                   // }
                   // else{
                        return bankLoanRepository.findTop1ByClientAndPaymentStatus(x, "Pendiente")
                                .flatMap(y -> Mono.error(new FunctionalException(ErrorMessage.LOAN_RESTRICTION.getValue())))
                                .switchIfEmpty(Mono.defer(()->bankLoanRepository.save(bankLoan)
                                        .doOnNext(aa -> schedulePaymentService.create(aa))));
                    //}
                })
                .switchIfEmpty(Mono.error(new FunctionalException(ErrorMessage.CLIENT_NOT_FOUND.getValue())));
    }
}
