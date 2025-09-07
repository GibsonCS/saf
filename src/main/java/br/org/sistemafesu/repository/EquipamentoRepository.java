package br.org.sistemafesu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.sistemafesu.entity.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {
    // @Query("SELECT e FROM Equipamento e WHERE e.locacao not is null")
    Iterable<Equipamento> findAllByLocacaoIsNull();
}
