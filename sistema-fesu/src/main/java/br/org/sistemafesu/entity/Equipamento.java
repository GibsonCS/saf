package br.org.sistemafesu.entity;

import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@SpringBootApplication
@ComponentScan(basePackages = "br.org.sistemafesu")
@Table(name = "equipamentos")
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeEquipamento;
    private String modeloEquipamento;
    private String numeroSerie;
    private String marcaEquipamento;
    private String tipoEquipamento;
    private boolean isLocated = false;

    @ManyToOne
    @JoinColumn(name = "id_locacao")
    @JsonIgnoreProperties(value = "equipamentos")
    private Locacao locacao;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    @JsonIgnoreProperties(value = "equipamentos")
    private Pessoa pessoa = null;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
