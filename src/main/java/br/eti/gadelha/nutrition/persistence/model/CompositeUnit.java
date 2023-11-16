package br.eti.gadelha.nutrition.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@IdClass(CompositePK.class)
@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor
public class CompositeUnit {
    @Id
    private String name;
    @Id
    private int number;
    private String value;
}
