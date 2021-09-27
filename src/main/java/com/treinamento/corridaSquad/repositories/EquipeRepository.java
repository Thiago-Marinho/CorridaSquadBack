package com.treinamento.corridaSquad.repositories;

import com.treinamento.corridaSquad.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Integer> {

}
