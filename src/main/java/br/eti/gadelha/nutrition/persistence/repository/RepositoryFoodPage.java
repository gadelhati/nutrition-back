package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.Food;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface RepositoryFoodPage extends PagingAndSortingRepository<Food, UUID>, RepositoryInterfacePage<Food> {

}