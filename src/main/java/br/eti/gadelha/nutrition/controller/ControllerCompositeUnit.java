package br.eti.gadelha.nutrition.controller;

import br.eti.gadelha.nutrition.persistence.model.CompositePK;
import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestCompositeUnit;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseCompositeUnit;
import br.eti.gadelha.nutrition.service.ServiceCompositeUnit;
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

@RestController @RequestMapping("/compositeunit") @RequiredArgsConstructor
public class ControllerCompositeUnit {

    private final ServiceCompositeUnit serviceCompositeUnit;

    @PostMapping("") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<DTOResponseCompositeUnit> create(@RequestBody @Valid DTORequestCompositeUnit created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role").toUriString());
        return ResponseEntity.created(uri).body(serviceCompositeUnit.create(created));
    }
    @GetMapping("/{name}/{number}") @PreAuthorize("hasAnyRole('ADMIN')")
//    public ResponseEntity<DTOResponseCompositeUnit> retrieve(@PathVariable("id") CompositePK id){
    public ResponseEntity<DTOResponseCompositeUnit> retrieve(@PathVariable("name") String name, @PathVariable("number") int number){
        return ResponseEntity.ok().body(serviceCompositeUnit.retrieve(new CompositePK(name, number)));
    }
    @GetMapping(value = {"/{value}", "/"}) @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Page<DTOResponseCompositeUnit>> retrieve(@PathVariable(value = "value", required = false) String value, Pageable pageable) {
        return ResponseEntity.ok().body(serviceCompositeUnit.retrieve(pageable, value));
    }
    @PutMapping("") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<DTOResponseCompositeUnit> update(@RequestBody @Valid DTORequestCompositeUnit updated){
        return ResponseEntity.accepted().body(serviceCompositeUnit.update(new CompositePK(updated.getName(), updated.getNumber()), updated));
    }
    @DeleteMapping("/{name}/{number}") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<DTOResponseCompositeUnit> delete(@PathVariable("name") String name, @PathVariable("number") int number){
        return ResponseEntity.accepted().body(serviceCompositeUnit.delete(new CompositePK(name, number)));
    }
    @DeleteMapping("") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<HttpStatus> delete(){
        try {
            serviceCompositeUnit.delete();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}