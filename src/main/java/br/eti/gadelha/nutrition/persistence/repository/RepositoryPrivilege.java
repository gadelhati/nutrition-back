package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import java.util.UUID;

public interface RepositoryPrivilege extends JpaRepository<Privilege, UUID>, RepositoryInterface<Privilege> {

    Streamable<Privilege> findByNameContaining(String value);
    Streamable<Privilege> findByIdNot(UUID value);
}