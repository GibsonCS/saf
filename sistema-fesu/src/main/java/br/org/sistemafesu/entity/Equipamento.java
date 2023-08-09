package br.org.sistemafesu.entity;

import jakarta.persistence.*;

@Entity
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nomeEquipamento;
    private String modeloEquipamento;
    private String marcaEquipamento;
    private String tipoEquipamento;

    @ManyToOne
    @JoinColumn(name = "id_locacao")
    private Locacao locacao;

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return  "Nome: " + nomeEquipamento +
                " Modelo: " + modeloEquipamento +
                " Marca: " + marcaEquipamento +
                "Tipo: " + tipoEquipamento;
    }
}
