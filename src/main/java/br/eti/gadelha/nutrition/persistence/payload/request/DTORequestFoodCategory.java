package br.eti.gadelha.nutrition.persistence.payload.request;

import br.eti.gadelha.nutrition.exception.annotation.UniqueIbgeCode;
//import br.eti.gadelha.nutrition.exception.annotation.UniqueNameFoodCategory;
import br.eti.gadelha.nutrition.persistence.model.FoodCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter /*@UniqueNameFoodCategory*/ @UniqueIbgeCode
public class DTORequestFoodCategory {

    private UUID id;
    @NotNull(message = "{food.category.not.null}") @NotBlank(message = "{food.category.not.blank}")
    private String name;

    public FoodCategory toObject(){
        return new FoodCategory(name);
    }
}