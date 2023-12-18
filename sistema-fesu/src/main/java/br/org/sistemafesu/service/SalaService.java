package br.org.sistemafesu.service;

import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.Sala;
import br.org.sistemafesu.repository.SalaRepository;

@Service
public class SalaService extends AbstractService<Sala, SalaRepository> {
    public SalaService(Sala sala, SalaRepository repository) {
        super(sala, repository);
    }

    @Override
    public Sala update(Long id, Sala model) {
        if (model.getId() == null || !repository.existsById(id)) {
            throw new IllegalArgumentException("Sala não encontrada!");
        }

        return repository.save(model);
    }
}
