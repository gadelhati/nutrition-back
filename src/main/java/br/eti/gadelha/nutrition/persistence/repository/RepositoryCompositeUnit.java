package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.CompositePK;
import br.eti.gadelha.nutrition.persistence.model.CompositeUnit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCompositeUnit extends JpaRepository<CompositeUnit, CompositePK> {

}