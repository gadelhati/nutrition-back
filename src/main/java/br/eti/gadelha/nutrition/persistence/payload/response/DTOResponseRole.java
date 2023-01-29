package br.eti.gadelha.nutrition.persistence.payload.response;

import br.eti.gadelha.nutrition.persistence.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseRole {

    private UUID id;
    private String name;

    public static DTOResponseRole toDTO(Role value) {
        return new DTOResponseRole(value.getId(), value.getName());
    }
}