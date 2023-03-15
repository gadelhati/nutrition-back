package br.eti.gadelha.nutrition.persistence.model;


import lombok.AllArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
public class CompositePK implements Serializable {
    private String name;
    private int number;

}
