package com.fretes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fretes.entity.TabelaFrete;
import com.fretes.repository.TabelaFreteRepository;

@Service
public class TabelaFreteService {

    private final TabelaFreteRepository repository;

    public TabelaFreteService(TabelaFreteRepository repository) {
        this.repository = repository;
    }

    public List<TabelaFrete> listar() {
        return repository.findAll();
    }

    public TabelaFrete buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public TabelaFrete salvar(TabelaFrete tabelaFrete) {
        return repository.save(tabelaFrete);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public TabelaFrete atualizar(Long id, TabelaFrete nova) {

        TabelaFrete existente = repository.findById(id).orElse(null);

        if (existente == null) {
            return null;
        }

        existente.setCidadeDestino(nova.getCidadeDestino());
        existente.setEstadoDestino(nova.getEstadoDestino());
        existente.setPesoMin(nova.getPesoMin());
        existente.setPesoMax(nova.getPesoMax());
        existente.setValor(nova.getValor());
        existente.setTransportadora(nova.getTransportadora());

        return repository.save(existente);
    }

    public List<TabelaFrete> cotarFrete(String cidadeDestino, String estadoDestino, double peso) {

        List<TabelaFrete> tabelas = repository.findByEstadoDestino(estadoDestino);

        List<TabelaFrete> resultado = new java.util.ArrayList<>();

        for (TabelaFrete t : tabelas) {

            boolean cidadeOk = t.getCidadeDestino() != null &&
                               t.getCidadeDestino().equalsIgnoreCase(cidadeDestino);

            boolean pesoOk = peso >= t.getPesoMin() && peso <= t.getPesoMax();

            if (cidadeOk && pesoOk) {
                resultado.add(t);
            }
        }

        resultado.sort((a, b) -> Double.compare(a.getValor(), b.getValor()));

        if (resultado.size() > 3) {
            return resultado.subList(0, 3);
        }

        return resultado;
    }
}