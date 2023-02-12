package br.eti.gadelha.nutrition.controller;

import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestFoodCategory;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseFoodCategory;
import br.eti.gadelha.nutrition.service.ServiceFoodCategory;
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

@RestController @RequestMapping("/food_category") @RequiredArgsConstructor
public class ControllerFoodCategory implements ControllerInterface<DTOResponseFoodCategory, DTORequestFoodCategory> {

    private final ServiceFoodCategory serviceFoodCategory;

    @PostMapping("")
    public ResponseEntity<DTOResponseFoodCategory> create(@RequestBody @Valid DTORequestFoodCategory created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/food_category").toUriString());
        return ResponseEntity.created(uri).body(serviceFoodCategory.create(created));
    }
    @GetMapping("id/{id}")
    public ResponseEntity<DTOResponseFoodCategory> retrieve(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(serviceFoodCategory.retrieve(id));
    }
    @GetMapping(value = {"/{value}", ""})
    public ResponseEntity<Page<DTOResponseFoodCategory>> retrieve(@PathVariable(value = "value", required = false) String value, Pageable pageable) {
        return ResponseEntity.ok().body(serviceFoodCategory.retrieve(pageable, value));
    }
    @PutMapping("")
    public ResponseEntity<DTOResponseFoodCategory> update(@RequestBody @Valid DTORequestFoodCategory updated){
        return ResponseEntity.accepted().body(serviceFoodCategory.update(updated.getId(), updated));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<DTOResponseFoodCategory> delete(@PathVariable("id") UUID id){
        return ResponseEntity.accepted().body(serviceFoodCategory.delete(id));
    }
    @DeleteMapping("")
    public ResponseEntity<HttpStatus> delete(){
        try {
            serviceFoodCategory.delete();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}