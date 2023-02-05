package br.eti.gadelha.nutrition.persistence.payload.response;

import br.eti.gadelha.nutrition.persistence.model.Food;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseFood {

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

    public static DTOResponseFood toDTO(Food value) {
        return new DTOResponseFood(value.getId(), value.getIBGECODE(), value.getName(), value.getPreparation(), value.getEnergy(), value.getProtein(), value.getTotalLipids(), value.getCarbohydrate(), value.getFiber(), value.getCholesterol(), value.getSaturated(), value.getMonounsaturated(), value.getPolyunsaturated(), value.getLinoleic(), value.getLinolenic(), value.getTrans(), value.getTotalSugar(), value.getAddedSugar(), value.getCalcium(), value.getMagnesium(), value.getManganese(), value.getPhosphorus(), value.getIron(), value.getSodium(), value.getAddedSodium(), value.getPotassium(), value.getCopper(), value.getZinc(), value.getSelenium(), value.getRetinol(), value.getVitaminaA(), value.getTiamina(), value.getRiboflavina(), value.getNiacina(), value.getNiacinaNE(), value.getPiridoxina(), value.getCobalamina(), value.getFolato(), value.getVitaminaD(), value.getVitaminaE(), value.getVitaminaC());
    }
}