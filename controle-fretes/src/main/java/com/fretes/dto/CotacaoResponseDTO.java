package com.fretes.dto;

import com.fretes.entity.Transportadora;

public class CotacaoResponseDTO {

    private Transportadora transportadora;
    private Double valor;
    private Double pesoMin;
    private Double pesoMax;

    public CotacaoResponseDTO(Transportadora transportadora, Double valor, Double pesoMin, Double pesoMax) {
        this.transportadora = transportadora;
        this.valor = valor;
        this.pesoMin = pesoMin;
        this.pesoMax = pesoMax;
    }

    // Getters e setters
    public Transportadora getTransportadora() { return transportadora; }
    public void setTransportadora(Transportadora transportadora) { this.transportadora = transportadora; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public Double getPesoMin() { return pesoMin; }
    public void setPesoMin(Double pesoMin) { this.pesoMin = pesoMin; }

    public Double getPesoMax() { return pesoMax; }
    public void setPesoMax(Double pesoMax) { this.pesoMax = pesoMax; }
}