package br.org.sistemafesu.service;

import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.Locacao;
import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.repository.LocacaoRepository;
import br.org.sistemafesu.repository.PessoaRepository;

@Service
public class PessoaService extends AbstractService<Pessoa, PessoaRepository> {
    private final LocacaoRepository locacaoRepository;

    public PessoaService(Pessoa pessoa, PessoaRepository pessoaRepository, LocacaoRepository locacaoRepository) {
        super(pessoa, pessoaRepository);

        this.locacaoRepository = locacaoRepository;
    }

    @Override
    public Pessoa update(Long id, Pessoa model) {
        if (model.getId() == null || !repository.existsById(id)) {
            throw new IllegalArgumentException("Pessoa não encontrada!");
        }

        return super.save(model);
    }

    public void deleteWithTreatment(Long id) {
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

    public void updateTelefone(Long id, String telefone) {
        repository.updateTelefone(id, telefone);
    }
}
