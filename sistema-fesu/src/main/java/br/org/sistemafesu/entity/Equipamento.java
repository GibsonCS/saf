package br.org.sistemafesu.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "equipamentos")
public class Equipamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeEquipamento;
    private String modeloEquipamento;
    private String marcaEquipamento;
    private String tipoEquipamento;

    @ManyToOne
    @JoinColumn(name = "id_locacao")
    private Locacao locacao;
}
