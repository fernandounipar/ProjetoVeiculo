package com.hibernate.projetoveiculos.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Entidade Cor - representa uma cor de veículo.
 */
@Entity
@Table(name = "cor")
public class Cor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, nullable = false)
    private String descricao;

    /** Construtor padrão. */
    public Cor() {}

    /** Construtor para criação rápida. */
    public Cor(String descricao) {
        this.descricao = descricao;
    }

    /** Construtor completo (caso precise). */
    public Cor(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String toString() {
        return "[" + id + "] " + descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cor)) return false;
        Cor cor = (Cor) o;
        return Objects.equals(id, cor.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
