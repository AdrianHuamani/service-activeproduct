package com.bootcamp.activeProduct.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "schedulePayment")
public class SchedulePayment {
    @Id
    private String id;

    @NotNull
    private String bankLoanId;

    @NotNull
    private Integer installmentNumber;

    @NotNull
    private Double amountInstallment;

    @NotNull
    private LocalDate expiryDate;

    @NotNull
    private Boolean installmentStatus;

    private LocalDate payDate;

    @NotNull
    private LocalDate creationDate;

    @NotNull
    private String creationUser;

    private LocalDate modifiedDate;

    private String modifiedUser;
}
