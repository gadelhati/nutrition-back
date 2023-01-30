package br.eti.gadelha.nutrition.controller;

import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestRole;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseRole;
import br.eti.gadelha.nutrition.service.ServiceRole;
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

@RestController @RequestMapping("/role") @RequiredArgsConstructor
public class ControllerRole implements ControllerInterface<DTOResponseRole, DTORequestRole> {

    private final ServiceRole serviceRole;

    @PostMapping("")
    public ResponseEntity<DTOResponseRole> create(@RequestBody @Valid DTORequestRole created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/food").toUriString());
        return ResponseEntity.created(uri).body(serviceRole.create(created));
    }
    @GetMapping("/retrieve")
    public List<DTOResponseRole> retrieve(){
        return serviceRole.retrieve();
    }
    @GetMapping("")
    public ResponseEntity<Page<DTOResponseRole>> retrieve(Pageable pageable){
        return ResponseEntity.ok().body(serviceRole.retrieve(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DTOResponseRole> retrieve(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(serviceRole.retrieve(id));
    }
    @GetMapping("/source")
    public ResponseEntity<Page<DTOResponseRole>> retrieve(Pageable pageable, @RequestParam(required = false) String q){
        return ResponseEntity.ok().body(serviceRole.retrieve(pageable, q));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DTOResponseRole> update(@PathVariable("id") UUID id, @RequestBody @Valid DTORequestRole updated){
        return ResponseEntity.accepted().body(serviceRole.update(id, updated));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DTOResponseRole> delete(@PathVariable("id") UUID id){
        return ResponseEntity.accepted().body(serviceRole.delete(id));
    }
    @DeleteMapping("")
    public ResponseEntity<HttpStatus> delete(){
        try {
            serviceRole.delete();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}