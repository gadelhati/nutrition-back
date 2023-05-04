package br.eti.gadelha.nutrition.persistence.payload.response;

import br.eti.gadelha.nutrition.persistence.model.Privilege;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseRole {

    private UUID id;
    private String name;
//    private Set<Privilege> privileges = new HashSet<>();
}