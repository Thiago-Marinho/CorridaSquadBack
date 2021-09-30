package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.treinamento.corridaSquad.controller.PilotoController;
import com.treinamento.corridaSquad.entities.Piloto;
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
		
		Integer expected = (int) pilotoRepository.findById(1).get().getId();
		Piloto result = this.controller.getOne(expected);

		assertThat(expected).isEqualTo(result.getId());
		assertThat(expected).isEqualTo(result.getNome());

	}
	@Test
	public void PilotoControllerAlterarTest() {

        List<Piloto> ListPiloto = this.pilotoRepository.findAll();
        Piloto pilotoAntigo = ListPiloto.get(1);
        String expectedName = "Veteu";

        Piloto pilotoAtualizado = new Piloto();
        pilotoAtualizado.setId(pilotoAntigo.getId());
        pilotoAtualizado.setNome(expectedName);
        pilotoAtualizado.setId_equipe(pilotoAntigo.getId_equipe());

        controller.alterarPiloto(pilotoAtualizado);
        pilotoAntigo  = controller.getOne(pilotoAntigo.getId());

        String resultName = pilotoAntigo.getNome();
        assertThat(expectedName).isEqualTo(resultName);
    }
}
