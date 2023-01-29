package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import java.util.UUID;

public interface RepositoryRole extends JpaRepository<Role, UUID>, RepositoryInterface<Role> {

    Streamable<Role> findByNameContaining(String value);
    Streamable<Role> findByIdNot(UUID value);
}