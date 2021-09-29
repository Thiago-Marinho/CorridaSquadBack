package com.treinamento.corridaSquad.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.treinamento.corridaSquad.entities.views.ViewMecanicoAuxiliar;

@Repository
public interface ViewMecanicoAuxiliarRepository extends JpaRepository<ViewMecanicoAuxiliar, Integer> {

}
