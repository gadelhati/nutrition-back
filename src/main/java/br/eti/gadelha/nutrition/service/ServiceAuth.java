package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestAuth;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseAuth;
import br.eti.gadelha.nutrition.security.JWTGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ServiceAuth {

    private final AuthenticationManager authenticationManager;
    private final JWTGenerator jwtGenerator;

    public DTOResponseAuth login(DTORequestAuth dtoRequestAuth) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dtoRequestAuth.getUsername(), dtoRequestAuth.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new DTOResponseAuth(token);
    }
}