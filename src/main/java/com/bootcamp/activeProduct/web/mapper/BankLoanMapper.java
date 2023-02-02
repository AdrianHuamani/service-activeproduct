package com.bootcamp.activeProduct.web.mapper;

import com.bootcamp.activeProduct.domain.BankLoan;
import com.bootcamp.activeProduct.web.model.BankLoanModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BankLoanMapper {
    @Mapping(target="client.id", source="client.id")
    @Mapping(target="entity.name", source="entity.name")
    BankLoan modelToEntity (BankLoanModel model);

    @Mapping(target="client.id", source="client.id")
    @Mapping(target="entity.name", source="entity.name")
    BankLoanModel entityToModel (BankLoan event);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget BankLoan entity, BankLoan updateEntity);
}
