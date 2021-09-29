package com.treinamento.corridaSquad.entities.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="view_mecanico_auxiliar")
public class ViewMecanicoAuxiliar {

	@Id
	@GeneratedValue()
	@Column(name="id")
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="quantidade_auxiliares")
	private Integer quantidade_auxiliares;

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

	public Integer getQuantidade_auxiliares() {
		return quantidade_auxiliares;
	}

	public void setQuantidade_auxiliares(Integer quantidade_auxiliares) {
		this.quantidade_auxiliares = quantidade_auxiliares;
	}

	
	
	
}
