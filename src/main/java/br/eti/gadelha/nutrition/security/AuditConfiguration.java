package br.eti.gadelha.nutrition.security;

import br.eti.gadelha.nutrition.persistence.model.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef="auditorProvider")
public class AuditConfiguration {

    @Bean
    public AuditorAware auditorProvider() {
        return new AuditorAwareImpl();
    }
}