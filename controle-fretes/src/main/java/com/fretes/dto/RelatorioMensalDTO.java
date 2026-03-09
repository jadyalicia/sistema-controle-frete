package com.fretes.dto;

public class RelatorioMensalDTO {

    private String mes;
    private Long total;

    public RelatorioMensalDTO(String mes, Long total) {
        this.mes = mes;
        this.total = total;
    }

    public String getMes() {
        return mes;
    }

    public Long getTotal() {
        return total;
    }
}