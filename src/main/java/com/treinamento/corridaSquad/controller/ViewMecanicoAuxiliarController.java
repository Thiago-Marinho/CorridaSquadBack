package com.treinamento.corridaSquad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.corridaSquad.entities.views.ViewMecanicoAuxiliar;
import com.treinamento.corridaSquad.repositories.ViewMecanicoAuxiliarRepository;

@RestController
@RequestMapping("mecanicoauxiliar")
@CrossOrigin
public class ViewMecanicoAuxiliarController {
	
	@Autowired 
	private ViewMecanicoAuxiliarRepository viewMecanicoAuxiliarRepository;
	
	@GetMapping("listar")
	public List<ViewMecanicoAuxiliar> listarViewMecanicoAuxiliar(){
		List<ViewMecanicoAuxiliar> lista = viewMecanicoAuxiliarRepository.findAll();
		return lista;
	}

}
