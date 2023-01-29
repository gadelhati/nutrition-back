package br.eti.gadelha.nutrition.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Role extends GenericEntity {

    private String name;
}