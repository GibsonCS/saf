package br.org.sistemafesu.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "endereco")
@Data
public class Endereco {

     public Endereco(){

     }

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String cep;
     private String uf;
     private String cidade;
     private String bairro;
     private String endereco;
     private long numero;
     private String complemento;

     @ManyToOne
     private Aluno aluno;
}
