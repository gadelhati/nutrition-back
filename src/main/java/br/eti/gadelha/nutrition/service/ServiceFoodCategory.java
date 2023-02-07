package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.model.FoodCategory;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestFoodCategory;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseFoodCategory;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryFoodCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class ServiceFoodCategory implements ServiceInterface<DTOResponseFoodCategory, DTORequestFoodCategory, FoodCategory> {

    private final RepositoryFoodCategory repositoryFoodCategory;

    public DTOResponseFoodCategory create(DTORequestFoodCategory created){
        return DTOResponseFoodCategory.toDTO(repositoryFoodCategory.save(created.toObject()));
    }
    public DTOResponseFoodCategory retrieve(UUID id){
        return DTOResponseFoodCategory.toDTO(repositoryFoodCategory.findById(id).orElse(null));
    }
    public List<DTOResponseFoodCategory> retrieve(){
        List<DTOResponseFoodCategory> list = new ArrayList<>();
        for(FoodCategory object: repositoryFoodCategory.findAll()) {
            list.add(DTOResponseFoodCategory.toDTO(object));
        }
        return list;
    }
    public Page<DTOResponseFoodCategory> retrieve(Pageable pageable){
        List<DTOResponseFoodCategory> list = new ArrayList<>();
        for(FoodCategory object: repositoryFoodCategory.findAll()) {
            list.add(DTOResponseFoodCategory.toDTO(object));
        }
        return new PageImpl<>(list, pageable, list.size());
    }
    public Page<DTOResponseFoodCategory> retrieve(Pageable pageable, String value){
        final List<DTOResponseFoodCategory> list = new ArrayList<>();
        if (value == null) {
            return retrieve(pageable);
        } else {
            for (FoodCategory object : repositoryFoodCategory.findByNameContainingIgnoreCaseOrderByNameAsc(value)) {
                list.add(DTOResponseFoodCategory.toDTO(object));
            }
        }
        return new PageImpl<>(list, pageable, list.size());
    }
    public DTOResponseFoodCategory update(UUID id, DTORequestFoodCategory updated){
        FoodCategory object = repositoryFoodCategory.findById(id).orElse(null);
        object.setName(updated.getName());
        return DTOResponseFoodCategory.toDTO(repositoryFoodCategory.save(object));
    }
    public DTOResponseFoodCategory delete(UUID id){
        FoodCategory object = repositoryFoodCategory.findById(id).orElse(null);
        repositoryFoodCategory.deleteById(id);
        return DTOResponseFoodCategory.toDTO(object);
    }
    public void delete() {
        repositoryFoodCategory.deleteAll();
    }
    public FoodCategory findByName(String value) { return  repositoryFoodCategory.findByName(value); }
    public boolean existsByName(String value) {
        return repositoryFoodCategory.existsByNameContainingIgnoreCase(value);
    }

    public boolean existsByNameAndIdNot(String value, UUID id) {
        return !repositoryFoodCategory.findByNameContaining(value).and(repositoryFoodCategory.findByIdNot(id)).isEmpty();
    }
}