package br.eti.gadelha.nutrition.service;

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
        return DTOResponseFood.toDTO(repositoryFood.save(created.toObject()));
    }
    public DTOResponseFood retrieve(UUID id){
        return DTOResponseFood.toDTO(repositoryFood.findById(id).orElse(null));
    }
    public List<DTOResponseFood> retrieve(){
        List<DTOResponseFood> list = new ArrayList<>();
        for(Food object: repositoryFood.findAll()) {
            list.add(DTOResponseFood.toDTO(object));
        }
        return list;
    }
    public Page<DTOResponseFood> retrieve(Pageable pageable){
        List<DTOResponseFood> list = new ArrayList<>();
        for(Food object: repositoryFood.findAll()) {
            list.add(DTOResponseFood.toDTO(object));
        }
        return new PageImpl<>(list, pageable, list.size());
    }
    public Page<DTOResponseFood> retrieve(Pageable pageable, String source){
        final List<DTOResponseFood> list = new ArrayList<>();
        if (source == null) {
            return retrieve(pageable);
        } else {
            for (Food object : repositoryFood.findByNameContainingIgnoreCaseOrderByNameAsc(source)) {
                list.add(DTOResponseFood.toDTO(object));
            }
        }
        return new PageImpl<>(list, pageable, list.size());
    }
    public DTOResponseFood update(UUID id, DTORequestFood updated){
        Food object = repositoryFood.findById(id).orElse(null);
        object.setName(updated.getName());
        return DTOResponseFood.toDTO(repositoryFood.save(object));
    }
    public DTOResponseFood delete(UUID id){
        Food object = repositoryFood.findById(id).orElse(null);
        repositoryFood.deleteById(id);
        return DTOResponseFood.toDTO(object);
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