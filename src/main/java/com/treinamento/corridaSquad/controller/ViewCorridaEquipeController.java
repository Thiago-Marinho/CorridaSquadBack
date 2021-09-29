package com.treinamento.corridaSquad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.corridaSquad.entities.views.ViewCorridaEquipe;
import com.treinamento.corridaSquad.repositories.ViewCorridaEquipeRepository;

@RequestMapping("ViewCorridaEquipe")
@RestController
@CrossOrigin
public class ViewCorridaEquipeController {

	@Autowired
	private ViewCorridaEquipeRepository view_corrida_equipeRepositorio;
	
	@GetMapping("listar")
	public List<ViewCorridaEquipe> listar(){
		List<ViewCorridaEquipe> lista = view_corrida_equipeRepositorio.findAll();
		return lista;
	}
}
