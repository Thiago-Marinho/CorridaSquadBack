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
import com.treinamento.corridaSquad.biz.AuxiliarBiz;
import com.treinamento.corridaSquad.biz.MecanicoBiz;
import com.treinamento.corridaSquad.entities.Auxiliar;
import com.treinamento.corridaSquad.repositories.AuxiliarRepository;
import com.treinamento.corridaSquad.repositories.EquipeRepository;
import com.treinamento.corridaSquad.repositories.MecanicoRepository;

@RestController
@CrossOrigin
@RequestMapping("auxiliar")
public class AuxiliarController {

	@Autowired
	private AuxiliarRepository auxiliarRepositorio;
	@Autowired
	private EquipeRepository equipeRepositorio;
	@Autowired
	private MecanicoRepository mecanicoRepositorio;
		
	@GetMapping("listar")
	public List<Auxiliar> listarAuxiliar() {
		List<Auxiliar> lista = auxiliarRepositorio.findAll();
		return lista;
	}
	
	@PostMapping("incluir")
	public Mensagem incluir(@RequestBody Auxiliar novoAuxiliar) {

		AuxiliarBiz validador = new AuxiliarBiz(auxiliarRepositorio, equipeRepositorio, mecanicoRepositorio);

		try {
			if (validador.validar(novoAuxiliar)) {
				auxiliarRepositorio.save(novoAuxiliar);
				auxiliarRepositorio.flush();
				validador.getMensagem().mensagem.add("Incluido com sucesso");
			}
		} catch (Exception e) {
			validador.getMensagem().mensagem.add("Erro ao Incluir:" + e.getMessage());
		}

		return validador.getMensagem();
	}

	@PutMapping("alterar")
	public Mensagem alterarAuxiliar(@RequestBody Auxiliar auxiliar) {

		AuxiliarBiz validador = new AuxiliarBiz(auxiliarRepositorio, equipeRepositorio, mecanicoRepositorio);
		try {
			if (validador.validar(auxiliar)) {
			auxiliarRepositorio.save(auxiliar);
			auxiliarRepositorio.flush();			
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
