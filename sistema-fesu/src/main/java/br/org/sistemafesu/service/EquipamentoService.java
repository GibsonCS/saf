package br.org.sistemafesu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.Equipamento;
import br.org.sistemafesu.entity.Locacao;
import br.org.sistemafesu.repository.EquipamentoRepository;
import br.org.sistemafesu.repository.LocacaoRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;

@Service
public class EquipamentoService {

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    public void criarEquipamento(Equipamento equipamento) {
        equipamentoRepository.save(equipamento);
    }

    public void deletarEquipamentoPorId(@NonNull Long id) {
        if (equipamentoRepository.existsById(id)) {
            equipamentoRepository.deleteById(id);
        }
    }

    public List<Equipamento> obterEquipamentos() {
        return equipamentoRepository.findAll();
    }

    @Transactional
    public void deleteLocacao(@NonNull Long id) {
        Locacao locacao = locacaoRepository.findById(id).orElse(null);

        if (locacao != null) {
            for (Equipamento equipamento : locacao.getEquipamentos()) {
                equipamento.setLocacao(null);
                equipamentoRepository.save(equipamento); // Atualize o equipamento para refletir a remoção da referência
                                                         // à locação
            }
            locacaoRepository.delete(locacao);
        }
    }
}
