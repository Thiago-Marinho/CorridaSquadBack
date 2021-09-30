package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.treinamento.corridaSquad.controller.ViewCorridaEquipeController;
import com.treinamento.corridaSquad.repositories.ViewCorridaEquipeRepository;

@SpringBootTest
public class ViewCorridaEquipeTest {
	
	 @Autowired
	 ViewCorridaEquipeController viewCorridaEquipeController;
	 @Autowired
	 ViewCorridaEquipeRepository viewCorridaEquipeRepository;
	 
	 @Test
	 public void ViewCorridaEquipeListarTest(){
		 Integer expected = 0;
		 Integer result = 0;

		 expected = (int)viewCorridaEquipeRepository.count();
		 result = viewCorridaEquipeController.listar().size();
		 assertThat(expected).isEqualTo(result);
	    }

}
