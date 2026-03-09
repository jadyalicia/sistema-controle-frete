package com.fretes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fretes.dto.CotacaoResponseDTO;
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
        existente.setCubagemMin(nova.getCubagemMin());
        existente.setCubagemMax(nova.getCubagemMax());
        existente.setValor(nova.getValor());
        existente.setTransportadora(nova.getTransportadora());

        return repository.save(existente);
    }

    public List<CotacaoResponseDTO> cotarFrete(String estadoDestino, String cidadeDestino, double peso) {

    // Busca por cidade primeiro, se não tiver, busca por estado
    List<TabelaFrete> tabelas = repository.findByCidadeDestino(cidadeDestino);
    if (tabelas.isEmpty()) {
        tabelas = repository.findByEstadoDestino(estadoDestino);
    }

    // Filtra por peso
    List<CotacaoResponseDTO> resultado = new ArrayList<>();
    for (TabelaFrete t : tabelas) {
        if (peso >= t.getCubagemMin() && peso <= t.getCubagemMax()) {
            resultado.add(new CotacaoResponseDTO(
                t.getTransportadora(),
                t.getValor(),
                t.getCubagemMin(),
                t.getCubagemMax()
            ));
        }
    }

    // Ordena pelo valor crescente
    resultado.sort((a, b) -> Double.compare(a.getValor(), b.getValor()));

    // Retorna os 3 melhores
    if (resultado.size() > 3) {
        return resultado.subList(0, 3);
    }

    return resultado;
}
}