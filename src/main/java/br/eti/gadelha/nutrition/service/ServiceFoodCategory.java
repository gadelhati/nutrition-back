package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.MapStruct;
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
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ServiceFoodCategory implements ServiceInterface<DTOResponseFoodCategory, DTORequestFoodCategory, FoodCategory> {

    private final RepositoryFoodCategory repositoryFoodCategory;

    @Override
    public DTOResponseFoodCategory create(DTORequestFoodCategory created){
        return MapStruct.MAPPER.toDTO(repositoryFoodCategory.save(MapStruct.MAPPER.toObject(created)));
    }
    @Override
    public DTOResponseFoodCategory retrieve(UUID id){
        return MapStruct.MAPPER.toDTO(repositoryFoodCategory.findById(id).orElseGet(null));
    }
    public List<DTOResponseFoodCategory> retrieve(List<FoodCategory> list){
        return list.stream().map(value -> MapStruct.MAPPER.toDTO(value)).collect(Collectors.toList());
    }
    @Override
    public List<DTOResponseFoodCategory> retrieve(){
        return retrieve(repositoryFoodCategory.findAll());
    }
    @Override
    public Page<DTOResponseFoodCategory> retrieve(Pageable pageable, String value){
        List<DTOResponseFoodCategory> list = new ArrayList<>();
        if (value == null) {
            return new PageImpl<>(retrieve(repositoryFoodCategory.findAll()), pageable, list.size());
        } else {
            return new PageImpl<>(retrieve(repositoryFoodCategory.findByNameContainingIgnoreCaseOrderByNameAsc(value)), pageable, list.size());
        }
    }
    @Override
    public DTOResponseFoodCategory update(UUID id, DTORequestFoodCategory updated){
        return MapStruct.MAPPER.toDTO(repositoryFoodCategory.save(MapStruct.MAPPER.toObject(updated)));
    }
    @Override
    public DTOResponseFoodCategory delete(UUID id){
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