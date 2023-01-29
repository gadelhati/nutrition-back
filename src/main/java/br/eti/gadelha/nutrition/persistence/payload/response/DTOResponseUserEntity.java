package br.eti.gadelha.nutrition.persistence.payload.response;

//import br.eti.gadelha.nutrition.exception.annotation.auth.UniqueEmail;
//import br.eti.gadelha.nutrition.exception.annotation.auth.UniqueNameRole;
import br.eti.gadelha.nutrition.persistence.model.Role;
import br.eti.gadelha.nutrition.persistence.model.UserEntity;
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
    @NotBlank @Size(min = 3, max = 20) //@UniqueNameRole
    private String username;
    @NotBlank @Size(max = 50) @Email //@UniqueEmail
    private String email;
    private String password;
    private Boolean active;
    private Collection<Role> roles;

    public static DTOResponseUserEntity toDTO(UserEntity value) {
        return new DTOResponseUserEntity(value.getId(), value.getUsername(), value.getEmail(), value.getPassword(), value.getActive(), value.getRoles());
    }
}