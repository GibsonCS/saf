package br.org.sistemafesu.entity;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@SpringBootApplication
@Table(name = "patrimonios")
public class Patrimonio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String marca;
    private String modelo;
    private String numeroSerie;
    private String categoria;
    private String numeroPatrimonio;
    private String localizacao;
}
