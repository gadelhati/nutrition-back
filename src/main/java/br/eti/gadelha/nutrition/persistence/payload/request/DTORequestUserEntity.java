package br.eti.gadelha.nutrition.persistence.payload.request;

//import br.eti.gadelha.nutrition.exception.annotation.auth.*;
import br.eti.gadelha.nutrition.persistence.model.Role;
import br.eti.gadelha.nutrition.persistence.model.UserEntity;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.*;
import java.util.Collection;

@Getter @Setter
public class DTORequestUserEntity {

    @NotNull(message = "{user.name.not.null}") @NotBlank(message = "{user.name.not.blank}") //@UniqueUserEntityName @ValidUserEntityName @UserEntityNameLength
    private String username;
    @NotBlank(message = "{user.email.not.blank}") @Size(max = 50) @Email //@UniqueEmail
    private String email;
    @NotNull(message = "{user.password.not.null}") @NotBlank(message = "{user.password.not.blank}") //@PasswordHasDigit @PasswordHasLetter @PasswordLength
    private String password;
    @NotNull(message = "{user.active.not.null}")
    private boolean active;
    private Collection<Role> roles;

    public UserEntity toObject(){
        return new UserEntity(username, email, password);
    }
}