package br.org.sistemafesu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lombok.NonNull;

public abstract class AbstractService<T, R extends JpaRepository<T, Long>> implements IService<T> {
    protected final R repository;

    public AbstractService(R repository) {
        this.repository = repository;
    }

    @Override
    public T save(@NonNull T model) {
        return repository.save(model);
    }

    @Override
    public List<T> getAll() {
        return (List<T>) repository.findAll();
    }

    @Override
    public boolean exists(@NonNull Long id) {
        return repository.existsById(id);
    }

    @Override
    public Optional<T> getById(@NonNull Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(@NonNull Long id) {
        repository.deleteById(id);
    }
}