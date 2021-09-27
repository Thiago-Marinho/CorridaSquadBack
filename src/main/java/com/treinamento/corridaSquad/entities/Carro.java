package com.treinamento.corridaSquad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="carro")
public class Carro {
	
	@Id
	@GeneratedValue()
	@Column(name="id")
	private Integer id;
	
	@Column(name="id_equipe")
	private Integer id_equipe;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="placa")
	private String placa;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_equipe() {
		return id_equipe;
	}

	public void setId_equipe(Integer id_equipe) {
		this.id_equipe = id_equipe;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	

}
