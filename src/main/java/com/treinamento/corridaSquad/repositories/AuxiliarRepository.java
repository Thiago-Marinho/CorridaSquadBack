package com.treinamento.corridaSquad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treinamento.corridaSquad.entities.Auxiliar;

@Repository
public interface AuxiliarRepository extends JpaRepository<Auxiliar, Integer>{

}
