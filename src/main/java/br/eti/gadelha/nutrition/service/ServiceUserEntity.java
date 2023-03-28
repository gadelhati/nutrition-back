package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.MapStruct;
import br.eti.gadelha.nutrition.persistence.model.UserEntity;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestUserEntity;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseUserEntity;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryRole;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryRolePage;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryUserEntity;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryUserEntityPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class ServiceUserEntity implements ServiceInterface<DTOResponseUserEntity, DTORequestUserEntity, UserEntity> {

    private final RepositoryUserEntity repositoryUserEntity;
    private final RepositoryRole repositoryRole;
    private final RepositoryUserEntityPage repositoryUserEntityPage;
    private final PasswordEncoder passwordEncoder;

    @Override
    public DTOResponseUserEntity create(DTORequestUserEntity created){
        created.setPassword(passwordEncoder.encode(created.getPassword()));
        created.setRoles(Arrays.asList(repositoryRole.findByName("ROLE_USER")));
        return MapStruct.MAPPER.toDTO(repositoryUserEntity.save(MapStruct.MAPPER.toObject(created)));
    }
    @Override
    public DTOResponseUserEntity retrieve(UUID id){
        return MapStruct.MAPPER.toDTO(repositoryUserEntity.findById(id).orElseGet(null));
    }
    public List<DTOResponseUserEntity> retrieve(List<UserEntity> list){
        return list.stream().map(value -> MapStruct.MAPPER.toDTO(value)).collect(Collectors.toList());
    }
    @Override
    public List<DTOResponseUserEntity> retrieve(){
        return retrieve(repositoryUserEntity.findAll());
    }
    @Override
    public Page<DTOResponseUserEntity> retrieve(Pageable pageable, String value){
        List<DTOResponseUserEntity> list = new ArrayList<>();
        if (value == null) {
            return new PageImpl<>(retrieve(repositoryUserEntity.findAll()), pageable, list.size());
        } else {
            return new PageImpl<>(retrieve(repositoryUserEntity.findByUsernameContainingIgnoreCaseOrderByUsernameAsc(value)), pageable, list.size());
        }
    }
    @Override
    public Page<DTOResponseUserEntity> retrievePage(Integer page, Integer size, String sort, String value, String order){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
//        if(order != null && order.equals("asc")) pageable = PageRequest.of(page, size, Sort.by(sort).ascending());
        if (value == null) {
            return repositoryUserEntityPage.findAll(pageable).map(object -> MapStruct.MAPPER.toDTO(object));
        } else {
            return repositoryUserEntityPage.findByUsernameContainingIgnoreCaseOrderByUsernameAsc(pageable, value).map(object -> MapStruct.MAPPER.toDTO(object));
        }
    }
    @Override
    public DTOResponseUserEntity update(UUID id, DTORequestUserEntity updated){
        return MapStruct.MAPPER.toDTO(repositoryUserEntity.save(MapStruct.MAPPER.toObject(updated)));
    }
    @Override
    public DTOResponseUserEntity delete(UUID id){
        repositoryUserEntity.deleteById(id);
        return MapStruct.MAPPER.toDTO(repositoryUserEntity.findById(id).orElse(null));
    }
    @Override
    public void delete() {
        repositoryUserEntity.deleteAll();
    }

    public boolean existsByName(String value) {
        return repositoryUserEntity.existsByUsernameIgnoreCase(value);
    }
    public boolean existsByNameAndIdNot(String value, UUID id) {
        return repositoryUserEntity.existsByUsernameIgnoreCaseAndIdNot(value, id);
    }
    public boolean existsByEmail(String value) {
        return repositoryUserEntity.existsByEmailIgnoreCase(value);
    }
    public boolean existsByEmailAndIdNot(String value, UUID id) {
        return repositoryUserEntity.existsByEmailIgnoreCaseAndIdNot(value, id);
    }
}