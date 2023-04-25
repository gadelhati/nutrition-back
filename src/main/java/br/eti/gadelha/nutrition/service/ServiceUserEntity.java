package br.eti.gadelha.nutrition.service;

import br.eti.gadelha.nutrition.persistence.MapStruct;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestUserEntity;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseUserEntity;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryRole;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryUserEntity;
import br.eti.gadelha.nutrition.persistence.repository.RepositoryUserEntityPage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.UUID;

@Service @RequiredArgsConstructor
public class ServiceUserEntity implements ServiceInterface<DTOResponseUserEntity, DTORequestUserEntity> {

    private final RepositoryUserEntity repositoryUserEntity;
    private final RepositoryRole repositoryRole;
    private final RepositoryUserEntityPage repositoryUserEntityPage;
    private final PasswordEncoder passwordEncoder;

    @Override
    public DTOResponseUserEntity create(DTORequestUserEntity created){
        created.setPassword(passwordEncoder.encode(created.getPassword()));
        created.setRoles(Collections.singletonList(repositoryRole.findByName("ROLE_USER")));
        return MapStruct.MAPPER.toDTO(repositoryUserEntity.save(MapStruct.MAPPER.toObject(created)));
    }
    @Override
    public Page<DTOResponseUserEntity> retrieve(Pageable pageable, String filter) {
        switch (pageable.getSort().toString().substring(0, pageable.getSort().toString().length() - 5)) {
            case "id": {
                return repositoryUserEntityPage.findByIdOrderByIdAsc(pageable, UUID.fromString(filter)).map(MapStruct.MAPPER::toDTO);
            }
            case "username": {
                return repositoryUserEntityPage.findByUsernameContainingIgnoreCaseOrderByUsernameAsc(pageable, filter).map(MapStruct.MAPPER::toDTO);
            }
            default: {
                return repositoryUserEntityPage.findAll(pageable).map(MapStruct.MAPPER::toDTO);
            }
        }
    }
    @Override
    public DTOResponseUserEntity update(UUID id, DTORequestUserEntity updated){
        return MapStruct.MAPPER.toDTO(repositoryUserEntity.save(MapStruct.MAPPER.toObject(updated)));
    }
    @Override
    public DTOResponseUserEntity delete(UUID id){
        DTOResponseUserEntity dtoResponseUserEntity = MapStruct.MAPPER.toDTO(repositoryUserEntity.findById(id).orElse(null));
        repositoryUserEntity.deleteById(id);
        return dtoResponseUserEntity;
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