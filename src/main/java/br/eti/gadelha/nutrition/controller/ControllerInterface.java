package br.eti.gadelha.nutrition.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

public interface ControllerInterface<T, S> {
    ResponseEntity<T> create(@RequestBody @Valid S created);
    List<T> retrieve();
    ResponseEntity<Page<T>> retrieve(Pageable pageable);
    ResponseEntity<T> retrieve(@PathVariable("id") UUID id);
    ResponseEntity<Page<T>> retrieve(@PathVariable("value") String value, Pageable pageable);
    ResponseEntity<T> update(@PathVariable("id") UUID id, @RequestBody @Valid S updated);
    ResponseEntity<T> delete(@PathVariable("id") UUID id);
    ResponseEntity<HttpStatus> delete();
}