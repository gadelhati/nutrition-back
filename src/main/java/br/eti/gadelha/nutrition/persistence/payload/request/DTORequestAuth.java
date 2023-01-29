package br.eti.gadelha.nutrition.persistence.payload.request;

import lombok.Data;

@Data
public class DTORequestAuth {
    private String username;
    private String password;
}