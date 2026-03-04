package com.fretes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fretes.entity.Frete;
import com.fretes.service.FreteService;

@RestController
@RequestMapping("/fretes")
public class FreteController {

    private final FreteService service;

    public FreteController(FreteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Frete> listar() {
        return service.listar();
    }

    @PostMapping
    public Frete salvar(@RequestBody Frete frete) {
        return service.salvar(frete);
    }
}