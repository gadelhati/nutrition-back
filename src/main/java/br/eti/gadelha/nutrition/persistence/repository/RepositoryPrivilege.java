package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RepositoryPrivilege extends JpaRepository<Privilege, UUID>, RepositoryInterface<Privilege> {

}