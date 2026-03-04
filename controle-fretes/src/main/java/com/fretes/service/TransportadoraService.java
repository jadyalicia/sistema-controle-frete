package com.fretes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fretes.entity.Transportadora;
import com.fretes.repository.TransportadoraRepository;

@Service
public class TransportadoraService {

    private final TransportadoraRepository repository;

    public TransportadoraService(TransportadoraRepository repository) {
        this.repository = repository;
    }

    public List<Transportadora> listar() {
        return repository.findAll();
    }

    public Transportadora salvar(Transportadora t) {
        return repository.save(t);
    }
}