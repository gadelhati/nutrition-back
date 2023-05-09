package br.eti.gadelha.nutrition.persistence.payload.request;

import br.eti.gadelha.nutrition.exception.annotation.UniqueNameRole;
import br.eti.gadelha.nutrition.persistence.model.Privilege;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter @UniqueNameRole
public class DTORequestRole {

    private UUID id;
    @NotNull(message = "{role.name.not.null}") @NotBlank(message = "{role.name.not.blank}")
    private String name;
    private Set<Privilege> privileges = new HashSet<>();
}