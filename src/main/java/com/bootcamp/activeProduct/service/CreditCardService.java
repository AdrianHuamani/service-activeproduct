package com.bootcamp.activeProduct.service;

import com.bootcamp.activeProduct.common.FunctionalException;
import com.bootcamp.activeProduct.domain.Client;
import com.bootcamp.activeProduct.domain.CreditCard;
import com.bootcamp.activeProduct.repository.CreditCardRepository;
import com.bootcamp.activeProduct.web.mapper.CreditCardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static com.bootcamp.activeProduct.common.ErrorMessage.CLIENT_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CreditCardService {
    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private CreditCardMapper creditCardMapper;

    private final WebClient webClient = WebClient.builder().baseUrl("http://localhost:8081/v1/client").defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

    public Mono<Client> getClientByIdentityNumber(String identityNumber) {
        return this.webClient.get().uri("/findByIdentityNumber/{identityNumber}", identityNumber)
                .retrieve().bodyToMono(Client.class);
    }

    public Flux<CreditCard> findAll(){
        log.debug("findAll executed");
        return creditCardRepository.findAll();
    }


    public Mono<CreditCard> findById(String id){
        log.debug("findById executed {}", id);
        return creditCardRepository.findById(id);
    }


    public Mono<CreditCard> create(CreditCard creditCard){
        log.debug("create executed {}", creditCard);
        return getClientByIdentityNumber(creditCard.getClient().getIdentityNumber())
                .flatMap(x-> {
                    creditCard.setClient(x);
                    return creditCardRepository.save(creditCard);
                })
                .switchIfEmpty(Mono.error(new FunctionalException(CLIENT_NOT_FOUND.getValue())));
    }


    public Mono<CreditCard> update(String id,  CreditCard CreditCard){
        log.debug("update executed {}:{}", id, CreditCard);
        return creditCardRepository.findById(id)
                .flatMap(dbCreditCard -> {
                    creditCardMapper.update(dbCreditCard, CreditCard);
                    return creditCardRepository.save(dbCreditCard);
                });
    }


    public Mono<CreditCard> delete(String creditCardId){
        log.debug("delete executed {}", creditCardId);
        return creditCardRepository.findById(creditCardId)
                .flatMap(existingCreditCard -> creditCardRepository.delete(existingCreditCard)
                        .then(Mono.just(existingCreditCard)));
    }

    public Mono<CreditCard> findByClient(String identityNumber){
        return creditCardRepository.findTop1ByClientIdentityNumber(identityNumber);
    }

}
