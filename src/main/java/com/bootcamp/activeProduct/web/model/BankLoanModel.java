package com.bootcamp.activeProduct.web.model;

import com.bootcamp.activeProduct.domain.Client;
import com.bootcamp.activeProduct.domain.Entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankLoanModel {
    @Id
    private String id;

    private ClientModel client;

    private Double loanAmount;

    @NotBlank(message="Currency cannot be null or empty")
    private String currency;

    @NotNull
    private Double interestRate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate loanDate;

    private Integer paymentinstallments;

    private Entity entity;
}
