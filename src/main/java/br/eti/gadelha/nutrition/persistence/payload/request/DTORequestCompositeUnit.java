package br.eti.gadelha.nutrition.persistence.payload.request;

import br.eti.gadelha.nutrition.persistence.model.CompositePK;
import lombok.Getter;

@Getter
public class DTORequestCompositeUnit {

    private CompositePK id;
    private String name;
    private int number;
}