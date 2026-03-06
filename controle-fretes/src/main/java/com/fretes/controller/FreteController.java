package com.fretes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fretes.dto.CotacaoResponseDTO;
import com.fretes.entity.Frete;
import com.fretes.service.CotacaoService;
import com.fretes.service.FreteService;

@RestController
@RequestMapping("/fretes")
public class FreteController {

    private final CotacaoService cotacaoService;

    private final FreteService service;

    public FreteController(FreteService service, CotacaoService cotacaoService) {
        this.service = service;
        this.cotacaoService = cotacaoService;
    }

    @GetMapping
    public List<Frete> listar() {
        return service.listar();
    }

    @PostMapping
    public Frete salvar(@RequestBody Frete frete) {
        return service.salvar(frete);
    }
   
    @GetMapping("/calcular")
    public List<CotacaoResponseDTO> calcular(
        @RequestParam String estado,
        @RequestParam String cidade,
        @RequestParam Double peso,
        @RequestParam Double cubagem
) {

    return cotacaoService.calcularCotacao(
            estado,
            cidade,
            peso,
            cubagem
    );
}
}