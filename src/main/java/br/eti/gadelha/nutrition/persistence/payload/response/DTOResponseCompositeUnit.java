package br.eti.gadelha.nutrition.persistence.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class DTOResponseCompositeUnit {

    private String name;
    private int number;
    private String value;
}