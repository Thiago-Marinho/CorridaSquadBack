package com.treinamento.corridaSquad.biz;

import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.entities.Auxiliar;
import com.treinamento.corridaSquad.repositories.AuxiliarRepository;
import com.treinamento.corridaSquad.repositories.EquipeRepository;
import com.treinamento.corridaSquad.repositories.MecanicoRepository;

public class AuxiliarBiz {

	private Mensagem mensagem;

	private EquipeRepository equipeRepository;
	private MecanicoRepository mecanicoRepository;

	public AuxiliarBiz(AuxiliarRepository pilotoRepository, EquipeRepository equipeRepository, MecanicoRepository mecanicoRepository) {
		this.equipeRepository = equipeRepository;
		this.mecanicoRepository = mecanicoRepository;
		this.mensagem = new Mensagem();
	}

	public Boolean validar(Auxiliar auxiliar) {

		Boolean valido = true;

		if (auxiliar.getNome() == null || auxiliar.getNome().isEmpty() || auxiliar.getNome().isBlank()) {
			getMensagem().mensagem.add("O nome do auxiliar não pode estar vazia!");
			valido = false;
		} else if (auxiliar.getNome().length() > 255) {
			getMensagem().mensagem.add("O nome não pode conter mais que 255 caracteres!");
			valido = false;
		} else if (mecanicoRepository.findById(auxiliar.getId_mecanico()).isEmpty()) {
			getMensagem().mensagem.add("O Mecanico não existe!");
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
