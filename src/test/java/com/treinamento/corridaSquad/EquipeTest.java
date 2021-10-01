package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.treinamento.corridaSquad.biz.EquipeBiz;
import com.treinamento.corridaSquad.controller.EquipeController;
import com.treinamento.corridaSquad.entities.Equipe;
import com.treinamento.corridaSquad.repositories.EquipeRepository;

@SpringBootTest
public class EquipeTest {

	@Autowired
	EquipeRepository equipeRepository;

	@Autowired
	EquipeController equipeController;

	EquipeBiz equipeBiz = new EquipeBiz();

	@Test
	public void EquipeControllerListarTest() {
		Integer expected = 0;
		expected = (int) this.equipeRepository.count();
		Integer result = this.equipeController.listarEquipe().size();
		assertThat(expected).isEqualTo(result);
	}

	@Test
	public void EquipeControllerConsultarTest() {

		Equipe expected = this.obterPrimeiroRegistro();
		Equipe result = this.equipeController.consultar(expected.getId());
		assertThat(expected.getId()).isEqualTo(result.getId());

	}

	@Test
	public void EquipeControllerInserirTest() {
        Integer expected = (int) this.equipeRepository.count()+1;
        Equipe novaEquipe = new Equipe();
        novaEquipe.setNome("Equipe de Teste 2.0");
        this.equipeController.incluirEquipe(novaEquipe);
        Integer result = this.equipeController.listarEquipe().size();
        assertThat(expected).isEqualTo(result);
	}

	@Test
	public void EquipeControllerAlterarTest() {

    	Boolean expected = true;
    	Boolean result = false;
    	
    	Equipe equipeUpdate = obterPrimeiroRegistro();
    	
        equipeUpdate.setNome("Equipe Teste 2.0 Alterada");

        Mensagem msg = this.equipeController.incluirEquipe(equipeUpdate);
        
        if (msg.ContemErro()){
        	result = false;
        } else {
	        Equipe equipe  = 
	        		equipeController.consultar(equipeUpdate.getId());
    	 
	        if (  equipe.getId() == equipeUpdate.getId() &&
	        		equipe.getNome().equals(equipeUpdate.getNome())) {
	        	result = true;
	        }
        }
        assertThat(result).isEqualTo(expected);
	}

	@Test
	public void equipeBizValidarTest() {
	EquipeBiz equipeBiz = new EquipeBiz();
    	
    	Boolean result = true;
        Boolean expected = true;

        Equipe equipe = new Equipe();
       
        // esperamos receber falso!
        equipe.setNome("Lorem ipsum dolor sit amet, consectetur adipiscing elit. "
        		+ "Etiam maximus commodo nulla, at vestibulum neque aliquam sed. "
        		+ "Donec cursus, erat mollis dapibus egestas, metus nunc pretium nulla, "
        		+ "pretium sodales dui ligula sed nunc. "
        		+ "Vivamus porta nisi vitae augue cursus pulvinar. "
        		+ "Praesent vehicula vitae mauris non sollicitudin. "
        		+ "Vivamus condimentum imperdiet arcu, quis.");
        if (equipeBiz.validar(equipe)) {
        	result = false;
        }
        assertThat(result).isEqualTo(expected);
    }
	
    public Equipe obterPrimeiroRegistro() {
    	Page<Equipe> pagina = equipeRepository.findAll(
    	    	PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id")));
    	return pagina.toList().get(0);
    }
}
