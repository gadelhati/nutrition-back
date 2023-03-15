package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.MapStruct;
import br.eti.gadelha.nutrition.persistence.model.CompositePK;
import br.eti.gadelha.nutrition.persistence.model.CompositeUnit;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestCompositeUnit;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseCompositeUnit;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryCompositeUnit;
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
public class ServiceCompositeUnit {

    private final RepositoryCompositeUnit repositoryCompositeUnit;

    public DTOResponseCompositeUnit create(DTORequestCompositeUnit created){
        return MapStruct.MAPPER.toDTO(repositoryCompositeUnit.save(MapStruct.MAPPER.toObject(created)));
    }
    public DTOResponseCompositeUnit retrieve(CompositePK id){
        return MapStruct.MAPPER.toDTO(repositoryCompositeUnit.findById(id).orElseGet(null));
    }
    public List<DTOResponseCompositeUnit> retrieve(List<CompositeUnit> list){
        return list.stream().map(value -> MapStruct.MAPPER.toDTO(value)).collect(Collectors.toList());
    }
    public List<DTOResponseCompositeUnit> retrieve(){
        return retrieve(repositoryCompositeUnit.findAll());
    }
    public Page<DTOResponseCompositeUnit> retrieve(Pageable pageable, String value){
        List<DTOResponseCompositeUnit> list = new ArrayList<>();
        if (value == null) {
            return new PageImpl<>(retrieve(repositoryCompositeUnit.findAll()), pageable, list.size());
        } else {
            return null;
//            return new PageImpl<>(retrieve(repositoryCompositeUnit.findByNameContainingIgnoreCaseOrderByNameAsc(value)), pageable, list.size());
        }
    }
    public DTOResponseCompositeUnit update(CompositePK id, DTORequestCompositeUnit updated){
        return MapStruct.MAPPER.toDTO(repositoryCompositeUnit.save(MapStruct.MAPPER.toObject(updated)));
    }
    public DTOResponseCompositeUnit delete(CompositePK id){
        repositoryCompositeUnit.deleteById(id);
        return MapStruct.MAPPER.toDTO(repositoryCompositeUnit.findById(id).orElse(null));
    }
    public void delete() {
        repositoryCompositeUnit.deleteAll();
    }

//    public boolean existsByName(String value) {
//        return repositoryCompositeUnit.existsByNameIgnoreCase(value);
//    }
//    public boolean existsByNameAndIdNot(String value, CompositePK id) {
//        return repositoryCompositeUnit.existsByNameIgnoreCaseAndIdNot(value, id);
//    }
}