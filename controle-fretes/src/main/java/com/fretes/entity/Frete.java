package com.fretes.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "fretes")
public class Frete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // =========================
    // DADOS DO PEDIDO
    // =========================
    private String numeroPedido;

    private String origem;

    private String destino;

    // =========================
    // DADOS LOGÍSTICOS
    // =========================
    private Double peso;

    private Double cubagem;

    // Valor do frete (financeiro)
    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    // =========================
    // DADOS FISCAIS
    // =========================
    private String numeroNF;

    private String numeroCte;

    // =========================
    // STATUS OPERACIONAL
    // =========================
    @Enumerated(EnumType.STRING)
    private StatusFrete status = StatusFrete.PENDENTE;

    // =========================
    // DATA
    // =========================
    private LocalDate dataFrete;

    // =========================
    // RELACIONAMENTO
    // =========================
    @ManyToOne
    @JoinColumn(name = "transportadora_id")
    private Transportadora transportadora;

    // =========================
    // AUTO DATA AO CRIAR
    // =========================
    @PrePersist
    public void prePersist() {
        this.dataFrete = LocalDate.now();
    }

    // =========================
    // GETTERS E SETTERS
    // =========================

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroPedido() {
        return numeroPedido;
    }

    public void setNumeroPedido(String numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getCubagem() {
        return cubagem;
    }

    public void setCubagem(Double cubagem) {
        this.cubagem = cubagem;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public String getNumeroNF() {
        return numeroNF;
    }

    public void setNumeroNF(String numeroNF) {
        this.numeroNF = numeroNF;
    }

    public String getNumeroCte() {
        return numeroCte;
    }

    public void setNumeroCte(String numeroCte) {
        this.numeroCte = numeroCte;
    }

    public StatusFrete getStatus() {
        return status;
    }

    public void setStatus(StatusFrete status) {
        this.status = status;
    }

    public LocalDate getDataFrete() {
        return dataFrete;
    }

    public void setDataFrete(LocalDate dataFrete) {
        this.dataFrete = dataFrete;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }
}