package com.fretes.controller;

import com.fretes.entity.TabelaFrete;
import com.fretes.repository.TabelaFreteRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tabela-frete")
public class TabelaFreteController {

    private final TabelaFreteRepository repository;

    public TabelaFreteController(TabelaFreteRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public TabelaFrete salvar(@RequestBody TabelaFrete tabela) {
        return repository.save(tabela);
    }

    @GetMapping
    public List<TabelaFrete> listar() {
        return repository.findAll();
    }
}