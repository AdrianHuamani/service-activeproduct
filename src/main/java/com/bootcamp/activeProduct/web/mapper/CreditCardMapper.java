package com.bootcamp.activeProduct.web.mapper;

import com.bootcamp.activeProduct.domain.CreditCard;
import com.bootcamp.activeProduct.web.model.CreditCardModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {
    @Mapping(target="client.id", source="client.id")
    CreditCard modelToEntity (CreditCardModel model);

    @Mapping(target="client.id", source="client.id")
    CreditCardModel entityToModel (CreditCard event);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget CreditCard entity, CreditCard updateEntity);
}
