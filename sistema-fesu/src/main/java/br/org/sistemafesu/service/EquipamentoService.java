package br.org.sistemafesu.service;

import br.org.sistemafesu.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.Equipamento;
import br.org.sistemafesu.entity.Locacao;
import br.org.sistemafesu.repository.EquipamentoRepository;
import br.org.sistemafesu.repository.LocacaoRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;

import java.util.List;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;
    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Equipamento update(@NonNull Long id, @NonNull Equipamento equipamento) {
        if (equipamento.getId() == null || !equipamentoRepository.existsById(id)) {
            throw new IllegalArgumentException("Sala não encontrada!");
        }
        return equipamentoRepository.save(equipamento);
    }

    @Transactional
    public void deleteLocacao(Long id) {
        Locacao locacao = locacaoRepository.findById(id).orElse(null);

        // Atualize o equipamento para refletir a remoção da referênci à locação.
        if (locacao != null) {
            for (Equipamento equipamento : locacao.getEquipamentos()) {
                equipamentoRepository.save(equipamento);
            }
            locacaoRepository.deleteById(id);
        }
    }

    public void deleteById(Long id) {
        equipamentoRepository.deleteById(id);
    }

    public List<Equipamento> getlAll() {

        return equipamentoRepository.findAll();
    }

    public void save(Equipamento equipamento) {
        if (!equipamentoRepository.existsById(equipamento.getId())) {
            equipamentoRepository.save(equipamento);
        }
    }

}
