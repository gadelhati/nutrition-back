package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.MapStruct;
import br.eti.gadelha.nutrition.persistence.model.Privilege;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestPrivilege;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponsePrivilege;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryPrivilege;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryPrivilegePage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service @RequiredArgsConstructor
public class ServicePrivilege implements ServiceInterface<DTOResponsePrivilege, DTORequestPrivilege, Privilege> {

    private final RepositoryPrivilege repositoryPrivilege;
    private final RepositoryPrivilegePage repositoryPrivilegePage;

    @Override
    public DTOResponsePrivilege create(DTORequestPrivilege created){
        return MapStruct.MAPPER.toDTO(repositoryPrivilege.save(MapStruct.MAPPER.toObject(created)));
    }
    @Override
    public Page<DTOResponsePrivilege> retrieve(Pageable pageable, String filter) {
        switch (pageable.getSort().toString().substring(0, pageable.getSort().toString().length() - 5)) {
            case "id": {
                return repositoryPrivilegePage.findByIdOrderByIdAsc(pageable, UUID.fromString(filter)).map(object -> MapStruct.MAPPER.toDTO(object));
            }
            case "name": {
                return repositoryPrivilegePage.findByNameContainingIgnoreCaseOrderByNameAsc(pageable, filter).map(object -> MapStruct.MAPPER.toDTO(object));
            }
            default: {
                return repositoryPrivilegePage.findAll(pageable).map(object -> MapStruct.MAPPER.toDTO(object));
            }
        }
    }
    @Override
    public DTOResponsePrivilege update(UUID id, DTORequestPrivilege updated){
        return MapStruct.MAPPER.toDTO(repositoryPrivilege.save(MapStruct.MAPPER.toObject(updated)));
    }
    @Override
    public DTOResponsePrivilege delete(UUID id){
        repositoryPrivilege.deleteById(id);
        return MapStruct.MAPPER.toDTO(repositoryPrivilege.findById(id).orElse(null));
    }
    @Override
    public void delete() {
        repositoryPrivilege.deleteAll();
    }

    public boolean existsByName(String value) {
        return repositoryPrivilege.existsByNameIgnoreCase(value);
    }
    public boolean existsByNameAndIdNot(String value, UUID id) {
        return repositoryPrivilege.existsByNameIgnoreCaseAndIdNot(value, id);
    }
}