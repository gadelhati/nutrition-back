package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.MapStruct;
import br.eti.gadelha.nutrition.persistence.model.FoodCategory;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestFoodCategory;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseFoodCategory;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryFoodCategory;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryFoodCategoryPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceFoodCategory implements ServiceInterface<DTOResponseFoodCategory, DTORequestFoodCategory> {

    private final RepositoryFoodCategory repositoryFoodCategory;
    private final RepositoryFoodCategoryPage repositoryFoodCategoryPage;

    @Override
    public DTOResponseFoodCategory create(DTORequestFoodCategory created) {
        return MapStruct.MAPPER.toDTO(repositoryFoodCategory.save(MapStruct.MAPPER.toObject(created)));
    }

    @Override
    public Page<DTOResponseFoodCategory> retrieve(Pageable pageable, String filter) {
        switch (pageable.getSort().toString().substring(0, pageable.getSort().toString().length() - 5)) {
            case "id": {
                return repositoryFoodCategoryPage.findByIdOrderByIdAsc(pageable, UUID.fromString(filter)).map(MapStruct.MAPPER::toDTO);
            }
            case "name": {
                return repositoryFoodCategoryPage.findByNameContainingIgnoreCaseOrderByNameAsc(pageable, filter).map(MapStruct.MAPPER::toDTO);
            }
            default: {
                return repositoryFoodCategoryPage.findAll(pageable).map(MapStruct.MAPPER::toDTO);
            }
        }
    }

    @Override
    public DTOResponseFoodCategory update(UUID id, DTORequestFoodCategory updated) {
        return MapStruct.MAPPER.toDTO(repositoryFoodCategory.save(MapStruct.MAPPER.toObject(updated)));
    }

    @Override
    public DTOResponseFoodCategory delete(UUID id) {
        repositoryFoodCategory.deleteById(id);
        return MapStruct.MAPPER.toDTO(repositoryFoodCategory.findById(id).orElse(null));
    }

    @Override
    public void delete() {
        repositoryFoodCategory.deleteAll();
    }

    public boolean existsByName(String value) {
        return repositoryFoodCategory.existsByNameIgnoreCase(value);
    }

    public boolean existsByNameAndIdNot(String value, UUID id) {
        return repositoryFoodCategory.existsByNameIgnoreCaseAndIdNot(value, id);
    }
}