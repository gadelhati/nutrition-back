package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.MapStruct;
import br.eti.gadelha.nutrition.persistence.model.Food;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestFood;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseFood;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryFood;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class ServiceFood implements ServiceInterface<DTOResponseFood, DTORequestFood, Food> {

    private final RepositoryFood repositoryFood;

    @Override
    public DTOResponseFood create(DTORequestFood created){
        return MapStruct.MAPPER.toDTOFood(repositoryFood.save(MapStruct.MAPPER.toFood(created)));
    }
    @Override
    public DTOResponseFood retrieve(UUID id){
        return MapStruct.MAPPER.toDTOFood(repositoryFood.findById(id).orElseGet(null));
    }
    public List<DTOResponseFood> retrieve(List<Food> list){
        List<DTOResponseFood> search = new ArrayList<>();
        list.stream().forEach(value -> search.add(MapStruct.MAPPER.toDTOFood(value)));
        return search;
    }
    @Override
    public List<DTOResponseFood> retrieve(){
        return retrieve(repositoryFood.findAll());
    }
    @Override
    public Page<DTOResponseFood> retrieve(Pageable pageable, String value){
        List<DTOResponseFood> list = new ArrayList<>();
        if (value == null) {
            return new PageImpl<>(retrieve(repositoryFood.findAll()), pageable, list.size());
        } else {
            return new PageImpl<>(retrieve(repositoryFood.findByNameContainingIgnoreCaseOrderByNameAsc(value)), pageable, list.size());
        }
    }
    @Override
    public DTOResponseFood update(UUID id, DTORequestFood updated){
        return MapStruct.MAPPER.toDTOFood(repositoryFood.save(MapStruct.MAPPER.toFood(updated)));
    }
    @Override
    public DTOResponseFood delete(UUID id){
        repositoryFood.deleteById(id);
        return MapStruct.MAPPER.toDTOFood(repositoryFood.findById(id).orElse(null));
    }
    @Override
    public void delete() {
        repositoryFood.deleteAll();
    }

    public boolean existsByName(String value) {
        return repositoryFood.existsByNameIgnoreCase(value);
    }
    public boolean existsByNameAndIdNot(String value, UUID id) {
        return repositoryFood.existsByNameIgnoreCaseAndIdNot(value, id);
    }
    public boolean existsByIbgeCode(String value) {
        return repositoryFood.existsByIbgeCodeIgnoreCase(value);
    }
    public boolean existsByIbgeCodeAndIdNot(String value, UUID id) {
        return repositoryFood.existsByIbgeCodeIgnoreCaseAndIdNot(value, id);
    }
}