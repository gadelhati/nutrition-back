package br.eti.gadelha.nutrition.persistence.payload.response;

import br.eti.gadelha.nutrition.persistence.model.Food;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseFood {

    private UUID id;
    private String name;

    public static DTOResponseFood toDTO(Food value) {
        return new DTOResponseFood(value.getId(), value.getName());
    }
}