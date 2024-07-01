package br.org.sistemafesu.entity;

import java.time.Instant;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@SpringBootApplication
@ComponentScan(basePackages = "br.org.sistemafesu")
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 11, max = 14)
    @CPF(message = "CPF inválido.")
    private String cpf;

    @NotBlank(message = "Nome é obrigatório.")
    @Size(max = 255, message = "Nome deve ter no máximo 255 caracteres")
    private String nome;

    private String sobrenome;

    @Column(length = 15)
    @Size(min = 14, max = 15)
    private String telefone;

    // @OneToMany(mappedBy = "pessoa")
    // private List<Locacao> locacoes;

    @OneToMany(mappedBy = "pessoa")
    private List<Equipamento> equipamentos;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;

    public void ifPresent(Object object) {
        throw new UnsupportedOperationException("Unimplemented method 'ifPresent'");
    }
}