package com.treinamento.corridaSquad.biz;

import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.entities.Piloto;
import com.treinamento.corridaSquad.repositories.EquipeRepository;
import com.treinamento.corridaSquad.repositories.PilotoRepository;

public class PilotoBiz {
	private Mensagem mensagem;

	private EquipeRepository equipeRepository;

	public PilotoBiz(PilotoRepository pilotoRepository, EquipeRepository equipeRepository) {
		this.equipeRepository = equipeRepository;
		this.mensagem = new Mensagem();
	}

	public Boolean validar(Piloto piloto) {

		Boolean valido = true;

		if (piloto.getNome() == null || piloto.getNome().isEmpty() || piloto.getNome().isBlank()) {
			getMensagem().mensagem.add("O nome do piloto não pode estar vazia!");
			valido = false;
		} else if (piloto.getNome().length() > 255) {
			getMensagem().mensagem.add("O nome não pode conter mais que 255 caracteres!");
			valido = false;
		} else if (this.equipeRepository.findById(piloto.getId_equipe()).isEmpty()) {
			getMensagem().mensagem.add("A equipe não existe!");
			valido = false;
		}
		return valido;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

}
