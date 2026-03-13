package com.fretes.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FreteDTO {

    private Integer id;
    private String numeroPedido;
    private String origem;
    private String destino;
    private Double peso;
    private Double cubagem;
    private BigDecimal valor;

    private String numeroNF;
    private String numeroCte;

    private String status;
    private LocalDate dataFrete;

    private Integer transportadoraId;
    private String transportadoraNome;

    // getters e setters
}