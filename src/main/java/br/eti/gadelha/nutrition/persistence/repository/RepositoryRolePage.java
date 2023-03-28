package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface RepositoryRolePage extends PagingAndSortingRepository<Role, UUID>, RepositoryInterface<Role> {

    Page<Role> findByNameContainingIgnoreCaseOrderByNameAsc(Pageable pageable, String name);
}