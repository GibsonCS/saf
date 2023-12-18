package br.org.sistemafesu.service;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class AbstractService<T, R extends JpaRepository<T, Long>> implements IService<T> {
    protected final R repository;
    protected final T model;

    public AbstractService(T model, R repository) {
        this.repository = repository;
        this.model = model;
    }

    @Override
    public T save(T model) {
        return repository.save(model);
    }

    @Override
    public Iterable<T> getAll() {
        return repository.findAll();
    }

    @Override
    public boolean exists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<T> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
