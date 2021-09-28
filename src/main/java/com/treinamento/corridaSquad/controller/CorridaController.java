package com.treinamento.corridaSquad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.biz.CorridaBiz;
import com.treinamento.corridaSquad.entities.Corrida;
import com.treinamento.corridaSquad.repositories.CorridaRepository;

@RestController
@RequestMapping("corrida")
@CrossOrigin
public class CorridaController {
	
	@Autowired
	private CorridaRepository corridaRepository;
	
	@GetMapping("listar")
	public List<Corrida> listarCorrida(){
		List<Corrida> lista = corridaRepository.findAll();
		return lista;
	}
	
	@GetMapping("/{id}")
    public Corrida consultar(@PathVariable int id) {
        return corridaRepository.findById(id).get();
    }
	
	@PostMapping("incluir")
	public Mensagem incluirCorrida(@Validated @RequestBody Corrida corrida) {
		
		CorridaBiz corridaBiz = new CorridaBiz(this.corridaRepository);
		try {
			
			if (corridaBiz.validar(corrida)) {
				corridaRepository.save(corrida);
				corridaRepository.flush();
				corridaBiz.getMsg().getMensagem().add("OK");
			}
			
		} catch (Exception e) {
			corridaBiz.getMsg().getMensagem().add("Erro ao Incluir:" + e.getMessage());
		}
		return corridaBiz.getMsg();
	}
		@PutMapping("alterar/{id}")
	    public String alterarPiloto(@RequestBody @Validated Corrida corrida) {
	
	        try {
	            corridaRepository.save(corrida);
	            corridaRepository.flush();
	            return " Alterado com sucesso";
	        } catch (Exception e) {
	            return e.getMessage();
		}

		}
}


