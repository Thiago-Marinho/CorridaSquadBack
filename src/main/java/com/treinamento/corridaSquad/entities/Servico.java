package com.treinamento.corridaSquad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="servicos")
public class Servico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="id_mecanico")
	private Integer id_mecanico;
	
	@Column(name="id_carro")
	private Integer id_carro;
	
	@Column(name="descricao")
	private String descricao;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId_mecanico() {
		return id_mecanico;
	}

	public void setId_mecanico(Integer id_mecanico) {
		this.id_mecanico = id_mecanico;
	}

	public Integer getId_carro() {
		return id_carro;
	}

	public void setId_carro(Integer id_carro) {
		this.id_carro = id_carro;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}
