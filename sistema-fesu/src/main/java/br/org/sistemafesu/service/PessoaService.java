package br.org.sistemafesu.service;

import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.Locacao;
import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.repository.LocacaoRepository;
import br.org.sistemafesu.repository.PessoaRepository;
import lombok.NonNull;

@Service
public class PessoaService extends AbstractService<Pessoa, PessoaRepository> {
    private final LocacaoRepository locacaoRepository;

    public PessoaService(PessoaRepository pessoaRepository, LocacaoRepository locacaoRepository) {
        super(pessoaRepository);

        this.locacaoRepository = locacaoRepository;
    }

    @Override
    public Pessoa update(@NonNull Long id, @NonNull Pessoa model) {
        if (model.getId() == null || !repository.existsById(id)) {
            throw new IllegalArgumentException("Pessoa não encontrada!");
        }

        return super.save(model);
    }

    public void deleteWithTreatment(@NonNull Long id) {
        Pessoa pessoa = repository.findById(id).orElse(null);

        if (pessoa != null) {
            // Remove a referência da pessoa nas locações
            for (Locacao locacao : pessoa.getLocacoes()) {
                locacao.setPessoa(null);
                locacaoRepository.save(locacao);
            }

            super.deleteById(id);
        }
    }

    public void updateTelefone(@NonNull Long id, String telefone) {
        repository.updateTelefone(id, telefone);
    }
}
