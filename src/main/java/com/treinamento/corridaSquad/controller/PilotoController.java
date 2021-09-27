package com.treinamento.corridaSquad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.biz.PilotoBiz;
import com.treinamento.corridaSquad.entities.Piloto;
import com.treinamento.corridaSquad.repositories.EquipeRepository;
import com.treinamento.corridaSquad.repositories.PilotoRepository;

@RestController
@CrossOrigin("http://localhost:4200")
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
	public String alterarPiloto(@RequestBody Piloto piloto) {

		try {
			pilotoRepositorio.save(piloto);
			pilotoRepositorio.flush();
			return ("Alterado com Sucesso");
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
