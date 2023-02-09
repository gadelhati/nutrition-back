package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.MapStruct;
import br.eti.gadelha.nutrition.persistence.model.Privilege;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestPrivilege;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponsePrivilege;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryPrivilege;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class ServicePrivilege implements ServiceInterface<DTOResponsePrivilege, DTORequestPrivilege, Privilege> {

    private final RepositoryPrivilege repositoryPrivilege;

    @Override
    public DTOResponsePrivilege create(DTORequestPrivilege created){
        return MapStruct.MAPPER.toDTOPrivilege(repositoryPrivilege.save(MapStruct.MAPPER.toPrivilege(created)));
    }
    @Override
    public DTOResponsePrivilege retrieve(UUID id){
        return MapStruct.MAPPER.toDTOPrivilege(repositoryPrivilege.findById(id).orElse(null));
    }
    public List<DTOResponsePrivilege> retrieve(List<Privilege> list){
        List<DTOResponsePrivilege> search = new ArrayList<>();
        list.stream().forEach(value -> search.add(MapStruct.MAPPER.toDTOPrivilege(value)));
        return search;
    }
    @Override
    public List<DTOResponsePrivilege> retrieve(){
        return retrieve(repositoryPrivilege.findAll());
    }
    @Override
    public Page<DTOResponsePrivilege> retrieve(Pageable pageable, String value){
        List<DTOResponsePrivilege> list = new ArrayList<>();
        if (value == null) {
            return new PageImpl<>(retrieve(repositoryPrivilege.findAll()), pageable, list.size());
        } else {
            return new PageImpl<>(retrieve(repositoryPrivilege.findByNameContainingIgnoreCaseOrderByNameAsc(value)), pageable, list.size());
        }
    }
    @Override
    public DTOResponsePrivilege update(UUID id, DTORequestPrivilege updated){
        return MapStruct.MAPPER.toDTOPrivilege(repositoryPrivilege.save(MapStruct.MAPPER.toPrivilege(updated)));
    }
    @Override
    public DTOResponsePrivilege delete(UUID id){
        repositoryPrivilege.deleteById(id);
        return MapStruct.MAPPER.toDTOPrivilege(repositoryPrivilege.findById(id).orElse(null));
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