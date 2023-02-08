package br.eti.gadelha.nutrition.persistence;

import br.eti.gadelha.nutrition.persistence.model.*;
import br.eti.gadelha.nutrition.persistence.payload.request.*;
import br.eti.gadelha.nutrition.persistence.payload.response.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface MapStruct {

    MapStruct MAPPER = Mappers.getMapper(MapStruct.class);
    DTOResponseFood toDTOFood(Food food);
    DTOResponseFoodCategory toDTOFoodCategory(FoodCategory foodCategory);
    DTOResponseUserEntity toDTOUserEntity(UserEntity userEntity);
    DTOResponseAuth authToDTOUserEntity(UserEntity userEntity);
    DTOResponseRole toDTORole(Role role);
    DTOResponsePrivilege toDTOPrivilege(Privilege privilege);

    Food toFood(DTORequestFood dtoRequestFood);
    FoodCategory toFoodCategory(DTORequestFoodCategory dtoRequestFoodCategory);
    UserEntity toUserEntity(DTORequestUserEntity dtoRequestUserEntity);
    UserEntity userEntityToDTOAuth(DTORequestAuth dtoRequestAuth);
    Role toRole(DTORequestRole dtoRequestRole);
    Privilege toPrivilege(DTORequestPrivilege dtoRequestPrivilege);
}