package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.Privilege;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface RepositoryPrivilegePage extends PagingAndSortingRepository<Privilege, UUID>, RepositoryInterface<Privilege> {

    Page<Privilege> findByNameContainingIgnoreCaseOrderByNameAsc(Pageable pageable, String name);
}