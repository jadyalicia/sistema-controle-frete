package com.fretes.dto;

public class RankingTransportadoraDTO {

    private String transportadora;
    private Long totalFretes;

    public RankingTransportadoraDTO(String transportadora, Long totalFretes) {
        this.transportadora = transportadora;
        this.totalFretes = totalFretes;
    }

    public String getTransportadora() {
        return transportadora;
    }

    public Long getTotalFretes() {
        return totalFretes;
    }
}