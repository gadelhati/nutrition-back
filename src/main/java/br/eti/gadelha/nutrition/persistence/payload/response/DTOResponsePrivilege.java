package br.eti.gadelha.nutrition.persistence.payload.response;

import br.eti.gadelha.nutrition.persistence.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponsePrivilege {

    private UUID id;
    private String name;
//    private Set<Role> roles = new HashSet<>();
}