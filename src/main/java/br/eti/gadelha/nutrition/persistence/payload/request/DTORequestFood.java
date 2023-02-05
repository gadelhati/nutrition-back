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
    private float cholesterol;
    private float saturated;
    private float monounsaturated;
    private float polyunsaturated;
    private float linoleic;
    private float linolenic;
    private float trans;
    private float totalSugar;
    private float addedSugar;
    private float calcium;
    private float magnesium;
    private float manganese;
    private float phosphorus;
    private float iron;
    private float sodium;
    private float addedSodium;
    private float potassium;
    private float copper;
    private float zinc;
    private float selenium;
    private float retinol;
    private float vitaminaA;
    private float tiamina;
    private float riboflavina;
    private float niacina;
    private float niacinaNE;
    private float piridoxina;
    private float cobalamina;
    private float folato;
    private float vitaminaD;
    private float vitaminaE;
    private float vitaminaC;

    public Food toObject(){
        return new Food(IBGECODE, name, preparation, energy, protein, totalLipids, carbohydrate, fiber, cholesterol, saturated, monounsaturated, polyunsaturated, linoleic, linolenic, trans, totalSugar, addedSugar, calcium, magnesium, manganese, phosphorus, iron, sodium, addedSodium, potassium, copper, zinc, selenium, retinol, vitaminaA, tiamina, riboflavina, niacina, niacinaNE, piridoxina, cobalamina, folato, vitaminaD, vitaminaE, vitaminaC);
    }
}