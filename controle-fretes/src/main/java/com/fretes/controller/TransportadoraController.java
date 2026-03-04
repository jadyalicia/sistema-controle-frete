package com.fretes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fretes.entity.Transportadora;
import com.fretes.service.TransportadoraService;

@RestController
@RequestMapping("/transportadoras")
@CrossOrigin("*")
public class TransportadoraController {

    private final TransportadoraService service;

    public TransportadoraController(TransportadoraService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transportadora> listar() {
        return service.listar();
    }

    @PostMapping
    public Transportadora salvar(@RequestBody Transportadora t) {
        return service.salvar(t);
    }
}