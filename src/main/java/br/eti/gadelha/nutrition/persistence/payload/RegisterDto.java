package br.eti.gadelha.nutrition.persistence.payload;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
}
