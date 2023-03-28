package br.eti.gadelha.nutrition.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ServiceInterface<T, S, R> {
    T create(S created);
    T retrieve(UUID id);
    List<T> retrieve();
    Page<T> retrieve(Pageable pageable, String source);
    Page<T> retrievePage(Integer page, Integer size, String sort, String value, String order);
    T update(UUID id, S updated);
    T delete(UUID id);
    void delete();
}