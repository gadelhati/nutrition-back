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
import java.util.UUID;

@RestController @RequestMapping("/food") @RequiredArgsConstructor
public class ControllerFood implements ControllerInterface<DTOResponseFood, DTORequestFood> {

    private final ServiceFood serviceFood;

    @PostMapping("")
    public ResponseEntity<DTOResponseFood> create(@RequestBody @Valid DTORequestFood created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/food").toUriString());
        return ResponseEntity.created(uri).body(serviceFood.create(created));
    }
    @GetMapping("id/{id}")
    public ResponseEntity<DTOResponseFood> retrieve(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(serviceFood.retrieve(id));
    }
    @GetMapping(value = {"/{value}", "/"})
    public ResponseEntity<Page<DTOResponseFood>> retrieve(@PathVariable(value = "value", required = false) String value, Pageable pageable) {
        return ResponseEntity.ok().body(serviceFood.retrieve(pageable, value));
    }
    @PutMapping("")
    public ResponseEntity<DTOResponseFood> update(@RequestBody @Valid DTORequestFood updated){
        return ResponseEntity.accepted().body(serviceFood.update(updated.getId(), updated));
    }
    @DeleteMapping("{id}")
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