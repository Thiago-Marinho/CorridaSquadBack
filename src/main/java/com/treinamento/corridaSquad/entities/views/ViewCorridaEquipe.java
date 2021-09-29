package com.treinamento.corridaSquad.entities.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name = "View_corrida_equipe")
public class ViewCorridaEquipe {

	@Id
	@Column(name= "id")
	private Integer id;
	@Column(name= "corrida")
	private String corrida;
	@Column(name= "equipe")
	private String equipe;
	@Column(name= "carro")
	private String carro;
	
	public ViewCorridaEquipe() {}
	
	public ViewCorridaEquipe(Integer id, String corrida, String equipe, String carro) {
		this.id = id;
		this.corrida = corrida;
		this.equipe = equipe;
		this.carro = carro;
	}

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCorrida() {
		return corrida;
	}

	public void setCorrida(String corrida) {
		this.corrida = corrida;
	}

	public String getEquipe() {
		return equipe;
	}

	public void setEquipe(String equipe) {
		this.equipe = equipe;
	}

	public String getCarro() {
		return carro;
	}

	public void setCarro(String carro) {
		this.carro = carro;
	}
	
	
	
	
}
