package com.hibernate.projetoveiculos.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "veiculo")
public class Veiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8, nullable = false, unique = true)
    private String placa;

    @Column(length = 20)
    private String status;

    @Column(length = 60)
    private String marca;

    @Column(length = 60)
    private String modelo;

    @Column(length = 11, unique = true)
    private String renavam;

    @Column(length = 2)
    private String ufPlaca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "municipio_id")
    private Municipio municipio;

    @Column(length = 17, unique = true)
    private String chassi;

    @Column(name = "ano_fabricacao")
    private Integer anoFabricacao;

    @Column(name = "ano_modelo")
    private Integer anoModelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_veiculo_id")
    private TipoVeiculo tipoVeiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cor_id")
    private Cor cor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "proprietario_id")
    private Pessoa proprietario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "motorista_id")
    private Pessoa motorista;

    @Column(precision = 10, scale = 2)
    private BigDecimal kmAtual;

    @Column(precision = 10, scale = 2)
    private BigDecimal kmAquisicao;

    private LocalDate dtCadastro;
    private LocalDate dtAquisicao;

    // ==== Getters e Setters ==== //

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getRenavam() { return renavam; }
    public void setRenavam(String renavam) { this.renavam = renavam; }

    public String getUfPlaca() { return ufPlaca; }
    public void setUfPlaca(String ufPlaca) { this.ufPlaca = ufPlaca; }

    public Municipio getMunicipio() { return municipio; }
    public void setMunicipio(Municipio municipio) { this.municipio = municipio; }

    public String getChassi() { return chassi; }
    public void setChassi(String chassi) { this.chassi = chassi; }

    public Integer getAnoFabricacao() { return anoFabricacao; }
    public void setAnoFabricacao(Integer anoFabricacao) { this.anoFabricacao = anoFabricacao; }

    public Integer getAnoModelo() { return anoModelo; }
    public void setAnoModelo(Integer anoModelo) { this.anoModelo = anoModelo; }

    public TipoVeiculo getTipoVeiculo() { return tipoVeiculo; }
    public void setTipoVeiculo(TipoVeiculo tipoVeiculo) { this.tipoVeiculo = tipoVeiculo; }

    public Cor getCor() { return cor; }
    public void setCor(Cor cor) { this.cor = cor; }

    public Pessoa getProprietario() { return proprietario; }
    public void setProprietario(Pessoa proprietario) { this.proprietario = proprietario; }

    public Pessoa getMotorista() { return motorista; }
    public void setMotorista(Pessoa motorista) { this.motorista = motorista; }

    public BigDecimal getKmAtual() { return kmAtual; }
    public void setKmAtual(BigDecimal kmAtual) { this.kmAtual = kmAtual; }

    public BigDecimal getKmAquisicao() { return kmAquisicao; }
    public void setKmAquisicao(BigDecimal kmAquisicao) { this.kmAquisicao = kmAquisicao; }

    public LocalDate getDtCadastro() { return dtCadastro; }
    public void setDtCadastro(LocalDate dtCadastro) { this.dtCadastro = dtCadastro; }

    public LocalDate getDtAquisicao() { return dtAquisicao; }
    public void setDtAquisicao(LocalDate dtAquisicao) { this.dtAquisicao = dtAquisicao; }
}
