package br.org.sistemafesu.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.Sala;
import br.org.sistemafesu.repository.SalaRepository;
import lombok.NonNull;

@Service
public class SalaService extends AbstractService<Sala, SalaRepository> {
    public SalaService(SalaRepository repository) {
        super(repository);
    }

    @Override
    public Sala update(@NonNull Long id, Sala model) {
        if (model.getId() == null || !repository.existsById(id)) {
            throw new IllegalArgumentException("Sala não encontrada!");
        }

        return repository.save(model);
    }

    public List<Sala> getSortedSalas() {
        List<Sala> salas = repository.findAll();

        return salas.stream()
                .sorted(Comparator.comparing(Sala::getNomeSala))
                .collect(Collectors.toList());
    }
}
