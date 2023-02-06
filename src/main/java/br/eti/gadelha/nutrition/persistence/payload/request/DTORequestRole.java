package br.eti.gadelha.nutrition.persistence.payload.request;

//import br.eti.gadelha.exception.annotation.auth.UniqueNameRole;
import br.eti.gadelha.nutrition.persistence.model.Role;
import lombok.Getter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Getter
public class DTORequestRole {

    private UUID id;
    @NotNull(message = "{role.name.not.null}") @NotBlank(message = "{role.name.not.blank}") //@UniqueNameRole
    private String name;

    public Role toObject() { return new Role(name); }
}