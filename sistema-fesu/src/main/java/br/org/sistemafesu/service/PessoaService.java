package br.org.sistemafesu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.Locacao;
import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.repository.LocacaoRepository;
import br.org.sistemafesu.repository.PessoaRepository;
import lombok.NonNull;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private LocacaoRepository locacaoRepository;
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> getAll(){

        return pessoaRepository.findAll();
    }

    public void save(Pessoa pessoa){
        pessoaRepository.save(pessoa);
    }

    public Pessoa update(@NonNull Long id, @NonNull Pessoa pessoa) {
        if (pessoa.getId() == null || !pessoaRepository.existsById(id)) {
            throw new IllegalArgumentException("Pessoa não encontrada!");
        }

        return pessoaRepository.save(pessoa);
    }

    //
    public void deleteWithTreatment(@NonNull Long id) {
        Pessoa pessoa = pessoaRepository.findById(id).orElse(null);

        if (pessoa != null) {
            // Remove a referência da pessoa nas locações
            for (Locacao locacao : pessoa.getLocacoes()) {
                locacao.setPessoa(null);
                locacaoRepository.save(locacao);
            }

            pessoaRepository.deleteById(id);
        }
    }

    //
    public void updateTelefone(@NonNull Long id, String telefone) {
        pessoaRepository.updateTelefone(id, telefone);
    }
}
