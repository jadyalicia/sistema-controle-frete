package com.fretes.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fretes.dto.TransportadoraDTO;
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

    // LISTAR
    @GetMapping
    public List<Transportadora> listar() {
        return service.listar();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Transportadora buscar(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    // SALVAR (USANDO DTO)
    @PostMapping
    public Transportadora salvar(@RequestBody TransportadoraDTO dto) {

        Transportadora t = new Transportadora();

        t.setNome(dto.getNome());
        t.setCnpj(dto.getCnpj());
        t.setTelefone(dto.getTelefone());
        t.setEmail(dto.getEmail());

        return service.salvar(t);
    }

    // ATUALIZAR
    @PutMapping("/{id}")
    public Transportadora atualizar(
            @PathVariable Integer id,
            @RequestBody TransportadoraDTO dto) {

        Transportadora t = new Transportadora();

        t.setNome(dto.getNome());
        t.setCnpj(dto.getCnpj());
        t.setTelefone(dto.getTelefone());
        t.setEmail(dto.getEmail());

        return service.atualizar(id, t);
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Integer id) {
        service.deletar(id);
    }

}