package com.treinamento.corridaSquad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treinamento.corridaSquad.entities.Corrida;

@Repository
public interface CorridaRepository extends JpaRepository<Corrida, Integer>{

}
