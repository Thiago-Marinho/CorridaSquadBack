package com.treinamento.corridaSquad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.biz.PilotoBiz;
import com.treinamento.corridaSquad.entities.Piloto;
import com.treinamento.corridaSquad.repositories.EquipeRepository;
import com.treinamento.corridaSquad.repositories.PilotoRepository;

@RestController
@CrossOrigin
@RequestMapping("piloto")
public class PilotoController {

	@Autowired
	private PilotoRepository pilotoRepositorio;
	@Autowired
	private EquipeRepository equipeRepositorio;

	@GetMapping("listar")
	public List<Piloto> listarPiloto() {
		List<Piloto> lista = pilotoRepositorio.findAll();
		return lista;
	}

	@GetMapping("/{id}")
	public Piloto getOne(@PathVariable int id){
		return pilotoRepositorio.findById(id).get();
	}

	@PostMapping("incluir")
	public Mensagem incluir(@RequestBody Piloto novoPiloto) {

		PilotoBiz validador = new PilotoBiz(pilotoRepositorio, equipeRepositorio);

		try {
			if (validador.validar(novoPiloto)) {
				pilotoRepositorio.save(novoPiloto);
				pilotoRepositorio.flush();
				validador.getMensagem().mensagem.add("Incluido com sucesso");
			}
		} catch (Exception e) {
			validador.getMensagem().mensagem.add("Erro ao Incluir:" + e.getMessage());
		}

		return validador.getMensagem();
	}

	@PutMapping("alterar")
	public Mensagem alterarPiloto(@RequestBody Piloto piloto) {

		PilotoBiz validador = new PilotoBiz(pilotoRepositorio, equipeRepositorio);
		try {
			if (validador.validar(piloto)) {
			pilotoRepositorio.save(piloto);
						
			validador.getMensagem().mensagem.add("Alterado com sucesso");
			}else {
            	validador.getMensagem().mensagem.add("Erro ao alterar");
            }
			
		} catch (Exception e) {
			validador.getMensagem().mensagem.add("Erro ao alterar: " + e.getMessage());
		}
		return validador.getMensagem();
	}
}
