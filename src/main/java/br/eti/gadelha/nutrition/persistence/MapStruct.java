package br.eti.gadelha.nutrition.persistence;

import br.eti.gadelha.nutrition.persistence.model.*;
import br.eti.gadelha.nutrition.persistence.payload.request.*;
import br.eti.gadelha.nutrition.persistence.payload.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface MapStruct {

    MapStruct MAPPER = Mappers.getMapper(MapStruct.class);
    DTOResponseFood toDTO(Food food);
    DTOResponseFoodCategory toDTO(FoodCategory foodCategory);
    DTOResponseUserEntity toDTO(UserEntity userEntity);
    DTOResponseRole toDTO(Role role);
    DTOResponsePrivilege toDTO(Privilege privilege);
    DTOResponseCompositeUnit toDTO(CompositeUnit compositeUnit);

    Food toObject(DTORequestFood dtoRequestFood);
    FoodCategory toObject(DTORequestFoodCategory dtoRequestFoodCategory);
    UserEntity toObject(DTORequestUserEntity dtoRequestUserEntity);
    Role toObject(DTORequestRole dtoRequestRole);
    Privilege toObject(DTORequestPrivilege dtoRequestPrivilege);
    CompositeUnit toObject(DTORequestCompositeUnit dtoRequestCompositeUnit);
}