package br.org.sistemafesu.entity;

import jakarta.persistence.*;

import java.util.Date;
@Entity
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idLocacao;

    private boolean situacao;
    private Date data;

    private String evento;

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
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

}
