package com.fretes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fretes.entity.Frete;
import com.fretes.repository.FreteRepository;

@Service
public class FreteService {

    private final FreteRepository repository;

    public FreteService(FreteRepository repository) {
        this.repository = repository;
    }

    public List<Frete> listar() {
        return repository.findAll();
    }

    public Frete salvar(Frete frete) {
        return repository.save(frete);
    }
}