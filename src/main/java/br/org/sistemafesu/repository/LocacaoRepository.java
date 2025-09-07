package br.org.sistemafesu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.sistemafesu.entity.Locacao;

public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
    // @Query("SELECT l FROM Locacao l WHERE l.isDeleted = true")
    List<Locacao> findAllByIsDeletedTrue();

    // @Query("SELECT l FROM Locacao l WHERE l.isDeleted = false")
    List<Locacao> findAllByIsDeletedFalse();
}
