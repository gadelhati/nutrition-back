package br.eti.gadelha.nutrition.persistence.model;

import br.eti.gadelha.nutrition.persistence.EnumMenu;
import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Role extends GenericEntity {

    private String name;
    private Collection<EnumMenu> menu;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "role_privilege", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Set<Privilege> privileges = new HashSet<>();
}