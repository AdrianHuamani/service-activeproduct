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
@Document(value = "bankLoan")
public class BankLoan {
    @Id
    private String id;

    @NotNull
    private Client client;

    @NotNull
    private Double loanAmount;

    @NotNull
    private String currency;

    @NotNull
    private Double interestRate;

    @NotNull
    private LocalDate loanDate;

    @NotNull
    private Integer paymentinstallments;

    @NotNull
    private String paymentStatus; /*Pagado, Pendiente*/

    @NotNull
    private Entity entity;

    @NotNull
    private LocalDate creationDate;

    @NotNull
    private String creationUser;

    @NotNull
    private LocalDate modifiedDate;

    @NotNull
    private String modifiedUser;

}
