package br.eti.gadelha.nutrition.persistence.payload.response;

import br.eti.gadelha.nutrition.persistence.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Collection;
import java.util.UUID;

@Getter @AllArgsConstructor
public class DTOResponseUserEntity {

    private UUID id;
    private String username;
    private String email;
    private Boolean active;
    private Collection<Role> roles;
}