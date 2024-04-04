package br.org.sistemafesu.entity;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@SpringBootApplication
@ComponentScan(basePackages = "br.org.sistemafesu")
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nome_completo", length = 50, nullable = false)
    private String nomeCompleto;

    @Column(name = "login", length = 50, nullable = true)
    private String username;

    @JsonIgnore
    @Column(name = "senha", length = 100, nullable = true)
    private String password;

    @Email
    private String email;

    @Length(min = 11, max = 14)
    @CPF(message = "CPF inválido.")
    private String cpf;

    @Length(min = 9, max = 10)
    private String rg;

    private Date dataExpedicao;

    private String orgaoEmissor;

    private String estadoCivil;

    private String genero;

    @Column(length = 15)
    @Size(min = 14, max = 15)
    private String telefone;

    @OneToMany
    private Set<Endereco> enderecos = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "funcoes_usuarios",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_funcao")
    )
    private Set<Role> roles = new HashSet<>();


    public User() {

    }

    public User(String login) {
        this.username = login;
    }


    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
