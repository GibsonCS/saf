package br.org.sistemafesu.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.Equipamento;
import br.org.sistemafesu.entity.Locacao;
import br.org.sistemafesu.repository.EquipamentoRepository;
import br.org.sistemafesu.repository.LocacaoRepository;
import lombok.NonNull;

@Service
public class LocacaoService extends AbstractService<Locacao, LocacaoRepository> {
    private final EquipamentoRepository equipamentoRepository;

    public LocacaoService(LocacaoRepository locacaoRepository,
            EquipamentoRepository equipamentoRepository) {
        super(locacaoRepository);

        this.equipamentoRepository = equipamentoRepository;
    }

    @Override
    public Locacao update(@NonNull Long id, @NonNull Locacao model) {
        if (model.getId() == null || !repository.existsById(id)) {
            throw new IllegalArgumentException("Locação não encontrada!");
        }

        return super.save(model);
    }

    public void deleteWithTreatment(@NonNull Long id) {
        Locacao locacao = repository.findById(id).orElse(null);

        if (locacao != null) {
            locacao.getEquipamentos().forEach(equip -> equip.setLocacao(null));
            locacao.getEquipamentos().forEach(equip -> equip.setLocated(false));

            List<Equipamento> equipamentos = locacao.getEquipamentos();

            if (equipamentos != null) {
                equipamentoRepository.saveAll(equipamentos);
            }

            locacao.setDeleted(true);

            repository.save(locacao);
        }
    }

    public Iterable<Locacao> findAllLocacaoDeleted() {
        return repository.findAllByIsDeletedTrue();
    }
}