package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.treinamento.corridaSquad.controller.EquipeController;
import com.treinamento.corridaSquad.entities.Equipe;
import com.treinamento.corridaSquad.repositories.EquipeRepository;

@SpringBootTest
public class EquipeTest {

	@Autowired
	EquipeRepository equipeRepository;
	
	@Autowired 
	EquipeController equipeController;
	
    @Test
    public void EquipeControllerListarTest() {
        Integer expected = 0;
        expected = (int) this.equipeRepository.count();
        Integer result = this.equipeController.listarEquipe().size();
        assertThat(expected).isEqualTo(result);
    }
    
    @Test
    public void EquipeControllerConsultarTest() {
        List<Equipe> ListaEquipe = this.equipeRepository.findAll();
        Equipe expected = ListaEquipe.get(1);
        Equipe result = this.equipeController.consultar(expected.getId());
        assertThat(expected.getId()).isEqualTo(result.getId());
        assertThat(expected.getNome()).isEqualTo(result.getNome());
    }

    @Test
    public void EquipeControllerInserirTest() {
        Integer expected = (int) this.equipeRepository.count()+1;
        Equipe novoEquipe = new Equipe();
        novoEquipe.setNome("Equipe de Testes");
        this.equipeController.incluirEquipe(novoEquipe);
        Integer result = this.equipeController.listarEquipe().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void EquipeControllerAlterarTest() {

        List<Equipe> ListaEquipe = this.equipeRepository.findAll();
        Equipe equipe = ListaEquipe.get(0);
        String expected = "Rocket";

        Equipe equipeUpdate = new Equipe();
        equipeUpdate.setId(equipe.getId());
        equipeUpdate.setNome("Rocket Filial");
        
        this.equipeController.alterarEquipe(equipeUpdate);
        equipe  = equipeController.consultar(equipe.getId());

        String result = equipe.getNome();
        assertThat(expected).isNotEqualTo(result);
    }
}
