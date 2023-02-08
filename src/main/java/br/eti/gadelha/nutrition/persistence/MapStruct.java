package br.eti.gadelha.nutrition.persistence;

import br.eti.gadelha.nutrition.persistence.model.*;
import br.eti.gadelha.nutrition.persistence.payload.request.*;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseFood;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface MapStruct {

    MapStruct MAPPER = Mappers.getMapper(MapStruct.class);
    DTOResponseFood toFood(Food food);
    DTORequestFoodCategory toFoodCategory(FoodCategory foodCategory);
    DTORequestUserEntity toUserEntity(UserEntity userEntity);
    DTORequestAuth mapAuthToUserEntity(UserEntity userEntity);
    DTORequestRole toRole(Role role);
    DTORequestPrivilege toPrivilege(Privilege privilege);

    Food toDTOFood(DTORequestFood dtoRequestFood);
    FoodCategory toDTOFoodCategory(DTORequestFoodCategory dtoRequestFoodCategory);
    UserEntity toDTOUserEntity(DTORequestUserEntity dtoRequestUserEntity);
    UserEntity mapAuthToDTOAuth(DTORequestAuth dtoRequestAuth);
    Role toDTORole(DTORequestRole dtoRequestRole);
    Privilege toDTOPrivilege(DTORequestPrivilege dtoRequestPrivilege);
}