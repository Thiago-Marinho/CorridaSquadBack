package com.treinamento.corridaSquad.biz;

import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.entities.Corrida;
import com.treinamento.corridaSquad.repositories.CorridaRepository;

public class CorridaBiz {
	
	private CorridaRepository corridaRepository;
	
	private Mensagem msg;
	
	public CorridaBiz(CorridaRepository corridaRepository) {
		this.msg = new Mensagem();
		this.corridaRepository = corridaRepository;
	}
	

	public Boolean validar(Corrida corrida) {
		Boolean valido = true;
		
		if(corrida.getDescricao() == null || corrida.getDescricao().isEmpty()) {
			msg.mensagem.add("O nome nao pode estar vazio");
			valido = false;
		} else if (corrida.getDescricao().length() > 255) {
			msg.mensagem.add("Nao pode conter mais que 255 letras");
		}
		if(corrida.getDescricao().isBlank()) {
			msg.mensagem.add("O nome nao pode ser composto somente por espaço");
			valido = false;
		}
		
		return valido;
	}


	public CorridaRepository getCorridaRepository() {
		return corridaRepository;
	}


	public void setCorridaRepository(CorridaRepository corridaRepository) {
		this.corridaRepository = corridaRepository;
	}


	public Mensagem getMsg() {
		return msg;
	}


	public void setMsg(Mensagem msg) {
		this.msg = msg;
	}
	
	

}
