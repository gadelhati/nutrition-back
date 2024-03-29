package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RepositoryUserEntity extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findByUsername(String name);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, UUID id);
    boolean existsByUsernameIgnoreCase(String value);
    boolean existsByUsernameIgnoreCaseAndIdNot(String username, UUID id);
    List<UserEntity> findByUsernameContainingIgnoreCaseOrderByUsernameAsc(String value);
    Page<UserEntity> findByIdOrderByIdAsc(Pageable pageable, UUID id);
    Page<UserEntity> findByUsernameContainingIgnoreCaseOrderByUsernameAsc(Pageable pageable, String username);
    Page<UserEntity> findByEmailContainingIgnoreCaseOrderByEmailAsc(Pageable pageable, String username);
}