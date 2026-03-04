package com.fretes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fretes.entity.Transportadora;

public interface TransportadoraRepository
        extends JpaRepository<Transportadora, Long> {
}