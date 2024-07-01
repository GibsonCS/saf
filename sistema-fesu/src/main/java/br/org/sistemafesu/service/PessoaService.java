package br.org.sistemafesu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.org.sistemafesu.entity.Pessoa;
import br.org.sistemafesu.repository.PessoaRepository;
import lombok.NonNull;

@Service
public class PessoaService  {

    @Autowired
    private PessoaRepository pessoaRepository;

    public void save(Pessoa pessoa) {
        pessoaRepository.save(pessoa);
    }

    public void deletePessoa(@NonNull Long id) {
        if(pessoaRepository.existsById(id)) {
            pessoaRepository.deleteById(id);
        }
    }

    public void updateTelefone(@NonNull Long id, String telefone) {
        pessoaRepository.updateTelefone(id, telefone);
    }

    public List<Pessoa> getAll() {
        return pessoaRepository.findAll();
    }
}
