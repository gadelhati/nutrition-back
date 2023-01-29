package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositoryUserEntity extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String name);
    boolean existsByUsername(String value);
    boolean existsByUsernameContainingIgnoreCase(String value);
    Streamable<UserEntity> findByUsernameContaining(String value);
    Streamable<UserEntity> findByUsernameContainingIgnoreCaseOrderByUsernameAsc(String value);
    Streamable<UserEntity> findByIdNot(UUID value);
}