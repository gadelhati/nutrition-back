package br.eti.gadelha.nutrition.persistence.model;

import lombok.Data;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

@Audited @Entity @Data
public class Role extends GenericEntity {

    private String name;
}