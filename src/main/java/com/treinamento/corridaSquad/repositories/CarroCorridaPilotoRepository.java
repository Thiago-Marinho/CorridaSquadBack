package com.treinamento.corridaSquad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treinamento.corridaSquad.entities.CarroCorridaPiloto;

@Repository
public interface CarroCorridaPilotoRepository extends JpaRepository<CarroCorridaPiloto, Integer>{

}
