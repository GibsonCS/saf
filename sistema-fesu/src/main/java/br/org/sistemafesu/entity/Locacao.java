package br.org.sistemafesu.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idLocacao;

    @OneToOne
    private Sala sala;

    private String data;

    private String evento;
     private String situacao;

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public long getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(long idLocacao) {
        this.idLocacao = idLocacao;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }


}
