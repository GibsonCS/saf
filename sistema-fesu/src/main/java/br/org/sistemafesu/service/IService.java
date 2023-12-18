package br.org.sistemafesu.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface IService<T> {
    T save(T model);
    boolean exists(Long id);
    Optional<T> getById(Long id);
    Iterable<T> getAll();
    T update(Long id, T model);
    void deleteById(Long id);
}
