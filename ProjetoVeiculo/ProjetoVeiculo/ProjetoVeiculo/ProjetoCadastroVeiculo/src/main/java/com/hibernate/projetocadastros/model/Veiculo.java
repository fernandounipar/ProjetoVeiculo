package com.hibernate.projetocadastros.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 * Entidade Veiculo – campos principais conforme layout. Mantém o mesmo
 * padrão usado nas entidades originais (Cliente, Usuario, etc.).
 */
@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8, nullable = false, unique = true)
    private String placa;

    @Column(name = "nr_frota")
    private Integer nrFrota;

    @Column(length = 60)
    private String modelo;

    @Column(length = 2)
    private String ufPlaca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "municipio_id")
    private Municipio municipio;

    @Column(length = 17, unique = true)
    private String chassi;

    private Integer anoFab;
    private Integer anoModelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_id")
    private TipoVeiculo tipoVeiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cor_id")
    private Cor cor;

    @Column(precision = 10, scale = 2)
    private BigDecimal kmAtual;

    @Column(precision = 10, scale = 2)
    private BigDecimal kmAquisicao;

    private LocalDate dtCadastro;
    private LocalDate dtAquisicao;

    /* ==== getters / setters ==== */

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public Integer getNrFrota() { return nrFrota; }
    public void setNrFrota(Integer nrFrota) { this.nrFrota = nrFrota; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getUfPlaca() { return ufPlaca; }
    public void setUfPlaca(String ufPlaca) { this.ufPlaca = ufPlaca; }

    public Municipio getMunicipio() { return municipio; }
    public void setMunicipio(Municipio municipio) { this.municipio = municipio; }

    public String getChassi() { return chassi; }
    public void setChassi(String chassi) { this.chassi = chassi; }

    public Integer getAnoFab() { return anoFab; }
    public void setAnoFab(Integer anoFab) { this.anoFab = anoFab; }

    public Integer getAnoModelo() { return anoModelo; }
    public void setAnoModelo(Integer anoModelo) { this.anoModelo = anoModelo; }

    public TipoVeiculo getTipoVeiculo() { return tipoVeiculo; }
    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) { this.tipoVeiculo = tipoVeiculo; }

    public Cor getCor() { return cor; }
    public void setCor(Cor cor) { this.cor = cor; }

    public BigDecimal getKmAtual() { return kmAtual; }
    public void setKmAtual(BigDecimal kmAtual) { this.kmAtual = kmAtual; }

    public BigDecimal getKmAquisicao() { return kmAquisicao; }
    public void setKmAquisicao(BigDecimal kmAquisicao) { this.kmAquisicao = kmAquisicao; }

    public LocalDate getDtCadastro() { return dtCadastro; }
    public void setDtCadastro(LocalDate dtCadastro) { this.dtCadastro = dtCadastro; }

    public LocalDate getDtAquisicao() { return dtAquisicao; }
    public void setDtAquisicao(LocalDate dtAquisicao) { this.dtAquisicao = dtAquisicao; }
}
