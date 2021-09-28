package com.treinamento.corridaSquad.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.biz.MecanicoBiz;
import com.treinamento.corridaSquad.entities.Equipe;
import com.treinamento.corridaSquad.entities.Mecanico;
import com.treinamento.corridaSquad.repositories.EquipeRepository;
import com.treinamento.corridaSquad.repositories.MecanicoRepository;

@RestController
@CrossOrigin
@RequestMapping("mecanico")
public class MecanicoController {

	@Autowired
	private MecanicoRepository mecanicoRepositorio;
	@Autowired
	private EquipeRepository equipeRepositorio;

	@GetMapping("listar")
	public List<Mecanico> listar() {
		List<Mecanico> lista = mecanicoRepositorio.findAll();
		return lista;
	}
	
	@CrossOrigin
	@GetMapping("/{id}")
	public Mecanico consultar(@PathVariable Integer id) {
    	return mecanicoRepositorio.findById(id).get();
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
	public Mensagem alterar(@RequestBody Mecanico mecanico) {

		MecanicoBiz validador = new MecanicoBiz(mecanicoRepositorio, equipeRepositorio);
		try {
			if (validador.validar(mecanico)) {
			mecanicoRepositorio.save(mecanico);
			mecanicoRepositorio.flush();			
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
