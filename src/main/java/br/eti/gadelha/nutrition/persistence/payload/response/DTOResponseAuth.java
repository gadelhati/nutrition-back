package br.eti.gadelha.nutrition.persistence.payload.response;

import lombok.Data;

@Data
public class DTOResponseAuth {

    private String accessToken;
    private String tokenType = "Bearer ";

    public DTOResponseAuth(String accessToken) {
        this.accessToken = accessToken;
    }
}