package br.org.sistemafesu.service;

import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.Locacao;
import br.org.sistemafesu.repository.EquipamentoRepository;
import br.org.sistemafesu.repository.LocacaoRepository;

@Service
public class LocacaoService extends AbstractService<Locacao, LocacaoRepository> {
    private final EquipamentoRepository equipamentoRepository;

    public LocacaoService(Locacao locacao, LocacaoRepository locacaoRepository,
            EquipamentoRepository equipamentoRepository) {
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
            locacao.getEquipamentos().forEach(equip -> equip.setLocated(false));

            equipamentoRepository.saveAll(locacao.getEquipamentos());

            locacao.setDeleted(true);

            repository.save(locacao);
        }
    }

    public Iterable<Locacao> findAllLocacaoDeleted() {
        return repository.findAllByIsDeletedTrue();
    }
}
