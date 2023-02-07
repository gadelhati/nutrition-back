package br.eti.gadelha.nutrition.service;

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

    public DTOResponsePrivilege create(DTORequestPrivilege created){
        return DTOResponsePrivilege.toDTO(repositoryPrivilege.save(created.toObject()));
    }
    public DTOResponsePrivilege retrieve(UUID id){
        return DTOResponsePrivilege.toDTO(repositoryPrivilege.findById(id).orElse(null));
    }
    public List<DTOResponsePrivilege> retrieve(){
        List<DTOResponsePrivilege> list = new ArrayList<>();
        for(Privilege object: repositoryPrivilege.findAll()) {
            list.add(DTOResponsePrivilege.toDTO(object));
        }
        return list;
    }
    public Page<DTOResponsePrivilege> retrieve(Pageable pageable){
        List<DTOResponsePrivilege> list = new ArrayList<>();
        for(Privilege object: repositoryPrivilege.findAll()) {
            list.add(DTOResponsePrivilege.toDTO(object));
        }
        return new PageImpl<>(list, pageable, list.size());
    }
    public Page<DTOResponsePrivilege> retrieve(Pageable pageable, String value){
        final List<DTOResponsePrivilege> list = new ArrayList<>();
        if (value == null) {
            return retrieve(pageable);
        } else {
            for (Privilege object : repositoryPrivilege.findByNameContainingIgnoreCaseOrderByNameAsc(value)) {
                list.add(DTOResponsePrivilege.toDTO(object));
            }
        }
        return new PageImpl<>(list, pageable, list.size());
    }
    public DTOResponsePrivilege update(UUID id, DTORequestPrivilege updated){
        Privilege object = repositoryPrivilege.findById(id).orElse(null);
        object.setName(updated.getName());
        return DTOResponsePrivilege.toDTO(repositoryPrivilege.save(object));
    }
    public DTOResponsePrivilege delete(UUID id){
        Privilege object = repositoryPrivilege.findById(id).orElse(null);
        repositoryPrivilege.deleteById(id);
        return DTOResponsePrivilege.toDTO(object);
    }
    public void delete() {
        repositoryPrivilege.deleteAll();
    }
    public Privilege findByName(String value) { return  repositoryPrivilege.findByName(value); }
    public boolean existsByName(String value) {
        return repositoryPrivilege.existsByNameContainingIgnoreCase(value);
    }

    public boolean existsByNameAndIdNot(String value, UUID id) {
        return !repositoryPrivilege.findByNameContaining(value).and(repositoryPrivilege.findByIdNot(id)).isEmpty();
    }
}