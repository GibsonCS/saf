package br.org.sistemafesu.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.Sala;
import br.org.sistemafesu.repository.SalaRepository;
import lombok.NonNull;

@Service
public class SalaService {
    //
    @Autowired
    private SalaRepository salaRepository;


    public Sala update(@NonNull Long id, Sala sala) {
        if (sala.getId() == null || !salaRepository.existsById(id)) {
            throw new IllegalArgumentException("Sala não encontrada!");
        }

        return salaRepository.save(sala);
    }

    //
    public List<Sala> getSortedSalas() {
        List<Sala> salas = salaRepository.findAll();

        return salas.stream()
                .sorted(Comparator.comparing(Sala::getNomeSala))
                .collect(Collectors.toList());
    }

    public void save(Sala sala){
        salaRepository.save(sala);
    }

    public void deleteById(Long id){
        if (salaRepository.existsById(id)){
            salaRepository.deleteById(id);
        }
    }
}
