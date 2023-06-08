package br.org.sistemafesu.repository;

import br.org.sistemafesu.entity.Pessoa;
import org.springframework.data.repository.CrudRepository;

public interface PessoaRepository extends CrudRepository<Pessoa,Long> {

}
