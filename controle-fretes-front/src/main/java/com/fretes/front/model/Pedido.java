package com.fretes.front.model;

public class Pedido {

    private int numeroPedido;
    private String cliente;
    private String cidade;
    private String estado;
    private double peso;
    private double cubagem;

    // Construtor vazio
    public Pedido() {
    }

    // Construtor completo
    public Pedido(int numeroPedido, String cliente, String cidade, String estado, double peso, double cubagem) {
        this.numeroPedido = numeroPedido;
        this.cliente = cliente;
        this.cidade = cidade;
        this.estado = estado;
        this.peso = peso;
        this.cubagem = cubagem;
    }

    // Getters e Setters
    public int getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(int numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getCubagem() {
        return cubagem;
    }

    public void setCubagem(double cubagem) {
        this.cubagem = cubagem;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "numeroPedido=" + numeroPedido +
                ", cliente='" + cliente + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado='" + estado + '\'' +
                ", peso=" + peso +
                ", cubagem=" + cubagem +
                '}';
    }
}