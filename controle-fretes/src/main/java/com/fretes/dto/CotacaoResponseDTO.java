package com.fretes.dto;

public class CotacaoResponseDTO {

    private String transportadora;
    private Double valor;

    public CotacaoResponseDTO() {
    }

    public CotacaoResponseDTO(String transportadora, Double valor) {
        this.transportadora = transportadora;
        this.valor = valor;
    }

    public String getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(String transportadora) {
        this.transportadora = transportadora;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}