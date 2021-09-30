package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.treinamento.corridaSquad.controller.ViewMecanicoAuxiliarController;
import com.treinamento.corridaSquad.repositories.ViewMecanicoAuxiliarRepository;

@SpringBootTest
public class ViewMecanicoAuxiliarTest {
	
	 @Autowired
	 ViewMecanicoAuxiliarController viewMecanicoAuxiliarController;
	 @Autowired
	 ViewMecanicoAuxiliarRepository viewMecanicoAuxiliarRepository;
	 
	 @Test
	 public void ViewMecanicoAuxiliarListarTest(){
		 Integer expected = 0;
		 Integer result = 0;

		 expected = (int)viewMecanicoAuxiliarRepository.count();
		 result = viewMecanicoAuxiliarController.listarViewMecanicoAuxiliar().size();
		 assertThat(expected).isEqualTo(result);
	    }

}
