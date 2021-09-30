package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.treinamento.corridaSquad.controller.View_quantidade_carro_corridaController;
import com.treinamento.corridaSquad.repositories.View_quantidade_carro_corridaRepository;

@SpringBootTest
public class View_quantidade_carro_corridaTest {
	
	@Autowired
	View_quantidade_carro_corridaController view_quantidade_carro_corridaController;
	 @Autowired
	 View_quantidade_carro_corridaRepository view_quantidade_carro_corridaRepository;

	 
	 @Test
	 public void ViewCorridaEquipeListarTest(){
		 Integer expected = 0;
		 Integer result = 0;

		 expected = (int) view_quantidade_carro_corridaRepository.count();
		 result = view_quantidade_carro_corridaController.listar().size();
		 assertThat(expected).isEqualTo(result);
	    }
}
