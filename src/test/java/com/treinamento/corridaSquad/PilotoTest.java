package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.treinamento.corridaSquad.biz.PilotoBiz;
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
		novo.setNome("Roberval Andrade");
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
		
		
        Piloto umPiloto =obterPrimeiroRegistro();
        Integer expected = umPiloto.getId();
        Piloto getPiloto = this.controller.getOne(expected);
        Integer result = getPiloto.getId();
        

		assertThat(result).isEqualTo(expected);

	}
	@Test
	public void PilotoControllerAlterarTest() {

		Boolean expected = true;
    	Boolean result = false;
    	
    	Piloto pilotoUpdate = obterPrimeiroRegistro();
    	
    	pilotoUpdate.setNome("Rubim");
    	pilotoUpdate.setId_equipe(3);
    
        Mensagem msg = this.controller.alterarPiloto(pilotoUpdate);
        
        if (msg.ContemErro()){
        	result = false;
        } else {
	        Piloto piloto  = controller.getOne(pilotoUpdate.getId());
    	 
	        if (piloto.getId() == pilotoUpdate.getId()) {
	        	result = true;
	        }
        }
        
        assertThat(result).isEqualTo(expected);
    }
	@Test
	public void PilotoBizValidarTest() {
		PilotoBiz pilotoBiz = new PilotoBiz(pilotoRepository ,equipeRepository);
		
        Boolean result = true;
        Boolean expected = true;

        Piloto piloto = new Piloto();
        piloto.setId_equipe(1);
        piloto.setNome("Vetel");
		boolean teste = pilotoBiz.validar(piloto);
		if(!teste){
			result=false;
		} //Esperando por teste=true;

		piloto.setNome("");
		teste = pilotoBiz.validar(piloto);
		if(teste){
			result=false;
		} //Esperando por teste=false;

		piloto.setNome("123456789123456789123456789123456789123456789123456789"
				+"123456789123456789123456789123456789123456789123456789"
				+"123456789123456789123456789123456789123456789123456789"
				+"123456789123456789123456789123456789123456789123456789"
				+"123456789123456789123456789123456789123456789123456789");
		teste = pilotoBiz.validar(piloto);
		if(teste){
			result=false;
		} //Esperando por teste=false;

        
        System.out.println(result);
        assertThat(result).isEqualTo(expected);
                                                     
    }
	
	public Piloto obterPrimeiroRegistro() {
    	Page<Piloto> pagina = pilotoRepository.findAll(
    	    	PageRequest.of(0, 1, Sort.by(Sort.Direction.ASC, "id")));
    	return pagina.toList().get(0);
    }
}
