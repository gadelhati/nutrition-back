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
    private Float energy;
    private Float protein;
    private Float totalLipids;
    private Float carbohydrate;
    private Float fiber;
//    fats and sugar
    private Float cholesterol;
    private Float saturated;
    private Float monounsaturated;
    private Float polyunsaturated;
    private Float linoleic;
    private Float linolenic;
    private Float trans;
    private Float totalSugar;
    private Float addedSugar;
//    minearals
    private Float calcium;
    private Float magnesium;
    private Float manganese;
    private Float phosphorus;
    private Float iron;
    private Float sodium;
    private Float addedSodium;
    private Float potassium;
    private Float copper;
    private Float zinc;
    private Float selenium;
//    vitaminas
    private Float retinol;
    private Float vitaminaA;
    private Float tiamina;
    private Float riboflavina;
    private Float niacina;
    private Float niacinaNE;
    private Float piridoxina;
    private Float cobalamina;
    private Float folato;
    private Float vitaminaD;
    private Float vitaminaE;
    private Float vitaminaC;
}