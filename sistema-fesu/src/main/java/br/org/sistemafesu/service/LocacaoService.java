package br.org.sistemafesu.service;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.Equipamento;
import br.org.sistemafesu.entity.Locacao;
import br.org.sistemafesu.repository.EquipamentoRepository;
import br.org.sistemafesu.repository.LocacaoRepository;
import lombok.NonNull;

@Service
public class LocacaoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;
    @Autowired
    private LocacaoRepository locacaoRepository;

    //
//    public LocacaoService(LocacaoRepository locacaoRepository,
//            EquipamentoRepository equipamentoRepository) {
//        super(locacaoRepository);
//
//        this.equipamentoRepository = equipamentoRepository;
//    }
//
//    @Override
//    public Locacao update(@NonNull Long id, @NonNull Locacao model) {
//        if (model.getId() == null || !repository.existsById(id)) {
//            throw new IllegalArgumentException("Locação não encontrada!");
//        }
//
//        return super.save(model);
//    }
//
    public void deleteWithTreatment(@NonNull Long id) {
        Locacao locacao = locacaoRepository.findById(id).orElse(null);

        if (locacao != null) {
            locacao.getEquipamentos().forEach(equip -> equip.setLocacao(null));
            locacao.getEquipamentos().forEach(equip -> equip.setLocated(false));
            locacao.getEquipamentos().forEach(equip -> equip.setPessoa(null));
            locacao.getPessoa().setLocacoes(null);

            List<Equipamento> equipamentos = locacao.getEquipamentos();

            if (equipamentos != null) {
                equipamentoRepository.saveAll(equipamentos);
            }

            locacao.setDeleted(true);

            locacaoRepository.save(locacao);
        }
    }
//
    public List<Locacao> findAllLocacaoDeleted() {
        return locacaoRepository.findAllByIsDeletedTrue();
    }
//
    public List<Locacao> findAllLocacaoNotDeleted() {
        List<Locacao> locacoes = (List<Locacao>) locacaoRepository.findAllByIsDeletedFalse();

        return locacoes.stream().sorted(Comparator.comparing(Locacao::getData)).toList();
    }
}