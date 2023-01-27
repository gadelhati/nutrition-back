package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import java.util.UUID;

public interface RepositoryFood extends JpaRepository<Food, UUID>, RepositoryInterface<Food> {

    Streamable<Food> findByNameContaining(String value);
    Streamable<Food> findByIdNot(UUID value);
}