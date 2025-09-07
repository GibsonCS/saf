package br.org.sistemafesu.entity;

import lombok.Data;
import groovy.transform.EqualsAndHashCode;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "alunos")
@PrimaryKeyJoinColumn(name = "user_id")
@EqualsAndHashCode(callSuper = false)
public class Aluno extends User {

    @Column(length = 10)
    private Long matricula;
}
