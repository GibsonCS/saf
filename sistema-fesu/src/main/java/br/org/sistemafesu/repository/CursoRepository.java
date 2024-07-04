package br.org.sistemafesu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.sistemafesu.entity.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

}
