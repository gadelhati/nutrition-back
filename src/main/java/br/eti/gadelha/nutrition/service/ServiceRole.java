package br.eti.gadelha.nutrition.service;

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

    public DTOResponseRole create(DTORequestRole created){
        return DTOResponseRole.toDTO(repositoryRole.save(created.toObject()));
    }
    public DTOResponseRole retrieve(UUID id){
        return DTOResponseRole.toDTO(repositoryRole.findById(id).orElse(null));
    }
    public List<DTOResponseRole> retrieve(){
        List<DTOResponseRole> list = new ArrayList<>();
        for(Role object: repositoryRole.findAll()) {
            list.add(DTOResponseRole.toDTO(object));
        }
        return list;
    }
    public Page<DTOResponseRole> retrieve(Pageable pageable){
        List<DTOResponseRole> list = new ArrayList<>();
        for(Role object: repositoryRole.findAll()) {
            list.add(DTOResponseRole.toDTO(object));
        }
        return new PageImpl<>(list, pageable, list.size());
    }
    public Page<DTOResponseRole> retrieve(Pageable pageable, String value){
        final List<DTOResponseRole> list = new ArrayList<>();
        if (value == null) {
            return retrieve(pageable);
        } else {
            for (Role object : repositoryRole.findByNameContainingIgnoreCaseOrderByNameAsc(value)) {
                list.add(DTOResponseRole.toDTO(object));
            }
        }
        return new PageImpl<>(list, pageable, list.size());
    }
    public DTOResponseRole update(UUID id, DTORequestRole updated){
        Role object = repositoryRole.findById(id).orElse(null);
        object.setName(updated.getName());
        return DTOResponseRole.toDTO(repositoryRole.save(object));
    }
    public DTOResponseRole delete(UUID id){
        Role object = repositoryRole.findById(id).orElse(null);
        repositoryRole.deleteById(id);
        return DTOResponseRole.toDTO(object);
    }
    public void delete() {
        repositoryRole.deleteAll();
    }
    public Role findByName(String value) { return  repositoryRole.findByName(value); }
    public boolean existsByName(String value) {
        return repositoryRole.existsByNameContainingIgnoreCase(value);
    }

    public boolean existsByNameAndIdNot(String value, UUID id) {
        return !repositoryRole.findByNameContaining(value).and(repositoryRole.findByIdNot(id)).isEmpty();
    }
}