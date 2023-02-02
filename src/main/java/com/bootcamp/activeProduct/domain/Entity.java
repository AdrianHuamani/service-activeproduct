package com.bootcamp.activeProduct.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Entity {
    public String name;
    public String accountNumber;
}
