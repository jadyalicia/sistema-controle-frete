package com.fretes.dto;

public class DashboardFreteDTO {

    private Long totalFretes;
    private Double valorTotal;
    private Double valorMedio;
    private String transportadoraMaisUsada;

    public DashboardFreteDTO(Long totalFretes, Double valorTotal, Double valorMedio, String transportadoraMaisUsada) {
        this.totalFretes = totalFretes;
        this.valorTotal = valorTotal;
        this.valorMedio = valorMedio;
        this.transportadoraMaisUsada = transportadoraMaisUsada;
    }

    public Long getTotalFretes() {
        return totalFretes;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public Double getValorMedio() {
        return valorMedio;
    }

    public String getTransportadoraMaisUsada() {
        return transportadoraMaisUsada;
    }
}