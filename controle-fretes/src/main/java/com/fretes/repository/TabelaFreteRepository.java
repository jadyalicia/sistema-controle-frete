package com.fretes.repository;

import com.fretes.entity.TabelaFrete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TabelaFreteRepository extends JpaRepository<TabelaFrete, Long> {

    List<TabelaFrete> findByCidadeDestino(String cidadeDestino);

    List<TabelaFrete> findByEstadoDestino(String estadoDestino);

}