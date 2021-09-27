package com.treinamento.corridaSquad.biz;

import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.entities.CorridaCarroPiloto;
import com.treinamento.corridaSquad.repositories.CarroRepository;
import com.treinamento.corridaSquad.repositories.CorridaRepository;
import com.treinamento.corridaSquad.repositories.PilotoRepository;

public class CorridaCarroPilotoBiz {
	private Mensagem mensagem;
	private CorridaRepository corridaRepository;
	private CarroRepository carroRepository;
	private PilotoRepository pilotoRepository;
	
	public CorridaCarroPilotoBiz(CorridaRepository corridaRepository, CarroRepository carroRepository,
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
	
	public Boolean validar(CorridaCarroPiloto corridaCarroPiloto) {
		Boolean validacao = true;
		
		if(corridaCarroPiloto.getId_carro() == null) {
			mensagem.getMensagem().add("O Carro não deve ser vazio!");
			validacao = false;
		}else if(carroRepository.findById(corridaCarroPiloto.getId_carro()).isEmpty()) {
			mensagem.getMensagem().add("O carro não está cadastrado!");
			validacao = false;
		}
		
		if(corridaCarroPiloto.getId_corrida() == null) {
			mensagem.getMensagem().add("A corrida não deve ser vazio!");
			validacao = false;
		}else if(corridaRepository.findById(corridaCarroPiloto.getId_corrida()).isEmpty()) {
			mensagem.getMensagem().add("A corrida não está cadastrada!");
			validacao = false;
		}
		
		if(corridaCarroPiloto.getId_piloto() == null) {
			mensagem.getMensagem().add("O piloto não deve ser vazio!");
			validacao = false;
		}else if(pilotoRepository.findById(corridaCarroPiloto.getId_piloto()).isEmpty()) {
			mensagem.getMensagem().add("O Piloto não está cadastrado!");
			validacao = false;
		}
		
		return validacao;
	}
}
