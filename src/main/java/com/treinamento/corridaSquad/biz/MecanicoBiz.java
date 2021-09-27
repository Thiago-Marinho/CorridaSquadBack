package com.treinamento.corridaSquad.biz;

import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.entities.Mecanico;
import com.treinamento.corridaSquad.repositories.EquipeRepository;
import com.treinamento.corridaSquad.repositories.MecanicoRepository;

public class MecanicoBiz {

	private Mensagem mensagem;

	private EquipeRepository equipeRepository;

	public MecanicoBiz(MecanicoRepository mecanicoRepository, EquipeRepository equipeRepository) {
		this.equipeRepository = equipeRepository;
		this.mensagem = new Mensagem();
	}

	public Boolean validar(Mecanico mecanico) {

		Boolean valido = true;

		if (mecanico.getNome() == null || mecanico.getNome().isEmpty() || mecanico.getNome().isBlank()) {
			getMensagem().mensagem.add("O nome do mecanico não pode estar vazia!");
			valido = false;
			
		} else if (mecanico.getNome().length() > 255) {
			getMensagem().mensagem.add("O nome não pode conter mais que 255 caracteres!");
			valido = false;
			
		} else if (equipeRepository.findById(mecanico.getId_equipe()).isEmpty()) {
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
