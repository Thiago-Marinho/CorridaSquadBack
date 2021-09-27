package com.treinamento.corridaSquad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treinamento.corridaSquad.entities.Servico;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Integer> {

}
