package com.treinamento.corridaSquad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treinamento.corridaSquad.entities.Piloto;

@Repository
public interface PilotoRepository extends JpaRepository<Piloto, Integer> {
	
}
