package com.hibernate.projetoveiculos.model;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tipo_veiculo")
public class TipoVeiculo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String descricao;

    @Column(length = 30)
    private String categoria;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    @Override
    public String toString() { return descricao; }
}
