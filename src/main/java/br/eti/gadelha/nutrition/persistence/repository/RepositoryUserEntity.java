package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositoryUserEntity extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String name);
    boolean existsByUsernameIgnoreCase(String value);
    boolean existsByUsernameIgnoreCaseAndIdNot(String username, UUID id);
    List<UserEntity> findByUsernameContainingIgnoreCaseOrderByUsernameAsc(String value);
}