package com.treinamento.corridaSquad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Equipe extends JpaRepository<Equipe, Integer> {

}
