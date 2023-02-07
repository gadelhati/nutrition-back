package br.eti.gadelha.nutrition.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "serviceSecurityAuditorAware")
public class AuditConfiguration {

    @Bean
    public AuditorAware<UUID> auditorProvider() {
        return new ServiceSecurityAuditorAware();
    }
}