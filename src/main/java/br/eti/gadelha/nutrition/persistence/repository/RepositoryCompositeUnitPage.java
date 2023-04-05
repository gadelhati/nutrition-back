package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.CompositePK;
import br.eti.gadelha.nutrition.persistence.model.CompositeUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RepositoryCompositeUnitPage extends PagingAndSortingRepository<CompositeUnit, CompositePK> {

    //    Page<CompositeUnit> findByNameAndNumberOrderByNameAndNumberAsc(Pageable pageable, String name, int number);
    Page<CompositeUnit> findByNameContainingIgnoreCaseOrderByNameAsc(Pageable pageable, String name);
}