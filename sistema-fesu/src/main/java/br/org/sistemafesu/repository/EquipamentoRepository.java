package br.org.sistemafesu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.sistemafesu.entity.Equipamento;

public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

}
