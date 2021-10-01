package com.treinamento.corridaSquad.biz;

import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.entities.CarroCorridaPiloto;
import com.treinamento.corridaSquad.repositories.CarroRepository;
import com.treinamento.corridaSquad.repositories.CorridaRepository;
import com.treinamento.corridaSquad.repositories.PilotoRepository;

public class CarroCorridaPilotoBiz {
	private Mensagem mensagem;
	private CorridaRepository corridaRepository;
	private CarroRepository carroRepository;
	private PilotoRepository pilotoRepository;
	
	public CarroCorridaPilotoBiz(CorridaRepository corridaRepository, CarroRepository carroRepository,
			PilotoRepository pilotoRepository) {
		this.corridaRepository = corridaRepository;
		this.carroRepository = carroRepository;
		this.pilotoRepository = pilotoRepository;
		this.mensagem = new Mensagem();
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
	
	public Boolean validar(CarroCorridaPiloto carroCorridaPiloto) {
		Boolean validacao = true;
		
		
		if(carroCorridaPiloto.getId_carro() == null) {
			mensagem.getMensagem().add("O Carro não deve ser vazio!");
			validacao = false;
		} else if(carroRepository.findById(carroCorridaPiloto.getId_carro()).isEmpty()) {
			mensagem.getMensagem().add("O carro não está cadastrado!");
			validacao = false;
		}
		
		if(carroCorridaPiloto.getId_corrida() == null) {
			mensagem.getMensagem().add("A corrida não deve ser vazio!");
			validacao = false;
		}else if(corridaRepository.findById(carroCorridaPiloto.getId_corrida()).isEmpty()) {
			mensagem.getMensagem().add("A corrida não está cadastrada!");
			validacao = false;
		}
		
		if(carroCorridaPiloto.getId_piloto() == null) {
			mensagem.getMensagem().add("O piloto não deve ser vazio!");
			validacao = false;
		}else if(pilotoRepository.findById(carroCorridaPiloto.getId_piloto()).isEmpty()) {
			mensagem.getMensagem().add("O Piloto não está cadastrado!");
			validacao = false;
		}
		
		return validacao;
	}
}
