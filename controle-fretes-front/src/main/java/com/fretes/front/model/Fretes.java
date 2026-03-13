package com.fretes.front.model;

public class Fretes {

    private Integer id;
    private Integer numeroPedido;
    private String cliente;
    private String cidade;
    private String estado;
    private Double peso;
    private Double cubagem;
    private String transportadora;
    private Double valor;
    private String numeroNF;
    private String numeroCte;
    private String status;

    // ===== GETTERS & SETTERS =====
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Integer getNumeroPedido() { return numeroPedido; }
    public void setNumeroPedido(Integer numeroPedido) { this.numeroPedido = numeroPedido; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public Double getCubagem() { return cubagem; }
    public void setCubagem(Double cubagem) { this.cubagem = cubagem; }

    public String getTransportadora() { return transportadora; }
    public void setTransportadora(String transportadora) { this.transportadora = transportadora; }

    public Double getValor() { return valor; }
    public void setValor(Double valor) { this.valor = valor; }

    public String getNumeroNF() { return numeroNF; }
    public void setNumeroNF(String numeroNF) { this.numeroNF = numeroNF; }

    public String getNumeroCte() { return numeroCte; }
    public void setNumeroCte(String numeroCte) { this.numeroCte = numeroCte; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}