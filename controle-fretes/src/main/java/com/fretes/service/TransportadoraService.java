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

    public Transportadora buscarPorId(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transportadora não encontrada"));
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }

    public Transportadora atualizar(Integer id, Transportadora nova) {

        Transportadora existente = buscarPorId(id);

        existente.setNome(nova.getNome());
        existente.setCnpj(nova.getCnpj());
        existente.setEmail(nova.getEmail());
        existente.setTelefone(nova.getTelefone());

        return repository.save(existente);
    }
}