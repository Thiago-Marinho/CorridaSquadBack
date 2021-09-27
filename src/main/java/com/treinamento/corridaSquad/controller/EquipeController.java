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
import com.treinamento.corridaSquad.biz.EquipeBiz;
import com.treinamento.corridaSquad.entities.Equipe;
import com.treinamento.corridaSquad.repositories.EquipeRepository;

@RestController
@RequestMapping("equipe")
@CrossOrigin
public class EquipeController {
	@Autowired
	private EquipeRepository equipeRepository;
	
	@GetMapping("listar")
	public List<Equipe> listarEquipe(){
		return equipeRepository.findAll();
	}	
	
	@PostMapping("incluir")
	public Mensagem incluirEquipe(@Validated @RequestBody Equipe equipe) {
		EquipeBiz equipeBiz = new EquipeBiz();
		try {
			if (equipeBiz.validar(equipe)) {
				equipeRepository.save(equipe);
				equipeRepository.flush();
				equipeBiz.getMensagem().mensagem.add("Equipe adicionada");
			}
		} catch (Exception e) {
			equipeBiz.getMensagem().mensagem.add("Erro ao Incluir:" + e.getMessage());
		}
		return equipeBiz.getMensagem();
	}
	
	@PutMapping(path="alterar/{id}")
    public Mensagem alterarEquipe(@PathVariable Integer id, @RequestBody @Validated Equipe equipe) {
        
    	EquipeBiz equipeBiz = new EquipeBiz();
    	equipe.setId(id);
        try{
            if(equipeBiz.validar(equipe)) {
            	equipeRepository.save(equipe);
                equipeRepository.flush();
                equipeBiz.getMensagem().mensagem.add("Equipe alterado com sucesso");
            }else {
            	equipeBiz.getMensagem().mensagem.add("Erro ao alterar");
            }
        	
        }catch(Exception e) {
            equipeBiz.getMensagem().mensagem.add("Erro ao alterar: " + e.getMessage());
        }
        return equipeBiz.getMensagem();
    }
}
