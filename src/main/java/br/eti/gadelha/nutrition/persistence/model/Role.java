package br.eti.gadelha.nutrition.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.Collection;

@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Role extends GenericEntity {

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;
}