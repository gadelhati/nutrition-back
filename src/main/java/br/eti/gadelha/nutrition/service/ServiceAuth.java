package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.model.UserEntity;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestAuth;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseAuth;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseUserEntity;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryRole;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryUserEntity;
import br.eti.gadelha.nutrition.security.JWTGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service @RequiredArgsConstructor
public class ServiceAuth {

    private final RepositoryUserEntity repositoryUserEntity;
    private final RepositoryRole repositoryRole;
    private final PasswordEncoder passwordEncoder;
    private final JWTGenerator jwtGenerator;

    public DTOResponseAuth login(DTORequestAuth dtoRequestAuth) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(dtoRequestAuth.getUsername(), null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new DTOResponseAuth(token);
    }
    public DTOResponseUserEntity register(DTORequestAuth created) {
        if (repositoryUserEntity.existsByUsername(created.getUsername())) {
            return null;
        }
        UserEntity userEntity = new UserEntity(
                created.getUsername(),
                passwordEncoder.encode(created.getPassword()),
                Arrays.asList(repositoryRole.findByName("ROLE_USER"))
        );
        return DTOResponseUserEntity.toDTO(repositoryUserEntity.save(userEntity));
    }
}