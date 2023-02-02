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
@Document(value = "movementCreditCard")
public class MovementCreditCard {
    @Id
    private String id;

    @NotNull
    private String creditCardId;

    @NotNull
    private LocalDate movementDate;

    @NotNull
    private Double amount;

    @NotNull
    private Integer paymentInstallments;

    @NotNull
    private String currency;

    @NotNull
    private Double exchangeRate;

    @NotNull
    private String payMethod;

}
