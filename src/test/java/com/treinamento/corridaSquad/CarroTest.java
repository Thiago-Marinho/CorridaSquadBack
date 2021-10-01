package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import com.treinamento.corridaSquad.biz.CarroBiz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.treinamento.corridaSquad.controller.CarroController;
import com.treinamento.corridaSquad.entities.Carro;
import com.treinamento.corridaSquad.repositories.CarroRepository;
import com.treinamento.corridaSquad.repositories.EquipeRepository;

import java.util.List;

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
	@Test
	public void CarroBizTest(){
		CarroBiz carroBiz = new CarroBiz(equipeRepository);
		int idEquipeValido = equipeRepository.findAll().get(0).getId();
		boolean expected = true;
		Carro carro = new Carro();
		carro.setDescricao("TestDrivenCarro");
		carro.setId_equipe(idEquipeValido);
		carro.setNumero("ABCD");
		boolean result = carroBiz.validarCarro(carro);
		assertThat(result).isEqualTo(expected); //esperando por result=true

		carro.setDescricao("TestDrivenCarro2");
		carro.setId_equipe(idEquipeValido);
		carro.setNumero("DCBA");
		assertThat(result).isEqualTo(expected); //esperando por result=true

		//Começo de testes com carros inválidos
		expected=false;
		carro.setId_equipe(null);
		result = carroBiz.validarCarro(carro);
		assertThat(result).isEqualTo(expected); //esperando por result=false


		carro.setId_equipe(-33);
		result=carroBiz.validarCarro(carro);
		assertThat(result).isEqualTo(expected); //esperando por result=false


		carro.setId_equipe(idEquipeValido);
		carro.setNumero("12345678910");
		result=carroBiz.validarCarro(carro);
		assertThat(result).isEqualTo(expected); //esperando por result=false


		carro.setNumero("");
		result=carroBiz.validarCarro(carro);
		assertThat(result).isEqualTo(expected); //esperando por result=false

		carro.setNumero("1234");
		carro.setDescricao("");
		result=carroBiz.validarCarro(carro);
		assertThat(result).isEqualTo(expected); //esperando por result=false

		carro.setDescricao("123456789123456789123456789123456789123456789123456789"
						  +"123456789123456789123456789123456789123456789123456789"
				          +"123456789123456789123456789123456789123456789123456789"
				          +"123456789123456789123456789123456789123456789123456789"
				          +"123456789123456789123456789123456789123456789123456789"); //270 caracteres
		result=carroBiz.validarCarro(carro);
		assertThat(result).isEqualTo(expected); //esperando por result=false

	}
	
}
