package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;

import java.util.UUID;

public interface RepositoryFoodCategory extends JpaRepository<FoodCategory, UUID>, RepositoryInterface<FoodCategory> {

}