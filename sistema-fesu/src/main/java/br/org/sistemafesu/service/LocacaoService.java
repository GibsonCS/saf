package br.org.sistemafesu.service;

import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.Locacao;
import br.org.sistemafesu.repository.EquipamentoRepository;
import br.org.sistemafesu.repository.LocacaoRepository;

@Service
public class LocacaoService extends AbstractService<Locacao, LocacaoRepository> {
    private final EquipamentoRepository equipamentoRepository;

    public LocacaoService(Locacao locacao, LocacaoRepository locacaoRepository, EquipamentoRepository equipamentoRepository) {
        super(locacao, locacaoRepository);

        this.equipamentoRepository = equipamentoRepository;
    }

    @Override
    public Locacao update(Long id, Locacao model) {
        if (model.getId() == null || !repository.existsById(id)) {
            throw new IllegalArgumentException("Locação não encontrada!");
        }

        return super.save(model);
    }

    public void deleteWithTreatment(Long id) {
        Locacao locacao = repository.findById(id).orElse(null);

        if (locacao != null) {
            locacao.getEquipamentos().forEach(equip -> equip.setLocacao(null));

            equipamentoRepository.saveAll(locacao.getEquipamentos());

            // for (Equipamento equipamento : locacao.getEquipamentos()) {
            //     equipamento.setLocacao(null);
            //     equipamentoRepository.save(equipamento); // Atualize o equipamento para refletir a remoção da referência
            //                                              // à locação
            // }

            locacao.setDeleted(true);

            repository.save(locacao);
        }
    }
}
