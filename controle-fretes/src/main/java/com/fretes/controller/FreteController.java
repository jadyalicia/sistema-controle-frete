package com.fretes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fretes.dto.CotacaoResponseDTO;
import com.fretes.dto.RankingTransportadoraDTO;
import com.fretes.entity.Frete;
import com.fretes.service.CotacaoService;
import com.fretes.service.FreteService;

@RestController
@RequestMapping("/fretes")
public class FreteController {

    private final FreteService freteService;
    private final CotacaoService cotacaoService;

    public FreteController(FreteService freteService, CotacaoService cotacaoService) {
        this.freteService = freteService;
        this.cotacaoService = cotacaoService;
    }

    @GetMapping
    public List<Frete> listar() {
        return freteService.listar();
    }

    @PostMapping
    public Frete salvar(@RequestBody Frete frete) {
        return freteService.salvar(frete);
    }

    @GetMapping("/cotacao")
    public List<CotacaoResponseDTO> cotarFrete(
            @RequestParam String estado,
            @RequestParam String cidade,
            @RequestParam Double peso,
            @RequestParam Double cubagem) {

        return cotacaoService.calcularCotacao(
                estado,
                cidade,
                peso,
                cubagem
        );
    }

    @GetMapping("/transportadoras/ranking")
    public List<RankingTransportadoraDTO> rankingTransportadoras() {
        return freteService.rankingTransportadoras();
    }

    @GetMapping("/transportadoras/{id}/fretes")
    public List<Frete> fretesPorTransportadora(@PathVariable Long id) {
        return freteService.fretesPorTransportadora(id);
    }
}