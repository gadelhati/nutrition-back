package br.eti.gadelha.nutrition.persistence.repository;

import br.eti.gadelha.nutrition.persistence.model.Food;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface RepositoryFoodPage extends PagingAndSortingRepository<Food, UUID>, RepositoryInterface<Food> {

    Page<Food> findByNameContainingIgnoreCaseOrderByNameAsc(Pageable pageable, String name);
}