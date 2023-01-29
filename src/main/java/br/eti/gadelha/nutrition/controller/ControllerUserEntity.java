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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController @RequestMapping("/user") @RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControllerUserEntity implements ControllerInterface<DTOResponseUserEntity, DTORequestUserEntity> {

    private final ServiceUserEntity serviceUserEntity;

    @PostMapping("")
    public ResponseEntity<DTOResponseUserEntity> create(@RequestBody @Valid DTORequestUserEntity created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/food").toUriString());
        return ResponseEntity.created(uri).body(serviceUserEntity.create(created));
    }
    @GetMapping("/retrieve")
    public List<DTOResponseUserEntity> retrieve(){
        return serviceUserEntity.retrieve();
    }
    @GetMapping("")
    public ResponseEntity<Page<DTOResponseUserEntity>> retrieve(Pageable pageable){
        return ResponseEntity.ok().body(serviceUserEntity.retrieve(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DTOResponseUserEntity> retrieve(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(serviceUserEntity.retrieve(id));
    }
    @GetMapping("/source")
    public ResponseEntity<Page<DTOResponseUserEntity>> retrieve(Pageable pageable, @RequestParam(required = false) String q){
        return ResponseEntity.ok().body(serviceUserEntity.retrieve(pageable, q));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DTOResponseUserEntity> update(@PathVariable("id") UUID id, @RequestBody @Valid DTORequestUserEntity updated){
        return ResponseEntity.accepted().body(serviceUserEntity.update(id, updated));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DTOResponseUserEntity> delete(@PathVariable("id") UUID id){
        return ResponseEntity.accepted().body(serviceUserEntity.delete(id));
    }
    @DeleteMapping("")
    public ResponseEntity<HttpStatus> delete(){
        try {
            serviceUserEntity.delete();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}