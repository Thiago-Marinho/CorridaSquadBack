package com.treinamento.corridaSquad;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
	
	CarroCorridaPilotoBiz carroCorridaPilotoBiz = new CarroCorridaPilotoBiz(corridaRepository, carroRepository, pilotoRepository);
	
    @Test
    public void CarroCorridaPilotoControllerListarTest() {
        Integer expected = 0;
        expected = (int) this.carroCorridaPilotoRepository.count();
        Integer result = this.carroCorridaPilotoController.listarCarroCorridaPiloto().size();
        assertThat(expected).isEqualTo(result);
    }
    
    @Test
    public void CarroCorridaPilotoControllerConsultarTest() {
        List<CarroCorridaPiloto> ListaCarroCorridaPiloto = this.carroCorridaPilotoRepository.findAll();
        CarroCorridaPiloto expected = ListaCarroCorridaPiloto.get(1);
        CarroCorridaPiloto result = this.carroCorridaPilotoController.consultar(expected.getId());
        assertThat(expected.getId()).isEqualTo(result.getId());
        assertThat(expected.getId_carro()).isEqualTo(result.getId_carro());
        assertThat(expected.getId_corrida()).isEqualTo(result.getId_corrida());
        assertThat(expected.getId_piloto()).isEqualTo(result.getId_piloto());
    }

    @Test
    public void CarroCorridaPilotoControllerInserirTest() {
        Integer expected = (int) this.carroCorridaPilotoRepository.count()+1;
        CarroCorridaPiloto novoCarroCorridaPiloto = new CarroCorridaPiloto();
        novoCarroCorridaPiloto.setId_carro(2);
        novoCarroCorridaPiloto.setId_corrida(1);
        novoCarroCorridaPiloto.setId_piloto(3);
        this.carroCorridaPilotoController.incluir(novoCarroCorridaPiloto);
        Integer result = this.carroCorridaPilotoController.listarCarroCorridaPiloto().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void CarroCorridaPilotoControllerAlterarTest() {

        List<CarroCorridaPiloto> ListaCarroCorridaPiloto = this.carroCorridaPilotoRepository.findAll();
        CarroCorridaPiloto carroCorridaPiloto = ListaCarroCorridaPiloto.get(3);
        Integer expectedIdCarro = carroCorridaPiloto.getId_carro();
        Integer expectedIdCorrida = carroCorridaPiloto.getId_corrida();
        Integer expectedIdPiloto= carroCorridaPiloto.getId_piloto();
        
        CarroCorridaPiloto carroCorridaPilotoUpdate = new CarroCorridaPiloto();
        carroCorridaPilotoUpdate.setId(carroCorridaPiloto.getId());
        carroCorridaPilotoUpdate.setId_carro(2);
        carroCorridaPilotoUpdate.setId_corrida(3);
        carroCorridaPilotoUpdate.setId_piloto(2);
       
        this.carroCorridaPilotoController.alterar(carroCorridaPilotoUpdate);
        carroCorridaPiloto  = carroCorridaPilotoController.consultar(carroCorridaPiloto.getId());

        Integer resultIdCarro = carroCorridaPiloto.getId_carro();
        Integer resultIdCorrida = carroCorridaPiloto.getId_corrida();
        Integer resultIdPiloto = carroCorridaPiloto.getId_piloto();
        assertThat(expectedIdCarro).isNotEqualTo(resultIdCarro);
        assertThat(expectedIdCorrida).isNotEqualTo(resultIdCorrida);
        assertThat(expectedIdPiloto).isNotEqualTo(resultIdPiloto);
    }
    
    @Test
    public void carroCorridaPilotoBizValidarTest() {
        Boolean result = true;
        Boolean expected = false;

        CarroCorridaPiloto carroCorridaPiloto = new CarroCorridaPiloto();
       
        // esperamos receber falso!
        carroCorridaPiloto.setId_carro(50);
                
        result = this.carroCorridaPilotoBiz.validar(carroCorridaPiloto);
        assertThat(result).isEqualTo(expected);
        
        // esperamos receber falso!
        carroCorridaPiloto.setId_corrida(60);
                
        result = this.carroCorridaPilotoBiz.validar(carroCorridaPiloto);
        assertThat(result).isEqualTo(expected);
        
        // esperamos receber falso!
        carroCorridaPiloto.setId_piloto(70);
        
        result = this.carroCorridaPilotoBiz.validar(carroCorridaPiloto);
        assertThat(result).isEqualTo(expected);

    }
}
