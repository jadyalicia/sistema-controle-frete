package com.fretes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fretes.dto.RankingTransportadoraDTO;
import com.fretes.entity.Frete;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long> {

    @Query("SELECT COUNT(f) FROM Frete f")
    Long totalFretes();

    @Query("SELECT SUM(f.valor) FROM Frete f")
    Double valorTotalFretes();

    @Query("SELECT AVG(f.valor) FROM Frete f")
    Double valorMedioFretes();

    @Query("""
    SELECT f.transportadora.nome
    FROM Frete f
    GROUP BY f.transportadora.nome
    ORDER BY COUNT(f) DESC
    """)
    List<String> transportadoraMaisUsada();

    List<Frete> findByTransportadoraId(Long transportadoraId);

    @Query("""
    SELECT new com.fretes.dto.RankingTransportadoraDTO(
        f.transportadora.nome,
        COUNT(f)
    )
    FROM Frete f
    GROUP BY f.transportadora.nome
    ORDER BY COUNT(f) DESC
    """)
    List<RankingTransportadoraDTO> rankingTransportadoras();

    @Query(value = """
    SELECT 
    strftime('%Y-%m', data_frete) as mes,
    COUNT(*) as total
    FROM frete
    GROUP BY mes
    ORDER BY mes
    """, nativeQuery = true)
    List<Object[]> relatorioMensal();
}