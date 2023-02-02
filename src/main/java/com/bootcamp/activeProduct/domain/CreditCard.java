package com.bootcamp.activeProduct.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "creditCard")
public class CreditCard {

    @Id
    private String id;

    @NotNull
    private Client client;

    @NotNull
    @Indexed(unique = true)
    private String cardNumber;

    @NotNull
    private LocalDate issueDate;

    @NotNull
    private LocalDate expiryDate;

    @NotNull
    private Boolean status;

    @NotNull
    private Integer payDate;

    @NotNull
    private Integer billingDate;

    @NotNull
    private Double creditLimit;

    @NotNull
    private String currency;

    @NotNull
    private Double interestRate;

    @NotNull
    private LocalDate creationDate;

    @NotNull
    private String creationUser;

    @NotNull
    private LocalDate modifiedDate;

    @NotNull
    private String modifiedUser;
}
