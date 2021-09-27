package com.treinamento.corridaSquad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.corridaSquad.entities.Equipe;
import com.treinamento.corridaSquad.repositories.EquipeRepository;

@RestController
@RequestMapping("equipe")
@CrossOrigin("http://localhost:4200")
public class EquipeController {
	@Autowired
	private EquipeRepository equipeRepository;
	
	@GetMapping("listar")
	public List<Equipe> listarEquipe(){
		return equipeRepository.findAll();
	}	
}
