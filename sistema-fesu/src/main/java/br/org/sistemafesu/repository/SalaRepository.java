package br.org.sistemafesu.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.org.sistemafesu.entity.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {

}
