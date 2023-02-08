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

    public DTOResponseFood create(DTORequestFood created){
        return MapStruct.MAPPER.toFood(repositoryFood.save(MapStruct.MAPPER.toDTOFood(created)));
    }
    public DTOResponseFood retrieve(UUID id){
        return MapStruct.MAPPER.toFood(repositoryFood.findById(id).orElse(null));
    }
    public List<DTOResponseFood> retrieve(){
        List<DTOResponseFood> list = new ArrayList<>();
        for(Food object: repositoryFood.findAll()) {
            list.add(MapStruct.MAPPER.toFood(object));
        }
        return list;
    }
    public Page<DTOResponseFood> retrieve(Pageable pageable, String value){
        List<DTOResponseFood> list = new ArrayList<>();
        if (value == null) {
            for(Food object: repositoryFood.findAll()) {
                list.add(MapStruct.MAPPER.toFood(object));
            }
        } else {
            for (Food object : repositoryFood.findByNameContainingIgnoreCaseOrderByNameAsc(value)) {
                list.add(MapStruct.MAPPER.toFood(object));
            }
        }
        return new PageImpl<>(list, pageable, list.size());
    }
    public DTOResponseFood update(UUID id, DTORequestFood updated){
        Food object = repositoryFood.findById(id).orElse(null);
        object.setName(updated.getName());
        object.setIbgeCode(updated.getIbgeCode());
        return DTOResponseFood.toDTO(repositoryFood.save(object));
    }
    public DTOResponseFood delete(UUID id){
        repositoryFood.deleteById(id);
        return MapStruct.MAPPER.toFood(repositoryFood.findById(id).orElse(null));
    }
    public void delete() {
        repositoryFood.deleteAll();
    }
    public Food findByName(String value) { return  repositoryFood.findByName(value); }
    public boolean existsByName(String value) {
        return repositoryFood.existsByNameContainingIgnoreCase(value);
    }

    public boolean existsByNameAndIdNot(String value, UUID id) {
        return !repositoryFood.findByNameContaining(value).and(repositoryFood.findByIdNot(id)).isEmpty();
    }
}