package br.eti.gadelha.nutrition.persistence.payload.response;

import br.eti.gadelha.nutrition.persistence.model.FoodCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseFoodCategory {

    private UUID id;
    private String name;

    public static DTOResponseFoodCategory toDTO(FoodCategory value) {
        return new DTOResponseFoodCategory(value.getId(), value.getName());
    }
}