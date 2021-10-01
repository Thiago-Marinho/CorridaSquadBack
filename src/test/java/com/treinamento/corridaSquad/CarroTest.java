package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.treinamento.corridaSquad.biz.CarroBiz;
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
	@Test
	public void CarroBizTest(){
		CarroBiz carroBiz = new CarroBiz(equipeRepository);
		int idEquipeValido = equipeRepository.findAll().get(0).getId();
		boolean expected = true;
		boolean result = true;
		Carro carro = new Carro();
		carro.setDescricao("TestDrivenCarro");
		carro.setId_equipe(idEquipeValido);
		carro.setNumero("ABCD");
		boolean teste = carroBiz.validarCarro(carro);
		if(!teste){
			result=false;
		}//esperando por teste=true

		carro.setDescricao("TestDrivenCarro2");
		carro.setId_equipe(idEquipeValido);
		carro.setNumero("DCBA");
		teste=carroBiz.validarCarro(carro);
		if(!teste){
			result=false;
		}//esperando por teste=true

		//Começo de testes com carros inválidos
		carro.setId_equipe(null);
		teste = carroBiz.validarCarro(carro);
		if(teste){
			result=false;
		} //esperando por teste=false


		carro.setId_equipe(-33);
		teste=carroBiz.validarCarro(carro);
		if(teste){
			result=false;
		} //esperando por teste=false


		carro.setId_equipe(idEquipeValido);
		carro.setNumero("12345678910");
		teste=carroBiz.validarCarro(carro);
		if(teste){
			result=false;
		} //esperando por teste=false


		carro.setNumero("");
		teste=carroBiz.validarCarro(carro);
		if(teste){
			result=false;
		} //esperando por teste=false

		carro.setNumero("1234");
		carro.setDescricao("");
		teste=carroBiz.validarCarro(carro);
		if(teste){
			result=false;
		} //esperando por teste=false

		carro.setDescricao("123456789123456789123456789123456789123456789123456789"
						  +"123456789123456789123456789123456789123456789123456789"
				          +"123456789123456789123456789123456789123456789123456789"
				          +"123456789123456789123456789123456789123456789123456789"
				          +"123456789123456789123456789123456789123456789123456789"); //270 caracteres
		teste=carroBiz.validarCarro(carro);
		if(teste){
			result=false;
		} //esperando por teste=false
		assertThat(result).isEqualTo(expected);

	}
	
}
