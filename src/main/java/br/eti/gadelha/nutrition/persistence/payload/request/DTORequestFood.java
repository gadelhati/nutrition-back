package br.eti.gadelha.nutrition.persistence.payload.request;

import br.eti.gadelha.nutrition.persistence.model.Food;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DTORequestFood {

    private UUID id;
    private String IBGECODE;
    @NotNull(message = "{food.name.not.null}") @NotBlank(message = "{food.name.not.blank}")
    private String name;
    private String preparation;
    private float energy;
    private float protein;
    private float totalLipids;
    private float carbohydrate;
    private float fiber;

    public Food toObject(){
        return new Food(IBGECODE, name, preparation, energy, protein, totalLipids, carbohydrate, fiber);
    }
}