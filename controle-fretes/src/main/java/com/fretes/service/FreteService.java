package com.fretes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fretes.dto.DashboardFreteDTO;
import com.fretes.dto.RankingTransportadoraDTO;
import com.fretes.dto.RelatorioMensalDTO;
import com.fretes.entity.Frete;
import com.fretes.repository.FreteRepository;

@Service
public class FreteService {

    private final FreteRepository freteRepository;

    public FreteService(FreteRepository freteRepository) {
        this.freteRepository = freteRepository;
    }

    public List<Frete> listar() {
        return freteRepository.findAll();
    }

    public Frete salvar(Frete frete) {
        return freteRepository.save(frete);
    }

    public DashboardFreteDTO dashboard() {

        Long totalFretes = freteRepository.totalFretes();

        Double valorTotal = freteRepository.valorTotalFretes();

        Double valorMedio = freteRepository.valorMedioFretes();

        String transportadoraMaisUsada = freteRepository
                .transportadoraMaisUsada()
                .stream()
                .findFirst()
                .orElse(null);

        return new DashboardFreteDTO(
                totalFretes,
                valorTotal,
                valorMedio,
                transportadoraMaisUsada
        );
    }

    public List<RelatorioMensalDTO> relatorioMensal() {

    List<Object[]> resultado = freteRepository.relatorioMensal();

    return resultado.stream()
            .map(r -> new RelatorioMensalDTO(
                    (String) r[0],
                    ((Number) r[1]).longValue()
            ))
            .toList();
}

    public List<RankingTransportadoraDTO> rankingTransportadoras() {
        return freteRepository.rankingTransportadoras();
    }

    public List<Frete> fretesPorTransportadora(Long id) {
        return freteRepository.findByTransportadoraId(id);
    }
}