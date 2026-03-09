package model;

public class DashboardDTO {
    private int totalFretes;
    private double valorTotal;
    private double valorMedio;
    private String transportadoraMaisUsada;

    public DashboardDTO(int totalFretes, double valorTotal, double valorMedio, String transportadoraMaisUsada) {
        this.totalFretes = totalFretes;
        this.valorTotal = valorTotal;
        this.valorMedio = valorMedio;
        this.transportadoraMaisUsada = transportadoraMaisUsada;
    }

    public int getTotalFretes() { return totalFretes; }
    public double getValorTotal() { return valorTotal; }
    public double getValorMedio() { return valorMedio; }
    public String getTransportadoraMaisUsada() { return transportadoraMaisUsada; }
}