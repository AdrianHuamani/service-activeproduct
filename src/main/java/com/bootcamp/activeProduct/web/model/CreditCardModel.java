package com.bootcamp.activeProduct.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardModel {
    @Id
    private String id;

    private ClientModel client;

    @NotBlank(message="Card number cannot be null or empty")
    private String cardNumber;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate issueDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate expiryDate;

    //@NotBlank(message="Pay date cannot be null or empty")
    private Integer payDate;

    //@NotBlank(message="Billing date cannot be null or empty")
    private Integer billingDate;

    //@NotBlank(message="Credit limit cannot be null or empty")
    private Double creditLimit;

    @NotBlank(message="Currency cannot be null or empty")
    private String currency;

    //@NotBlank(message="Interest rate cannot be null or empty")
    private Double interestRate;
}
