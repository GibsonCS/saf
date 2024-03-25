package br.org.sistemafesu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.sistemafesu.entity.Patrimonio;

@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio, Long> {

}
