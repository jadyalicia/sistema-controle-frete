package com.fretes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fretes.dto.CotacaoResponseDTO;
import com.fretes.service.CotacaoService;

@RestController
@RequestMapping("/cotacao")
public class CotacaoController {

    private final CotacaoService cotacaoService;

    public CotacaoController(CotacaoService cotacaoService) {
        this.cotacaoService = cotacaoService;
    }

    @GetMapping("/calcular")
    public List<CotacaoResponseDTO> calcularCotacao(
            @RequestParam String cidadeDestino,
            @RequestParam String estadoDestino,
            @RequestParam Double peso,
            @RequestParam Double cubagem) {

        return cotacaoService.calcularCotacao(cidadeDestino, estadoDestino, peso, cubagem);
    }

}