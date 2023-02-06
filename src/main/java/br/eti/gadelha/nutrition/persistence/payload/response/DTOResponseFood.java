package br.eti.gadelha.nutrition.persistence.payload.response;

import br.eti.gadelha.nutrition.persistence.model.Food;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseFood {

    private UUID id;
    private String ibgeCode;
    private String name;
    private String preparation;
    private BigDecimal energy;
    private BigDecimal protein;
    private BigDecimal totalLipids;
    private BigDecimal carbohydrate;
    private BigDecimal fiber;
    private BigDecimal cholesterol;
    private BigDecimal saturated;
    private BigDecimal monounsaturated;
    private BigDecimal polyunsaturated;
    private BigDecimal linoleic;
    private BigDecimal linolenic;
    private BigDecimal trans;
    private BigDecimal totalSugar;
    private BigDecimal addedSugar;
    private BigDecimal calcium;
    private BigDecimal magnesium;
    private BigDecimal manganese;
    private BigDecimal phosphorus;
    private BigDecimal iron;
    private BigDecimal sodium;
    private BigDecimal addedSodium;
    private BigDecimal potassium;
    private BigDecimal copper;
    private BigDecimal zinc;
    private BigDecimal selenium;
    private BigDecimal retinol;
    private BigDecimal vitaminaA;
    private BigDecimal tiamina;
    private BigDecimal riboflavina;
    private BigDecimal niacina;
    private BigDecimal niacinaNE;
    private BigDecimal piridoxina;
    private BigDecimal cobalamina;
    private BigDecimal folato;
    private BigDecimal vitaminaD;
    private BigDecimal vitaminaE;
    private BigDecimal vitaminaC;

    public static DTOResponseFood toDTO(Food value) {
        return new DTOResponseFood(value.getId(), value.getIbgeCode(), value.getName(), value.getPreparation(), value.getEnergy(), value.getProtein(), value.getTotalLipids(), value.getCarbohydrate(), value.getFiber(), value.getCholesterol(), value.getSaturated(), value.getMonounsaturated(), value.getPolyunsaturated(), value.getLinoleic(), value.getLinolenic(), value.getTrans(), value.getTotalSugar(), value.getAddedSugar(), value.getCalcium(), value.getMagnesium(), value.getManganese(), value.getPhosphorus(), value.getIron(), value.getSodium(), value.getAddedSodium(), value.getPotassium(), value.getCopper(), value.getZinc(), value.getSelenium(), value.getRetinol(), value.getVitaminaA(), value.getTiamina(), value.getRiboflavina(), value.getNiacina(), value.getNiacinaNE(), value.getPiridoxina(), value.getCobalamina(), value.getFolato(), value.getVitaminaD(), value.getVitaminaE(), value.getVitaminaC());
    }
}