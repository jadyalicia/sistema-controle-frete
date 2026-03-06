package com.fretes.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fretes.dto.CotacaoResponseDTO;
import com.fretes.entity.TabelaFrete;
import com.fretes.repository.TabelaFreteRepository;

@Service
public class CotacaoService {

    private final TabelaFreteRepository tabelaFreteRepository;

    public CotacaoService(TabelaFreteRepository tabelaFreteRepository) {
        this.tabelaFreteRepository = tabelaFreteRepository;
    }

    public List<CotacaoResponseDTO> calcularCotacao(
            String cidadeDestino,
            String estadoDestino,
            Double peso,
            Double cubagem) {

        Double pesoConsiderado = Math.max(peso, cubagem);

        List<TabelaFrete> tabelas = tabelaFreteRepository.findAll();

        List<CotacaoResponseDTO> resultado = new ArrayList<>();

        for (TabelaFrete tabela : tabelas) {

            boolean destinoValido =
                    (tabela.getCidadeDestino() != null &&
                     tabela.getCidadeDestino().equalsIgnoreCase(cidadeDestino))
                    ||
                    (tabela.getEstadoDestino() != null &&
                     tabela.getEstadoDestino().equalsIgnoreCase(estadoDestino));

            boolean pesoValido =
                    pesoConsiderado >= tabela.getPesoMin() &&
                    pesoConsiderado <= tabela.getPesoMax();

            if (destinoValido && pesoValido) {

                CotacaoResponseDTO dto = new CotacaoResponseDTO();

                dto.setTransportadora(
                        tabela.getTransportadora().getNome()
                );

                dto.setValor(tabela.getValor());

                resultado.add(dto);
            }
        }

        resultado.sort(Comparator.comparing(CotacaoResponseDTO::getValor));

        if (resultado.size() > 3) {
            return resultado.subList(0, 3);
        }

        return resultado;
    }
}