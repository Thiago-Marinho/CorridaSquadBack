package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.treinamento.corridaSquad.entities.Carro;
import com.treinamento.corridaSquad.repositories.CarroRepository;
import com.treinamento.corridaSquad.repositories.EquipeRepository;

@SpringBootTest
class CorridaSquadApplicationTests {

	@Test
	void contextLoads() {
		Integer expected = 10;
		Integer result = 10;
		
		assertThat(expected).isEqualTo(result);
	}
	


}
