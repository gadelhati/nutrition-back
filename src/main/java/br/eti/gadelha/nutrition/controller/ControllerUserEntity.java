package br.eti.gadelha.nutrition.controller;

import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestUserEntity;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseUserEntity;
import br.eti.gadelha.nutrition.service.ServiceUserEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController @RequestMapping("/user_entity") @RequiredArgsConstructor
public class ControllerUserEntity implements ControllerInterface<DTOResponseUserEntity, DTORequestUserEntity> {

    private final ServiceUserEntity serviceUserEntity;

    @PostMapping("") @Override @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    public ResponseEntity<DTOResponseUserEntity> create(@RequestBody @Valid DTORequestUserEntity created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user_entity").toUriString());
        return ResponseEntity.created(uri).body(serviceUserEntity.create(created));
    }
    @GetMapping("") @Override @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    public ResponseEntity<Page<DTOResponseUserEntity>> retrieve(@RequestParam(name = "key", defaultValue = "", required = false) String key, @RequestParam(name="value", defaultValue = "", required = false) String value, Pageable pageable){
        return ResponseEntity.ok().body(serviceUserEntity.retrieve(pageable, key, value));
    }
    @PutMapping("") @Override @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    public ResponseEntity<DTOResponseUserEntity> update(@RequestBody @Valid DTORequestUserEntity updated){
        return ResponseEntity.accepted().body(serviceUserEntity.update(updated.getId(), updated));
    }
    @DeleteMapping("/{id}") @Override @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    public ResponseEntity<DTOResponseUserEntity> delete(@PathVariable("id") UUID id){
        return ResponseEntity.accepted().body(serviceUserEntity.delete(id));
    }
    @DeleteMapping("") @Override @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<HttpStatus> delete(){
        try {
            serviceUserEntity.delete();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/changePassword")
    public ResponseEntity<DTOResponseUserEntity> changePassword(@RequestBody @Valid DTORequestUserEntity updated){
        try {
            return new ResponseEntity<>(serviceUserEntity.changePassword(updated), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}