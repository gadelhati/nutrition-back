package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.MapStruct;
import br.eti.gadelha.nutrition.persistence.model.Privilege;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestPrivilege;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponsePrivilege;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseUserEntity;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryPrivilege;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryPrivilegePage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ServicePrivilege implements ServiceInterface<DTOResponsePrivilege, DTORequestPrivilege, Privilege> {

    private final RepositoryPrivilege repositoryPrivilege;
    private final RepositoryPrivilegePage repositoryPrivilegePage;

    @Override
    public DTOResponsePrivilege create(DTORequestPrivilege created){
        return MapStruct.MAPPER.toDTO(repositoryPrivilege.save(MapStruct.MAPPER.toObject(created)));
    }
    @Override
    public DTOResponsePrivilege retrieve(UUID id){
        return MapStruct.MAPPER.toDTO(repositoryPrivilege.findById(id).orElseGet(null));
    }
    public List<DTOResponsePrivilege> retrieve(List<Privilege> list){
        return list.stream().map(value -> MapStruct.MAPPER.toDTO(value)).collect(Collectors.toList());
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
    public Page<DTOResponsePrivilege> retrievePage(Integer page, Integer size, String sort, String value, String order){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
//        if(order != null && order.equals("asc")) pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        if (value == null) {
            return repositoryPrivilegePage.findAll(pageable).map(object -> MapStruct.MAPPER.toDTO(object));
        } else {
            return repositoryPrivilegePage.findByNameContainingIgnoreCaseOrderByNameAsc(pageable, value).map(object -> MapStruct.MAPPER.toDTO(object));
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