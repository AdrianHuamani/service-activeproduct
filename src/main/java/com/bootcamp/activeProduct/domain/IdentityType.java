package com.bootcamp.activeProduct.domain;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IdentityType {
    private String id;

    private String description;

    private Boolean status;

    private LocalDate creationDate;

    private String creationUser;

    private LocalDate modifiedDate;

    private String modifiedUser;
}
