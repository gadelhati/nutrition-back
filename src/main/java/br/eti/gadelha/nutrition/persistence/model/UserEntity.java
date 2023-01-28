package br.eti.gadelha.nutrition.persistence.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.List;

@Audited @Entity @Data @NoArgsConstructor
public class UserEntity extends GenericEntity {

    private String username;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();
}