package com.treinamento.corridaSquad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="auxiliar")
public class Auxiliar {
	
	@Id
	@GeneratedValue()
	@Column(name="id")
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="id_mecanico")
	private Integer id_mecanico;
	
	@Column(name="id_equipe")
	private Integer id_equipe;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getId_mecanico() {
		return id_mecanico;
	}

	public void setId_mecanico(Integer id_mecanico) {
		this.id_mecanico = id_mecanico;
	}

	public Integer getId_equipe() {
		return id_equipe;
	}

	public void setId_equipe(Integer id_equipe) {
		this.id_equipe = id_equipe;
	}
	
	

}
