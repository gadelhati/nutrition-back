package br.eti.gadelha.nutrition.persistence.payload.request;

//import br.eti.gadelha.exception.annotation.auth.UniqueNamePrivilege;
import br.eti.gadelha.nutrition.persistence.model.Privilege;
import br.eti.gadelha.nutrition.persistence.model.Role;
import lombok.Getter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;
import java.util.UUID;

@Getter
public class DTORequestPrivilege {

    private UUID id;
    @NotNull(message = "{privilege.name.not.null}") @NotBlank(message = "{Privilege.name.not.blank}") //@UniqueNamePrivilege
    private String name;
    private Collection<Role> roles;

    public Privilege toObject() { return new Privilege(name, roles); }
}