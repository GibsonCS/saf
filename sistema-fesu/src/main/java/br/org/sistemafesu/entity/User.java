package br.org.sistemafesu.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer id;

    @Column(name = "nome", length = 50, nullable = false)
    private String name;

    @Column(name = "login", length = 50, nullable = false)
    private String username;

    @Column(name = "senha", length = 100, nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "funcoes", joinColumns = @JoinColumn(name = "id_usuario"))
    @Column (name = "id_funcao")
    private List<String> roles = new ArrayList<>();

    public User(){

    }

    public User(String login){
        this.username = login;
    }

}
