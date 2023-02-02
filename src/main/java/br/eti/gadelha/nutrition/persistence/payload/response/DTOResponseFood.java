package br.eti.gadelha.nutrition.persistence.payload.response;

import br.eti.gadelha.nutrition.persistence.model.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseFood {

    private UUID id;
    private String IBGECODE;
    private String name;
    private String preparation;
    private float energy;
    private float protein;
    private float totalLipids;
    private float carbohydrate;
    private float fiber;

    public static DTOResponseFood toDTO(Food value) {
        return new DTOResponseFood(value.getId(), value.getIBGECODE(), value.getName(), value.getPreparation(), value.getEnergy(), value.getProtein(), value.getTotalLipids(), value.getCarbohydrate(), value.getFiber());
    }
}