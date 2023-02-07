package br.eti.gadelha.nutrition.security;

import br.eti.gadelha.nutrition.persistence.repository.RepositoryUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ServiceSecurityAuditorAware implements AuditorAware<UUID> {

    @Autowired
    private RepositoryUserEntity repositoryUserEntity;

    @Override
    public Optional<UUID> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return Optional.of(repositoryUserEntity.findByUsername(authentication.getName()).get().getId());
    }
}