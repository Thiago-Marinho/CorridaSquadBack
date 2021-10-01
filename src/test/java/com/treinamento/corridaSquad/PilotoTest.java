package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.treinamento.corridaSquad.biz.PilotoBiz;
import com.treinamento.corridaSquad.controller.PilotoController;
import com.treinamento.corridaSquad.entities.CarroCorridaPiloto;
import com.treinamento.corridaSquad.entities.Corrida;
import com.treinamento.corridaSquad.entities.Piloto;
import com.treinamento.corridaSquad.entities.Servico;
import com.treinamento.corridaSquad.repositories.EquipeRepository;
import com.treinamento.corridaSquad.repositories.PilotoRepository;
@SpringBootTest
public class PilotoTest {
	
	
	@Autowired
	PilotoRepository pilotoRepository;
	@Autowired
	EquipeRepository equipeRepository;

	@Autowired
	PilotoController controller;

	PilotoBiz pilotoBiz = new PilotoBiz(pilotoRepository ,equipeRepository);
	@Test
	public void PilotoRepositoryTest() {

		Integer expected = 1;
		Integer result = (int) pilotoRepository.findById(1).get().getId();
		assertThat(expected).isEqualTo(result);

	}

	@Test
	public void PilotoRepositoryInclirTest() {
		Integer expected = (int) this.pilotoRepository.count() + 1;
		
		Piloto novo = new Piloto();
		novo.setNome("Maclaren");
		novo.setId_equipe(equipeRepository.findById(1).get().getId());
		pilotoRepository.save(novo);
		pilotoRepository.flush();
		Integer result = (int) this.pilotoRepository.count();
		assertThat(expected).isEqualTo(result);
	}

	@Test
	public void PilotoControllerListarTest() {
		Integer expected = 0;
		Integer result = 0;

		expected = (int) pilotoRepository.count();
		result = controller.listarPiloto().size();
		assertThat(expected).isEqualTo(result);

	}

	@Test
	public void PilotoControllerConsultarTest() {
		
		List<Piloto> ListPiloto = this.pilotoRepository.findAll();
        Piloto expectedObject = ListPiloto.get(1);
        Piloto resultObject = this.controller.getOne(expectedObject.getId());


		assertThat(expectedObject.getId()).isEqualTo(resultObject.getId());
		assertThat(expectedObject.getNome()).isEqualTo(resultObject.getNome());

	}
	@Test
	public void PilotoControllerAlterarTest() {

        Piloto piloto = new Piloto();
        
        piloto.setNome("Vetel");
        piloto.setId_equipe(2);
        pilotoRepository.save(piloto).getId();

        piloto.setNome("Vetel Valocit");

        controller.alterarPiloto(piloto);
        Piloto result = controller.getOne(piloto.getId());
        assertThat(result.getNome()).isEqualTo(piloto.getNome());
    }
	@Test
	public void PilotoBizValidarTest() {
        Boolean result = false;
        Boolean expected = true;

        Piloto piloto = new Piloto();
       
        
        piloto.setId_equipe(1);
                
        result = this.pilotoBiz.validar(piloto);
        assertThat(expected).isEqualTo(result);
        
        
        piloto.setNome("Vetel VV");
                
        result = this.pilotoBiz.validar(piloto);
        assertThat(expected).isEqualTo(result);                 

    }
}
