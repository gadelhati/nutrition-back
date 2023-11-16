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

@RestController @RequestMapping("/composite_unit") @RequiredArgsConstructor
public class ControllerCompositeUnit {

    private final ServiceCompositeUnit serviceCompositeUnit;

    @PostMapping("") @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    public ResponseEntity<DTOResponseCompositeUnit> create(@RequestBody @Valid DTORequestCompositeUnit created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/composite_unit").toUriString());
        return ResponseEntity.created(uri).body(serviceCompositeUnit.create(created));
    }
    @GetMapping("/composite")
    public ResponseEntity<Page<DTOResponseCompositeUnit>> retrieve(
            @RequestParam(name = "key", defaultValue = "", required = false) String key,
            @RequestParam(name="value", defaultValue = "", required = false) String value,
            Pageable pageable,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "number", required = false) int number){
        return ResponseEntity.ok().body(serviceCompositeUnit.retrieveComposite(pageable, key, value, name, number));
    }
    @GetMapping("")
    public ResponseEntity<Page<DTOResponseCompositeUnit>> retrieve(@RequestParam(name = "key", defaultValue = "", required = false) String key, @RequestParam(name="value", defaultValue = "", required = false) String value, Pageable pageable){
        return ResponseEntity.ok().body(serviceCompositeUnit.retrieve(pageable, key, value));
    }
    @PutMapping("") @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
    public ResponseEntity<DTOResponseCompositeUnit> update(@RequestBody @Valid DTORequestCompositeUnit updated){
        return ResponseEntity.accepted().body(serviceCompositeUnit.update(new CompositePK(updated.getName(), updated.getNumber()), updated));
    }
    @DeleteMapping("/{name}/{number}") @PreAuthorize("hasAnyRole('MODERATOR', 'ADMIN')")
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