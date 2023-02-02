package com.bootcamp.activeProduct.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientTypeModel {
    @Id
    private String id;

    @NotBlank(message="description cannot be null or empty")
    private String description;
}
