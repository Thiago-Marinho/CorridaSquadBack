package com.treinamento.corridaSquad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.corridaSquad.entities.views.View_corrida_equipe;
import com.treinamento.corridaSquad.repositories.View_corrida_equipeRepository;

@RequestMapping("ViewCorridaEquipe")
@RestController
@CrossOrigin
public class View_corrida_equipeController {

	@Autowired
	private View_corrida_equipeRepository view_corrida_equipeRepositorio;
	
	@GetMapping("listar")
	public List<View_corrida_equipe> listar(){
		List<View_corrida_equipe> lista = view_corrida_equipeRepositorio.findAll();
		return lista;
	}
}
