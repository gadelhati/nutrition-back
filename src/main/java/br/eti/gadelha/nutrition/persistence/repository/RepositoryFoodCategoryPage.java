package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.FoodCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface RepositoryFoodCategoryPage extends PagingAndSortingRepository<FoodCategory, UUID>, RepositoryInterface<FoodCategory> {

    Page<FoodCategory> findByNameContainingIgnoreCaseOrderByNameAsc(Pageable pageable, String name);
}