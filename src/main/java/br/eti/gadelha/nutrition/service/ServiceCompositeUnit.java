package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.MapStruct;
import br.eti.gadelha.nutrition.persistence.model.CompositePK;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestCompositeUnit;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseCompositeUnit;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryCompositeUnit;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryCompositeUnitPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class ServiceCompositeUnit {

    private final RepositoryCompositeUnit repositoryCompositeUnit;
    private final RepositoryCompositeUnitPage repositoryCompositeUnitPage;

    public DTOResponseCompositeUnit create(DTORequestCompositeUnit created){
        return MapStruct.MAPPER.toDTO(repositoryCompositeUnit.save(MapStruct.MAPPER.toObject(created)));
    }
    public Page<DTOResponseCompositeUnit> retrieve(Pageable pageable, String filter, String name, int number) {
        switch (pageable.getSort().toString().substring(0, pageable.getSort().toString().length() - 5)) {
            case "id": {
//                return repositoryCompositeUnitPage.findByNameAndNumberOrderByNameAndNumberAsc(pageable, name, number).map(object -> MapStruct.MAPPER.toDTO(object));
                return repositoryCompositeUnitPage.findAll(pageable).map(MapStruct.MAPPER::toDTO);
            }
            case "name": {
                return repositoryCompositeUnitPage.findByNameContainingIgnoreCaseOrderByNameAsc(pageable, filter).map(MapStruct.MAPPER::toDTO);
            }
            default: {
                return repositoryCompositeUnitPage.findAll(pageable).map(MapStruct.MAPPER::toDTO);
            }
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
}