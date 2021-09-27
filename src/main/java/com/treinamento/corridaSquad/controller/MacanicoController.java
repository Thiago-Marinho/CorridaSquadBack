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
import com.treinamento.corridaSquad.biz.MecanicoBiz;
import com.treinamento.corridaSquad.entities.Mecanico;
import com.treinamento.corridaSquad.repositories.EquipeRepository;
import com.treinamento.corridaSquad.repositories.MecanicoRepository;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("mecanico")
public class MacanicoController {

	@Autowired
	private MecanicoRepository mecanicoRepositorio;
	@Autowired
	private EquipeRepository equipeRepositorio;

	@GetMapping("listar")
	public List<Mecanico> listar() {
		List<Mecanico> lista = mecanicoRepositorio.findAll();
		return lista;
	}

	@PostMapping("incluir")
	public Mensagem incluir(@RequestBody Mecanico novoMecanico) {

		MecanicoBiz validador = new MecanicoBiz(mecanicoRepositorio, equipeRepositorio);

		try {
			if (validador.validar(novoMecanico)) {
				mecanicoRepositorio.save(novoMecanico);
				mecanicoRepositorio.flush();
				validador.getMensagem().mensagem.add("Incluido com sucesso");
			}
		} catch (Exception e) {
			validador.getMensagem().mensagem.add("Erro ao Incluir:" + e.getMessage());
		}

		return validador.getMensagem();
	}

	@PutMapping("alterar")
	public String alterar(@RequestBody Mecanico mecanico) {

		try {
			mecanicoRepositorio.save(mecanico);
			mecanicoRepositorio.flush();
			return ("Alterado com Sucesso");
		} catch (Exception e) {
			return e.getMessage();
		}
	}
}
