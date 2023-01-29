package br.eti.gadelha.nutrition.controller;

import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestFood;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseFood;
import br.eti.gadelha.nutrition.service.ServiceFood;
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

@RestController @RequestMapping("/food") @RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class ControllerFood implements ControllerInterface<DTOResponseFood, DTORequestFood> {

    private final ServiceFood serviceFood;

    @PostMapping("")
    public ResponseEntity<DTOResponseFood> create(@RequestBody @Valid DTORequestFood created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/food").toUriString());
        return ResponseEntity.created(uri).body(serviceFood.create(created));
    }
    @GetMapping("/retrieve")
    public List<DTOResponseFood> retrieve(){
        return serviceFood.retrieve();
    }
    @GetMapping("")
    public ResponseEntity<Page<DTOResponseFood>> retrieve(Pageable pageable){
        return ResponseEntity.ok().body(serviceFood.retrieve(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DTOResponseFood> retrieve(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(serviceFood.retrieve(id));
    }
    @GetMapping("/source")
    public ResponseEntity<Page<DTOResponseFood>> retrieve(Pageable pageable, @RequestParam(required = false) String q){
        return ResponseEntity.ok().body(serviceFood.retrieve(pageable, q));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DTOResponseFood> update(@PathVariable("id") UUID id, @RequestBody @Valid DTORequestFood updated){
        return ResponseEntity.accepted().body(serviceFood.update(id, updated));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DTOResponseFood> delete(@PathVariable("id") UUID id){
        return ResponseEntity.accepted().body(serviceFood.delete(id));
    }
    @DeleteMapping("")
    public ResponseEntity<HttpStatus> delete(){
        try {
            serviceFood.delete();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}