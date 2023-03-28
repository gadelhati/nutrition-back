package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.MapStruct;
import br.eti.gadelha.nutrition.persistence.model.Role;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestRole;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseFood;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseRole;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryRole;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryRolePage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ServiceRole implements ServiceInterface<DTOResponseRole, DTORequestRole, Role> {

    private final RepositoryRole repositoryRole;
    private final RepositoryRolePage repositoryRolePage;

    @Override
    public DTOResponseRole create(DTORequestRole created){
        return MapStruct.MAPPER.toDTO(repositoryRole.save(MapStruct.MAPPER.toObject(created)));
    }
    @Override
    public DTOResponseRole retrieve(UUID id){
        return MapStruct.MAPPER.toDTO(repositoryRole.findById(id).orElseGet(null));
    }
    public List<DTOResponseRole> retrieve(List<Role> list){
        return list.stream().map(value -> MapStruct.MAPPER.toDTO(value)).collect(Collectors.toList());
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
    public Page<DTOResponseRole> retrievePage(Integer page, Integer size, String sort, String value, String order){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
//        if(order != null && order.equals("asc")) pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        if (value == null) {
            return repositoryRolePage.findAll(pageable).map(object -> MapStruct.MAPPER.toDTO(object));
        } else {
            return repositoryRolePage.findByNameContainingIgnoreCaseOrderByNameAsc(pageable, value).map(object -> MapStruct.MAPPER.toDTO(object));
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