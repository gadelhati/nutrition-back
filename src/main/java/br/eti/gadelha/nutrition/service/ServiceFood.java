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
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ServiceFood implements ServiceInterface<DTOResponseFood, DTORequestFood, Food> {

    private final RepositoryFood repositoryFood;

    @Override
    public DTOResponseFood create(DTORequestFood created){
        return MapStruct.MAPPER.toDTO(repositoryFood.save(MapStruct.MAPPER.toObject(created)));
    }
    @Override
    public DTOResponseFood retrieve(UUID id){
        return MapStruct.MAPPER.toDTO(repositoryFood.findById(id).orElseGet(null));
    }
    public List<DTOResponseFood> retrieve(List<Food> list){
        return list.stream().map(value -> MapStruct.MAPPER.toDTO(value)).collect(Collectors.toList());
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
        return MapStruct.MAPPER.toDTO(repositoryFood.save(MapStruct.MAPPER.toObject(updated)));
    }
    @Override
    public DTOResponseFood delete(UUID id){
        repositoryFood.deleteById(id);
        return MapStruct.MAPPER.toDTO(repositoryFood.findById(id).orElse(null));
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