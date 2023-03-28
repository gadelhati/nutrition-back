package br.eti.gadelha.nutrition.controller;

import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestPrivilege;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponseFood;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponsePrivilege;
import br.eti.gadelha.nutrition.service.ServicePrivilege;
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

@RestController @RequestMapping("/privilege") @RequiredArgsConstructor
public class ControllerPrivilege implements ControllerInterface<DTOResponsePrivilege, DTORequestPrivilege> {

    private final ServicePrivilege servicePrivilege;

    @PostMapping("") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<DTOResponsePrivilege> create(@RequestBody @Valid DTORequestPrivilege created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/privilege").toUriString());
        return ResponseEntity.created(uri).body(servicePrivilege.create(created));
    }
    @GetMapping("/id/{id}") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<DTOResponsePrivilege> retrieve(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(servicePrivilege.retrieve(id));
    }
    @GetMapping(value = {"/{value}", "/", ""}) @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Page<DTOResponsePrivilege>> retrieve(@PathVariable(value = "value", required = false) String value, Pageable pageable) {
        return ResponseEntity.ok().body(servicePrivilege.retrieve(pageable, value));
    }
    @GetMapping(value = {"/{page}/{size}/{sort}", "/{page}/{size}/{sort}/{value}", "/{page}/{size}/{sort}/{value}/{order}"})
    public ResponseEntity<Page<DTOResponsePrivilege>> retrievePage(
            @PathVariable(value = "page", required = false) Integer page,
            @PathVariable(value = "size", required = false) Integer size,
            @PathVariable(value = "sort", required = false) String sort,
            @PathVariable(value = "value", required = false) String value,
            @PathVariable(value = "order", required = false) String order) {
        return ResponseEntity.ok().body(servicePrivilege.retrievePage(page, size, sort, value, order));
    }
    @PutMapping("") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<DTOResponsePrivilege> update(@RequestBody @Valid DTORequestPrivilege updated){
        return ResponseEntity.accepted().body(servicePrivilege.update(updated.getId(), updated));
    }
    @DeleteMapping("{id}") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<DTOResponsePrivilege> delete(@PathVariable("id") UUID id){
        return ResponseEntity.accepted().body(servicePrivilege.delete(id));
    }
    @DeleteMapping("") @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<HttpStatus> delete(){
        try {
            servicePrivilege.delete();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}