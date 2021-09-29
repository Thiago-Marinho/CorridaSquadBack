package com.treinamento.corridaSquad.entities.views;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="view_quantidade_carro_corrida")
public class View_quantidade_carro_corrida {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="corrida")
	private String corrida;
	@Column(name="quant_carro")
	private Integer quant_carro;
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
	public Integer getQuant_carro() {
		return quant_carro;
	}
	public void setQuant_carro(Integer quant_carro) {
		this.quant_carro = quant_carro;
	}
}
