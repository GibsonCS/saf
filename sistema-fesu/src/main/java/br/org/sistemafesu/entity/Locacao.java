package br.org.sistemafesu.entity;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@SpringBootApplication
@ComponentScan(basePackages = "br.org.sistemafesu")
@Table(name = "locacoes")
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date data;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime horaInicial;

    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime horaFinal;

    @Column(name = "is_deleted", columnDefinition = "BIT", nullable = false)
    private boolean isDeleted = false;

    private String evento;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_sala")
    @JsonIgnoreProperties(value = "locacoes")
    private Sala sala;

    @ManyToOne
    @JoinColumn(name = "id_pessoa")
    @JsonIgnoreProperties(value = "locacoes")
    private Pessoa pessoa;

    @OneToMany(mappedBy = "locacao")
    @JsonIgnoreProperties(value = "locacao")
    private List<Equipamento> equipamentos;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
