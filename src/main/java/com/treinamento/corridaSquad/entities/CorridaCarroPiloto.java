package com.treinamento.corridaSquad.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carro_corrida_piloto")
public class CorridaCarroPiloto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "id_carro")
	private Integer id_carro;
	@Column(name = "id_corrida")
	private Integer id_corrida;
	@Column(name = "id_piloto")
	private Integer id_piloto;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getId_carro() {
		return id_carro;
	}
	public void setId_carro(Integer id_carro) {
		this.id_carro = id_carro;
	}
	public Integer getId_corrida() {
		return id_corrida;
	}
	public void setId_corrida(Integer id_corrida) {
		this.id_corrida = id_corrida;
	}
	public Integer getId_piloto() {
		return id_piloto;
	}
	public void setId_piloto(Integer id_piloto) {
		this.id_piloto = id_piloto;
	}
	
	
	
}
