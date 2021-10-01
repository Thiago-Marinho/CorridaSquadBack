package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.treinamento.corridaSquad.biz.CorridaBiz;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.treinamento.corridaSquad.controller.CorridaController;
import com.treinamento.corridaSquad.entities.Corrida;
import com.treinamento.corridaSquad.repositories.CorridaRepository;

@SpringBootTest
public class CorridaTest {
	
	 @Autowired
	 CorridaController corridaController;
	 @Autowired
	 CorridaRepository corridaRepository;

	 @Test
	 public void carroListarTest(){
		 Integer expected = 0;
		 Integer result = 0;

		 expected = (int)corridaRepository.count();
		 result = corridaController.listarCorrida().size();
		 assertThat(expected).isEqualTo(result);
	    }
	 
	 @Test 
	public void corridaIncluirAdd() {
		Integer expected = (int) corridaRepository.count()+1;
		Corrida novo = new Corrida();
		novo.setDescricao("Maclaren");
		corridaRepository.save(novo);
		corridaRepository.flush();
		Integer result = (int) corridaRepository.count();
		assertThat(expected).isEqualTo(result);
		}
	 
    @Test
    public void corridaConsultarTest() {
        List<Corrida> ListCorrida = this.corridaRepository.findAll();
        Corrida expectedObject = ListCorrida.get(1);
        Corrida resultObject = this.corridaController.consultar(expectedObject.getId());
        assertThat(expectedObject.getId()).isEqualTo(resultObject.getId());
        assertThat(expectedObject.getDescricao()).isEqualTo(resultObject.getDescricao());

	     }
    
    @Test
    public void corridaAlterarTest(){
        Corrida corrida = new Corrida();
        corrida.setId(1);
        corrida.setDescricao("Teste");

        Integer savedCorridaId = corridaRepository.save(corrida).getId();

        corrida.setDescricao("Teste Realizado");

        corridaController.alterarPiloto(corrida);
        Corrida result = corridaController.consultar(corrida.getId());
        assertThat(result.getDescricao()).isEqualTo(corrida.getDescricao());
    }
	@Test
	public void corridaBizTest(){
		CorridaBiz corridaBiz = new CorridaBiz(corridaRepository);
		Corrida corrida = new Corrida();
		corrida.setDescricao("TestDrivenCorrida");
		boolean result = corridaBiz.validar(corrida);
		boolean expected = true;
		assertThat(result).isEqualTo(expected); //Esperando por result=true

		corrida.setDescricao("Test Driven Corrida 2");
		result= corridaBiz.validar(corrida);
		assertThat(result).isEqualTo(expected); //Esperando por result=true

		//Inicio de testes com 'corrida' inválida
		expected = false;
		corrida.setDescricao(" ");
		result=corridaBiz.validar(corrida);
		assertThat(result).isEqualTo(expected); //Esperando por result=false


		corrida.setDescricao("");
		result=corridaBiz.validar(corrida);
		assertThat(result).isEqualTo(expected); //Esperando por result=false

	}
	 	
}
