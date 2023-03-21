package br.eti.gadelha.nutrition.persistence;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum EnumMenu {
    AUTH("Auth"),
    FOOD("Food"),
    FOOD_CATEGORY("FoodCategory"),
    PRIVILEGE("Privilege"),
    ROLE("Role"),
    USER_ENTITY("UserEntity");

    private final String name;
}