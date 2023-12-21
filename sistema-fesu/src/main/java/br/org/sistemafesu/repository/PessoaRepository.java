package br.org.sistemafesu.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.sistemafesu.entity.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    default void updateTelefone(long id, String novoTelefone) {
        Optional<Pessoa> pessoaOptional = findById(id);

        pessoaOptional.ifPresent(pessoa -> {
            pessoa.setTelefone(novoTelefone);
            save(pessoa);
        });
    }


}
