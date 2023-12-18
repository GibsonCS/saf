package br.org.sistemafesu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.sistemafesu.entity.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

}
