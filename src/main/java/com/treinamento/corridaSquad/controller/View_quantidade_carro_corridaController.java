package com.treinamento.corridaSquad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.corridaSquad.entities.views.View_quantidade_carro_corrida;
import com.treinamento.corridaSquad.repositories.View_quantidade_carro_corridaRepository;

@RestController
@CrossOrigin
@RequestMapping("quantidade_carro_corrida")
public class View_quantidade_carro_corridaController {
	
	@Autowired
	private View_quantidade_carro_corridaRepository quantidade_carro_corridaRepository;
	
	@GetMapping("listar")
	public List<View_quantidade_carro_corrida> listar(){
		return quantidade_carro_corridaRepository.findAll();
	}
}
