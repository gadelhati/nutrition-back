package br.eti.gadelha.nutrition.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ServiceInterface<T, S, R> {
    T create(S created);
    Page<T> retrieve(Pageable pageable, String filter);
    T update(UUID id, S updated);
    T delete(UUID id);
    void delete();
}