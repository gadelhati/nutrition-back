package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.MapStruct;
import br.eti.gadelha.nutrition.persistence.model.Role;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestRole;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseRole;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryRole;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class ServiceRole implements ServiceInterface<DTOResponseRole, DTORequestRole, Role> {

    private final RepositoryRole repositoryRole;

    @Override
    public DTOResponseRole create(DTORequestRole created){
        return MapStruct.MAPPER.toDTORole(repositoryRole.save(MapStruct.MAPPER.toRole(created)));
    }
    @Override
    public DTOResponseRole retrieve(UUID id){
        return MapStruct.MAPPER.toDTORole(repositoryRole.findById(id).orElse(null));
    }
    public List<DTOResponseRole> retrieve(List<Role> list){
        List<DTOResponseRole> search = new ArrayList<>();
        list.stream().forEach(value -> search.add(MapStruct.MAPPER.toDTORole(value)));
        return search;
    }
    @Override
    public List<DTOResponseRole> retrieve(){
        return retrieve(repositoryRole.findAll());
    }
    @Override
    public Page<DTOResponseRole> retrieve(Pageable pageable, String value){
        List<DTOResponseRole> list = new ArrayList<>();
        if (value == null) {
            return new PageImpl<>(retrieve(repositoryRole.findAll()), pageable, list.size());
        } else {
            return new PageImpl<>(retrieve(repositoryRole.findByNameContainingIgnoreCaseOrderByNameAsc(value)), pageable, list.size());
        }
    }
    @Override
    public DTOResponseRole update(UUID id, DTORequestRole updated){
        return MapStruct.MAPPER.toDTORole(repositoryRole.save(MapStruct.MAPPER.toRole(updated)));
    }
    @Override
    public DTOResponseRole delete(UUID id){
        repositoryRole.deleteById(id);
        return MapStruct.MAPPER.toDTORole(repositoryRole.findById(id).orElse(null));
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