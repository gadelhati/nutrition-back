package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.CompositePK;
import br.eti.gadelha.nutrition.persistence.model.CompositeUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface RepositoryCompositeUnitPage extends PagingAndSortingRepository<CompositeUnit, CompositePK> {

    Page<CompositeUnit> findByNameContainingIgnoreCaseOrderByNameAsc(Pageable pageable, String name);
}