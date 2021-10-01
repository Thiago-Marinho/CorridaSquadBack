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
import com.treinamento.corridaSquad.biz.CarroCorridaPilotoBiz;
import com.treinamento.corridaSquad.entities.CarroCorridaPiloto;
import com.treinamento.corridaSquad.repositories.CarroCorridaPilotoRepository;
import com.treinamento.corridaSquad.repositories.CarroRepository;
import com.treinamento.corridaSquad.repositories.CorridaRepository;
import com.treinamento.corridaSquad.repositories.PilotoRepository;

@RestController
@RequestMapping("carrocorridapiloto")
@CrossOrigin
public class CarroCorridaPilotoController {
	@Autowired
	private CarroCorridaPilotoRepository carroCorridaPilotoRepository;
	@Autowired
	private CorridaRepository corridaRepository;
	@Autowired
	private CarroRepository carroRepository;
	@Autowired
	private PilotoRepository pilotoRepository;
	
	@GetMapping("listar")
    public List<CarroCorridaPiloto> listarCarroCorridaPiloto()
    {
        List<CarroCorridaPiloto> lista = carroCorridaPilotoRepository.findAll();
        return lista;
    }
	
	@CrossOrigin
	@GetMapping("/{id}")
	public CarroCorridaPiloto consultar(@PathVariable Integer id) {
    	return carroCorridaPilotoRepository.findById(id).get();
    }
	
	@PostMapping("incluir")
    public Mensagem incluir(@RequestBody CarroCorridaPiloto carroCorridaPiloto)
    {
        CarroCorridaPilotoBiz carroCorridaPilotoBiz = new CarroCorridaPilotoBiz(corridaRepository,carroRepository, pilotoRepository);
        try{
            if(carroCorridaPilotoBiz.validar(carroCorridaPiloto)) {
            	carroCorridaPilotoRepository.save(carroCorridaPiloto);
            	carroCorridaPilotoRepository.flush();
            	carroCorridaPilotoBiz.getMensagem().mensagem.add("CorridaCarroPiloto cadastrado com sucesso!");
            }
        } catch(Exception e) {
        	carroCorridaPilotoBiz.getMensagem().mensagem.add("Erro ao incluir: " + e.getMessage());
        }
        return carroCorridaPilotoBiz.getMensagem();
    }
	
	@PutMapping("alterar")
	public Mensagem alterar(@RequestBody @Validated CarroCorridaPiloto carroCorridaPiloto) {
		CarroCorridaPilotoBiz carroCorridaPilotoBiz = new CarroCorridaPilotoBiz(corridaRepository, carroRepository,
				pilotoRepository);
		try {
			if (carroCorridaPilotoBiz.validar(carroCorridaPiloto)) {
				carroCorridaPilotoRepository.save(carroCorridaPiloto);
				carroCorridaPilotoRepository.flush();
				carroCorridaPilotoBiz.getMensagem().mensagem.add("CorridaCarroPiloto atualizado com sucesso!");
			} else {
				carroCorridaPilotoBiz.getMensagem().mensagem.add("Erro ao alterar!");
			}
		} catch (Exception e) {
			carroCorridaPilotoBiz.getMensagem().mensagem.add("Erro ao alterar: " + e.getMessage());
		}
		return carroCorridaPilotoBiz.getMensagem();
	}
}
