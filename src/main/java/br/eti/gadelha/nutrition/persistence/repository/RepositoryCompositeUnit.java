package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.CompositePK;
import br.eti.gadelha.nutrition.persistence.model.CompositeUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCompositeUnit extends JpaRepository<CompositeUnit, CompositePK> {
    Page<CompositeUnit> findByNameAndNumberOrderByNameAsc(Pageable pageable, String name, int number);
    Page<CompositeUnit> findByNumberAndNameOrderByNumberAsc(Pageable pageable, String name, int number);
    Page<CompositeUnit> findByNameContainingIgnoreCaseOrderByNameAsc(Pageable pageable, String name);
    Page<CompositeUnit> findByValueContainingIgnoreCaseOrderByValueAsc(Pageable pageable, String name);
}