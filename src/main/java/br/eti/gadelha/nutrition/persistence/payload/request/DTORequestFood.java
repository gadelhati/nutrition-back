package br.eti.gadelha.nutrition.persistence.payload.request;

import br.eti.gadelha.nutrition.persistence.model.Food;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DTORequestFood {

    private UUID id;
    @NotNull(message = "{food.name.not.null}") @NotBlank(message = "{food.name.not.blank}")
    private String name;

    public Food toObject(){
        return new Food(name);
    }
}