package br.eti.gadelha.nutrition.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import java.util.HashSet;
import java.util.Set;

@Audited @Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Privilege extends GenericEntity {

    private String name;

//    @ManyToMany(mappedBy = "privileges")
//    private Set<Role> roles = new HashSet<>();
}