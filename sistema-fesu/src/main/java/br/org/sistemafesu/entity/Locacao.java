package br.org.sistemafesu.entity;
import java.util.List;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    private String data;

    private String horaInicial;

    private String horaFinal;

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
}
