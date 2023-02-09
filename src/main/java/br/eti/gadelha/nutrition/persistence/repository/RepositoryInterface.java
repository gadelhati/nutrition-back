package br.eti.gadelha.nutrition.persistence.repository;

import java.util.List;
import java.util.UUID;

public interface RepositoryInterface<T> {
    List<T> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
    T findByName(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String username, UUID id);
    boolean existsByName(String value);
    boolean existsByNameIgnoreCase(String value);
}