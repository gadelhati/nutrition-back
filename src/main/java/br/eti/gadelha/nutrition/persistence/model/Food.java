package br.eti.gadelha.nutrition.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor @Table(uniqueConstraints = @UniqueConstraint(columnNames = {"IBGECODE", "name"})) @EqualsAndHashCode(callSuper = false)
public class Food extends GenericEntity {

    private String IBGECODE;
    private String name;
    private String preparation;
//    energy, macronutrients and fiber
    private float energy;
    private float protein;
    private float totalLipids;
    private float carbohydrate;
    private float fiber;
//    fats and sugar
    private float cholesterol;
    private float saturated;
    private float monounsaturated;
    private float polyunsaturated;
    private float linoleic;
    private float linolenic;
    private float trans;
    private float totalSugar;
    private float addedSugar;
//    minearals
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
//    vitaminas
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
}