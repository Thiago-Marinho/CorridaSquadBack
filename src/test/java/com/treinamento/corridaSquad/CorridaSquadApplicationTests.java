package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CorridaSquadApplicationTests {

	@Test
	void contextLoads() {
		Integer expected = 10;
		Integer result = 10;
		
		assertThat(expected).isEqualTo(result);
	}
	


}
