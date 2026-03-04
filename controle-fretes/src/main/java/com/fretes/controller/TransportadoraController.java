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

    @GetMapping
    public List<Transportadora> listar() {
        return service.listar();
    }

    @PostMapping
    public Transportadora salvar(@RequestBody Transportadora t) {
        return service.salvar(t);
    }

    @GetMapping("/{id}")
public Transportadora buscar(@PathVariable Integer id) {
    return service.buscarPorId(id);
}

    @DeleteMapping("/{id}")
public void deletar(@PathVariable Integer id) {
    service.deletar(id);
}

    @PutMapping("/{id}")
public Transportadora atualizar(
        @PathVariable Integer id,
        @RequestBody Transportadora transportadora) {

    return service.atualizar(id, transportadora);
}

@PostMapping
public Transportadora salvar(@RequestBody TransportadoraDTO dto) {
    return service.salvarDTO(dto);
}

}