package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.treinamento.corridaSquad.biz.CarroCorridaPilotoBiz;
import com.treinamento.corridaSquad.controller.CarroCorridaPilotoController;
import com.treinamento.corridaSquad.entities.CarroCorridaPiloto;
import com.treinamento.corridaSquad.repositories.CarroCorridaPilotoRepository;
import com.treinamento.corridaSquad.repositories.CarroRepository;
import com.treinamento.corridaSquad.repositories.CorridaRepository;
import com.treinamento.corridaSquad.repositories.PilotoRepository;

@SpringBootTest
public class CarroCorridaPilotoTest {
	
	@Autowired
	CarroCorridaPilotoRepository carroCorridaPilotoRepository;
	
	@Autowired
	CorridaRepository corridaRepository;
	
	@Autowired
	CarroRepository carroRepository;
	
	@Autowired
	PilotoRepository pilotoRepository;
	
	@Autowired 
	CarroCorridaPilotoController carroCorridaPilotoController;
	
    @Test
    public void CarroCorridaPilotoControllerListarTest() {
        Integer expected = 0;
        expected = (int) this.carroCorridaPilotoRepository.count();
        Integer result = this.carroCorridaPilotoController.listarCarroCorridaPiloto().size();
        assertThat(expected).isEqualTo(result);
    }
    
    @Test
    public void CarroCorridaPilotoControllerConsultarTest() {

    	CarroCorridaPiloto expected = this.obterPrimeiroRegistro();
        CarroCorridaPiloto result = this.carroCorridaPilotoController.consultar(expected.getId());
        assertThat(expected.getId()).isEqualTo(result.getId());
 
    }

    @Test
    public void CarroCorridaPilotoControllerInserirTest() {
        Integer expected = (int) this.carroCorridaPilotoRepository.count()+1;
        CarroCorridaPiloto novoCarroCorridaPiloto = new CarroCorridaPiloto();
        novoCarroCorridaPiloto.setId_carro(1);
        novoCarroCorridaPiloto.setId_corrida(1);
        novoCarroCorridaPiloto.setId_piloto(1);
        this.carroCorridaPilotoController.incluir(novoCarroCorridaPiloto);
        Integer result = this.carroCorridaPilotoController.listarCarroCorridaPiloto().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void CarroCorridaPilotoControllerAlterarTest() {
    	
    	Boolean expected = true;
    	Boolean result = false;
    	
    	CarroCorridaPiloto carroCorridaPilotoUpdate = obterPrimeiroRegistro();
    	
        carroCorridaPilotoUpdate.setId_carro(3);
        carroCorridaPilotoUpdate.setId_corrida(3);
        carroCorridaPilotoUpdate.setId_piloto(3);
        Mensagem msg = this.carroCorridaPilotoController.alterar(carroCorridaPilotoUpdate);
        
        if (msg.ContemErro()){
        	result = false;
        } else {
	        CarroCorridaPiloto carroCorridaPiloto  = 
	        		carroCorridaPilotoController.consultar(carroCorridaPilotoUpdate.getId());
    	 
	        if (  carroCorridaPiloto.getId() == carroCorridaPilotoUpdate.getId() &&
	        		carroCorridaPiloto.getId_carro() == carroCorridaPilotoUpdate.getId_carro() && 
	        		carroCorridaPiloto.getId_corrida() == carroCorridaPilotoUpdate.getId_corrida() &&
	        		carroCorridaPiloto.getId_piloto() == carroCorridaPilotoUpdate.getId_piloto()
	        	) {
	        	result = true;
	        }
        }
        assertThat(result).isEqualTo(expected);

    }
    
    @Test
    public void carroCorridaPilotoBizValidarTest() {
        
    	CarroCorridaPilotoBiz carroCorridaPilotoBiz = new CarroCorridaPilotoBiz(corridaRepository, carroRepository, pilotoRepository);
    	
    	Boolean result = true;
        Boolean expected = true;

        CarroCorridaPiloto carroCorridaPiloto = new CarroCorridaPiloto();
       
        // esperamos receber falso!
        carroCorridaPiloto.setId_carro(9999);
        if (carroCorridaPilotoBiz.validar(carroCorridaPiloto)) {
        	result = false;
        }
        
        // esperamos receber false!
        carroCorridaPiloto.setId_corrida(9999);
        if (carroCorridaPilotoBiz.validar(carroCorridaPiloto)) {
        	result = false;
        }

        // esperamos receber falso!
        carroCorridaPiloto.setId_piloto(9999);
        
        if (carroCorridaPilotoBiz.validar(carroCorridaPiloto)) {
        	result = false;
        }
        
        assertThat(result).isEqualTo(expected);

    }
    
    public CarroCorridaPiloto obterPrimeiroRegistro() {
    	Page<CarroCorridaPiloto> pagina = carroCorridaPilotoRepository.findAll(
    	    	PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id")));
    	return pagina.toList().get(0);
    }
   
}
