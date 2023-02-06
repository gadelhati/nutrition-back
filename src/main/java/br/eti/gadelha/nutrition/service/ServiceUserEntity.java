package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.model.UserEntity;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestUserEntity;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseUserEntity;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryUserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class ServiceUserEntity implements ServiceInterface<DTOResponseUserEntity, DTORequestUserEntity, Optional<UserEntity>> {

    private final RepositoryUserEntity repositoryUserEntity;

    public DTOResponseUserEntity create(DTORequestUserEntity created){
        return DTOResponseUserEntity.toDTO(repositoryUserEntity.save(created.toObject()));
    }
    public DTOResponseUserEntity retrieve(UUID id){
        return DTOResponseUserEntity.toDTO(repositoryUserEntity.findById(id).orElse(null));
    }
    public List<DTOResponseUserEntity> retrieve(){
        List<DTOResponseUserEntity> list = new ArrayList<>();
        for(UserEntity object: repositoryUserEntity.findAll()) {
            list.add(DTOResponseUserEntity.toDTO(object));
        }
        return list;
    }
    public Page<DTOResponseUserEntity> retrieve(Pageable pageable){
        List<DTOResponseUserEntity> list = new ArrayList<>();
        for(UserEntity object: repositoryUserEntity.findAll()) {
            list.add(DTOResponseUserEntity.toDTO(object));
        }
        return new PageImpl<>(list, pageable, list.size());
    }
    public Page<DTOResponseUserEntity> retrieve(Pageable pageable, String value){
        final List<DTOResponseUserEntity> list = new ArrayList<>();
        if (value == null) {
            return retrieve(pageable);
        } else {
            for (UserEntity object : repositoryUserEntity.findByUsernameContainingIgnoreCaseOrderByUsernameAsc(value)) {
                list.add(DTOResponseUserEntity.toDTO(object));
            }
        }
        return new PageImpl<>(list, pageable, list.size());
    }
    public DTOResponseUserEntity update(UUID id, DTORequestUserEntity updated){
        UserEntity object = repositoryUserEntity.findById(id).orElse(null);
        object.setUsername(updated.getUsername());
        object.setEmail(updated.getEmail());
        object.setPassword(updated.getPassword());
        object.setActive(updated.isActive());
        return DTOResponseUserEntity.toDTO(repositoryUserEntity.save(object));
    }
    public DTOResponseUserEntity delete(UUID id){
        UserEntity object = repositoryUserEntity.findById(id).orElse(null);
        repositoryUserEntity.deleteById(id);
        return DTOResponseUserEntity.toDTO(object);
    }
    public void delete() {
        repositoryUserEntity.deleteAll();
    }
    public Optional<UserEntity> findByName(String value) { return  repositoryUserEntity.findByUsername(value); }
    public boolean existsByName(String value) {
        return repositoryUserEntity.existsByUsernameContainingIgnoreCase(value);
    }

    public boolean existsByNameAndIdNot(String value, UUID id) {
        return !repositoryUserEntity.findByUsernameContaining(value).and(repositoryUserEntity.findByIdNot(id)).isEmpty();
    }
}