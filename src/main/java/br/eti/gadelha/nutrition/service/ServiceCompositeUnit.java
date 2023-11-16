package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.MapStruct;
import br.eti.gadelha.nutrition.persistence.model.CompositePK;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestCompositeUnit;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseCompositeUnit;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryCompositeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service @RequiredArgsConstructor
public class ServiceCompositeUnit {

    private final RepositoryCompositeUnit repositoryCompositeUnit;

    public DTOResponseCompositeUnit create(DTORequestCompositeUnit created){
        return MapStruct.MAPPER.toDTO(repositoryCompositeUnit.save(MapStruct.MAPPER.toObject(created)));
    }
    public Page<DTOResponseCompositeUnit> retrieveComposite(Pageable pageable, String key, String value, String name, int number) {
        switch (key) {
            case "name": {
                return repositoryCompositeUnit.findByNameAndNumberOrderByNameAsc(pageable, name, number).map(MapStruct.MAPPER::toDTO);
            }
            case "number": {
                return repositoryCompositeUnit.findByNumberAndNameOrderByNumberAsc(pageable, name, number).map(MapStruct.MAPPER::toDTO);
            }
            case "value": {
                return repositoryCompositeUnit.findByValueContainingIgnoreCaseOrderByValueAsc(pageable, value).map(MapStruct.MAPPER::toDTO);
            }
            default: {
                return repositoryCompositeUnit.findAll(pageable).map(MapStruct.MAPPER::toDTO);
            }
        }
    }
    public Page<DTOResponseCompositeUnit> retrieve(Pageable pageable, String key, String value) {
        switch (key) {
            case "name": {
                return repositoryCompositeUnit.findByNameContainingIgnoreCaseOrderByNameAsc(pageable, value).map(MapStruct.MAPPER::toDTO);
            }
            case "value": {
                return repositoryCompositeUnit.findByValueContainingIgnoreCaseOrderByValueAsc(pageable, value).map(MapStruct.MAPPER::toDTO);
            }
            default: {
                return repositoryCompositeUnit.findAll(pageable).map(MapStruct.MAPPER::toDTO);
            }
        }
    }
    public DTOResponseCompositeUnit update(CompositePK id, DTORequestCompositeUnit updated){
        return MapStruct.MAPPER.toDTO(repositoryCompositeUnit.save(MapStruct.MAPPER.toObject(updated)));
    }
    public DTOResponseCompositeUnit delete(CompositePK id){
        DTOResponseCompositeUnit dtoResponseCompositeUnit = MapStruct.MAPPER.toDTO(repositoryCompositeUnit.findById(id).orElse(null));
        repositoryCompositeUnit.deleteById(id);
        return dtoResponseCompositeUnit;
    }
    public void delete() {
        repositoryCompositeUnit.deleteAll();
    }
}