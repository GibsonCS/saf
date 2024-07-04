package br.org.sistemafesu.entity;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@SpringBootApplication
// @ComponentScan(basePackages = "br.org.sistemafesu")
@Table(name = "USUARIOS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;


    @Column(name = "login", length = 50, nullable = true)
    private String username;


    @JsonIgnore
    @Column(name = "senha", length = 100, nullable = true)
    private String password;

    @Column(name = "nome_completo", length = 50, nullable = false)
    private String nomeCompleto;



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
