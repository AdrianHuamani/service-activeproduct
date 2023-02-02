package com.bootcamp.activeProduct.web.model;

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
public class MovementCreditCardModel {
    @Id
    private String id;

    @NotBlank(message="Card card cannot be null or empty")
    private String creditCardId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate movementDate;

    private Double amount;

    private Integer paymentInstallments;

    @NotBlank(message="Currency cannot be null or empty")
    private String currency;

    private Double exchangeRate;

    @NotBlank(message="Pay method number cannot be null or empty")
    private String payMethod;
}
