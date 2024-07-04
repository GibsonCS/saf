package br.org.sistemafesu.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
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

    @Email
    private String email;

    @Length(min = 9, max = 10)
    private String rg;

    private Date dataExpedicao;

    private String orgaoEmissor;

    private String estadoCivil;

    private String genero;

    @OneToMany
    private Set<Endereco> enderecos = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "pessoas_cursos",
        joinColumns = { @JoinColumn(name = "pessoa_id") },
        inverseJoinColumns = { @JoinColumn(name = "curso_id") }
    )
    private List<Curso> cursos = new ArrayList<>();

    // @CreationTimestamp
    // private Instant createdAt;

    // @UpdateTimestamp
    // private Instant updatedAt;

    public void ifPresent(Object object) {
        throw new UnsupportedOperationException("Unimplemented method 'ifPresent'");
    }
}