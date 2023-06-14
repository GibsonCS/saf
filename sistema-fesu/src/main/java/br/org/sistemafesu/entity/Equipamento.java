package br.org.sistemafesu.entity;

import jakarta.persistence.*;

@Entity
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idEquipamento;
    private String nomeEquipamento;
    private String modeloEquipamento;
    private String marcaEquipamento;
    private String tipoEquipamento;


    public long getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(long idEquipamento) {
        this.idEquipamento = idEquipamento;
    }

    public String getNomeEquipamento() {
        return nomeEquipamento;
    }

    public void setNomeEquipamento(String nomeEquipamento) {
        this.nomeEquipamento = nomeEquipamento;
    }

    public String getModeloEquipamento() {
        return modeloEquipamento;
    }

    public void setModeloEquipamento(String modeloEquipamento) {
        this.modeloEquipamento = modeloEquipamento;
    }

    public String getMarcaEquipamento() {
        return marcaEquipamento;
    }

    public void setMarcaEquipamento(String marcaEquipamento) {
        this.marcaEquipamento = marcaEquipamento;
    }

    public String getTipoEquipamento() {
        return tipoEquipamento;
    }

    public void setTipoEquipamento(String tipoEquipamento) {
        this.tipoEquipamento = tipoEquipamento;
    }

}
