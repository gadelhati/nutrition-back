package br.eti.gadelha.nutrition.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor @Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name"})) @EqualsAndHashCode(callSuper = false)
public class Food extends GenericEntity {

    private String IBGECODE;
    private String name;
    private String preparation;
    private float energy;
    private float protein;
    private float totalLipids;
    private float carbohydrate;
    private float fiber;
}