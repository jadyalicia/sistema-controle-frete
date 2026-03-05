package com.fretes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fretes.entity.Frete;

public interface FreteRepository extends JpaRepository<Frete, Integer> {
}
