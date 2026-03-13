package com.fretes.front.model;

public class RankingCotacao {

    private String transportadora;
    private Double valor;
    private Integer posicao;

    public RankingCotacao() {}

    public RankingCotacao(String transportadora, Double valor, Integer posicao) {
        this.transportadora = transportadora;
        this.valor = valor;
        this.posicao = posicao;
    }

    public String getTransportadora() {
        return transportadora;
    }

    public Double getValor() {
        return valor;
    }

    public Integer getPosicao() {
        return posicao;
    }
}