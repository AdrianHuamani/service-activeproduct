package com.bootcamp.activeProduct.web.mapper;

import com.bootcamp.activeProduct.domain.CreditCard;
import com.bootcamp.activeProduct.domain.MovementCreditCard;
import com.bootcamp.activeProduct.web.model.MovementCreditCardModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MovementCreditCardMapper {
    MovementCreditCard modelToEntity (MovementCreditCardModel model);

    MovementCreditCardModel entityToModel (MovementCreditCard event);

    @Mapping(target = "id", ignore = true)
    void update(@MappingTarget CreditCard entity, CreditCard updateEntity);
}
