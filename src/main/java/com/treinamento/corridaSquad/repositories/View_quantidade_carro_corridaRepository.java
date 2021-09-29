package com.treinamento.corridaSquad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treinamento.corridaSquad.entities.views.View_quantidade_carro_corrida;

@Repository
public interface View_quantidade_carro_corridaRepository extends JpaRepository<View_quantidade_carro_corrida, Integer>{
	
}
