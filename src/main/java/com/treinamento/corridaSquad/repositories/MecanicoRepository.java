package com.treinamento.corridaSquad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treinamento.corridaSquad.entities.Mecanico;

@Repository
public interface MecanicoRepository extends JpaRepository<Mecanico, Integer>{

}
