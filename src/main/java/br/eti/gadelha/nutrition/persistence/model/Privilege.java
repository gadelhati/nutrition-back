package br.eti.gadelha.nutrition.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.Collection;

@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Privilege extends GenericEntity {

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}