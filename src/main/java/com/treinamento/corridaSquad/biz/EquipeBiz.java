package com.treinamento.corridaSquad.biz;

import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.entities.Equipe;

public class EquipeBiz {
	private Mensagem mensagem;
	
	public EquipeBiz() {
		this.mensagem = new Mensagem();
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
	
	public Boolean validar(Equipe equipe) {
		Boolean validacao = true;
		
		if(equipe.getNome().isEmpty()) {
			mensagem.getMensagem().add("O nome não pode ser vazio");
			validacao = false;
		}else if(equipe.getNome().length() > 50) {
			mensagem.getMensagem().add("O nome não pode ser maior que 50 caracteres");
			validacao = false;
		}
		
		return validacao;
	}
}
