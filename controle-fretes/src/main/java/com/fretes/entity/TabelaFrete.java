package com.fretes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tabela_frete")
public class TabelaFrete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estadoDestino;

    private String cidadeDestino;

    private Double pesoMin;

    private Double pesoMax;

    private Double cubagemMin;

    private Double cubagemMax;

    private Double valor;

    @ManyToOne
    @JoinColumn(name = "transportadora_id")
    private Transportadora transportadora;

    public Long getId() {
        return id;
    }

    public String getEstadoDestino() {
        return estadoDestino;
    }

    public void setEstadoDestino(String estadoDestino) {
        this.estadoDestino = estadoDestino;
    }

    public String getCidadeDestino() {
        return cidadeDestino;
    }

    public void setCidadeDestino(String cidadeDestino) {
        this.cidadeDestino = cidadeDestino;
    }

    public Double getPesoMin() {
        return pesoMin;
    }

    public void setPesoMin(Double pesoMin) {
        this.pesoMin = pesoMin;
    }

    public Double getPesoMax() {
        return pesoMax;
    }

    public void setPesoMax(Double pesoMax) {
        this.pesoMax = pesoMax;
    }

    public Double getCubagemMin() {
        return cubagemMin;
    }

    public void setCubagemMin(Double cubagemMin) {
        this.cubagemMin = cubagemMin;
    }

    public Double getCubagemMax() {
        return cubagemMax;
    }

    public void setCubagemMax(Double cubagemMax) {
        this.cubagemMax = cubagemMax;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }
}