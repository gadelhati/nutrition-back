package br.eti.gadelha.nutrition.controller;

import br.eti.gadelha.nutrition.persistence.payload.request.DTORequestPrivilege;
import br.eti.gadelha.nutrition.persistence.payload.response.DTOResponsePrivilege;
import br.eti.gadelha.nutrition.service.ServicePrivilege;
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

@RestController @RequestMapping("/privilege/") @RequiredArgsConstructor
public class ControllerPrivilege implements ControllerInterface<DTOResponsePrivilege, DTORequestPrivilege> {

    private final ServicePrivilege servicePrivilege;

    @PostMapping("")
    public ResponseEntity<DTOResponsePrivilege> create(@RequestBody @Valid DTORequestPrivilege created){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/privilege").toUriString());
        return ResponseEntity.created(uri).body(servicePrivilege.create(created));
    }
    @GetMapping("retrieve")
    public List<DTOResponsePrivilege> retrieve(){
        return servicePrivilege.retrieve();
    }
    @GetMapping("id/{id}")
    public ResponseEntity<DTOResponsePrivilege> retrieve(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(servicePrivilege.retrieve(id));
    }
    @GetMapping(value = {"{value}", ""})
    public ResponseEntity<Page<DTOResponsePrivilege>> retrieve(@PathVariable(value = "value", required = false) String value, Pageable pageable) {
        return ResponseEntity.ok().body(servicePrivilege.retrieve(pageable, value));
    }
    @PutMapping("")
    public ResponseEntity<DTOResponsePrivilege> update(@RequestBody @Valid DTORequestPrivilege updated){
        return ResponseEntity.accepted().body(servicePrivilege.update(updated.getId(), updated));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<DTOResponsePrivilege> delete(@PathVariable("id") UUID id){
        return ResponseEntity.accepted().body(servicePrivilege.delete(id));
    }
    @DeleteMapping("")
    public ResponseEntity<HttpStatus> delete(){
        try {
            servicePrivilege.delete();
            return new ResponseEntity<>(null, HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(HttpStatus.BAD_REQUEST);
        }
    }
}