package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.MapStruct;
import br.eti.gadelha.nutrition.persistence.model.Role;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestRole;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseRole;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryRole;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryRolePage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service @RequiredArgsConstructor
public class ServiceRole implements ServiceInterface<DTOResponseRole, DTORequestRole, Role> {

    private final RepositoryRole repositoryRole;
    private final RepositoryRolePage repositoryRolePage;

    @Override
    public DTOResponseRole create(DTORequestRole created){
        return MapStruct.MAPPER.toDTO(repositoryRole.save(MapStruct.MAPPER.toObject(created)));
    }
    @Override
    public Page<DTOResponseRole> retrieve(Pageable pageable, String filter) {
        switch (pageable.getSort().toString().substring(0, pageable.getSort().toString().length() - 5)) {
            case "id": {
                return repositoryRolePage.findByIdOrderByIdAsc(pageable, UUID.fromString(filter)).map(object -> MapStruct.MAPPER.toDTO(object));
            }
            case "name": {
                return repositoryRolePage.findByNameContainingIgnoreCaseOrderByNameAsc(pageable, filter).map(object -> MapStruct.MAPPER.toDTO(object));
            }
            default: {
                return repositoryRolePage.findAll(pageable).map(object -> MapStruct.MAPPER.toDTO(object));
            }
        }
    }
    @Override
    public DTOResponseRole update(UUID id, DTORequestRole updated){
        return MapStruct.MAPPER.toDTO(repositoryRole.save(MapStruct.MAPPER.toObject(updated)));
    }
    @Override
    public DTOResponseRole delete(UUID id){
        repositoryRole.deleteById(id);
        return MapStruct.MAPPER.toDTO(repositoryRole.findById(id).orElse(null));
    }
    @Override
    public void delete() {
        repositoryRole.deleteAll();
    }

    public boolean existsByName(String value) {
        return repositoryRole.existsByNameIgnoreCase(value);
    }
    public boolean existsByNameAndIdNot(String value, UUID id) {
        return repositoryRole.existsByNameIgnoreCaseAndIdNot(value, id);
    }
}