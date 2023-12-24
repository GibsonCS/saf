package br.org.sistemafesu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.org.sistemafesu.entity.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
    @Query("SELECT l FROM Locacao l WHERE l.isDeleted = true")
    Iterable<Locacao> findAllByIsDeletedTrue();
}
