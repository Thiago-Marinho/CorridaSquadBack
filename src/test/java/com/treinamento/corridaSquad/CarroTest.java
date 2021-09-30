package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.treinamento.corridaSquad.controller.CarroController;
import com.treinamento.corridaSquad.entities.Carro;
import com.treinamento.corridaSquad.repositories.CarroRepository;
import com.treinamento.corridaSquad.repositories.EquipeRepository;

@SpringBootTest
public class CarroTest {
	
	
	@Autowired
	CarroRepository carroRepository;
	@Autowired
	EquipeRepository equipeRepository;
	
	@Autowired
	CarroController controller;
	
	@Test
	public void CarroRepositoryTest() {
		
		Integer expected = 3;
		Integer result = (int) carroRepository.findById(3).get().getId();
		assertThat(expected).isEqualTo(result);
		
	}
	@Test 
	public void CarroRepositoryAdd() {
		Integer expected = (int) carroRepository.count()+1;
		Carro novo = new Carro();
		novo.setDescricao("Maclaren");
		novo.setId_equipe( equipeRepository.findById(1).get().getId()  );
		novo.setNumero("3464");
		carroRepository.save(novo);
		carroRepository.flush();
		Integer result = (int) carroRepository.count();
		assertThat(expected).isEqualTo(result);
	}
	
	@Test
	public void CarroControllerListarTest() {
		Integer expected = 0;
		Integer result = 0;

		expected = (int)carroRepository.count();
		result = controller.listar().size();
		assertThat(expected).isEqualTo(result);
		
	}
	
}
