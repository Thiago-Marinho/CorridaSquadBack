package com.treinamento.corridaSquad;

import com.treinamento.corridaSquad.biz.AuxiliarBiz;
import com.treinamento.corridaSquad.controller.AuxiliarController;
import com.treinamento.corridaSquad.entities.Auxiliar;
import com.treinamento.corridaSquad.repositories.AuxiliarRepository;
import com.treinamento.corridaSquad.repositories.MecanicoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuxiliarTest {

    @Autowired
    AuxiliarRepository auxiliarRepository;
    @Autowired
    AuxiliarController auxiliarController;
    @Autowired
    MecanicoRepository mecanicoRepository;

    AuxiliarBiz auxiliarBiz = new AuxiliarBiz(mecanicoRepository);

    @Test
    public void auxiliarControllerListarTest() {
        Integer expected = 0;
        expected = (int) this.auxiliarRepository.count();
        Integer result = this.auxiliarController.listarAuxiliar().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void auxiliarControllerConsultarTest() {
        List<Auxiliar> ListAuxiliar = this.auxiliarRepository.findAll();
        Auxiliar expectedObject = ListAuxiliar.get(1);
        Auxiliar resultObject = this.auxiliarController.consultar(expectedObject.getId());
        assertThat(expectedObject.getId()).isEqualTo(resultObject.getId());
        assertThat(expectedObject.getNome()).isEqualTo(resultObject.getNome());
        assertThat(expectedObject.getId_mecanico()).isEqualTo(resultObject.getId_mecanico());
    }

    @Test
    public void auxiliarControllerInserirTest() {
        Integer expected = (int) this.auxiliarRepository.count()+1;
        Auxiliar novoAuxiliar = new Auxiliar();
        novoAuxiliar.setNome("Carlos");
        novoAuxiliar.setId_mecanico(2);
        this.auxiliarController.incluir(novoAuxiliar);
        Integer result = this.auxiliarController.listarAuxiliar().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void auxiliarControllerAlterarTest() {

        List<Auxiliar> ListAuxiliar = this.auxiliarRepository.findAll();
        Auxiliar auxiliarAntigo = ListAuxiliar.get(1);
        String expectedName = "Laura";

        Auxiliar auxiliarAtualizado = new Auxiliar();
        auxiliarAtualizado.setId(auxiliarAntigo.getId());
        auxiliarAtualizado.setNome(expectedName);
        auxiliarAtualizado.setId_mecanico(auxiliarAntigo.getId_mecanico());

        this.auxiliarController.alterarAuxiliar(auxiliarAtualizado);
        auxiliarAntigo  = auxiliarController.consultar(auxiliarAntigo.getId());

        String resultName = auxiliarAntigo.getNome();
        assertThat(expectedName).isEqualTo(resultName);
    }

    @Test
    public void auxiliarBizValidarTest() {
        Boolean result = true;
        Boolean expected = false;

        Auxiliar auxiliar = new Auxiliar();
       
        // esperamos receber falso!
        auxiliar.setId_mecanico(10000);
        auxiliar.setNome("");
        result = this.auxiliarBiz.validar(auxiliar);
        assertThat(result).isEqualTo(expected);

        // esperamos receber falso!
        auxiliar.setId_mecanico(1);
        auxiliar.setNome("");
        result = this.auxiliarBiz.validar(auxiliar);
        assertThat(result).isEqualTo(expected);

        // esperamos receber falso!
        auxiliar.setId_mecanico(10000);
        auxiliar.setNome("Carlos");
        result = this.auxiliarBiz.validar(auxiliar);
        assertThat(result).isEqualTo(expected);

        //esperamos receber trues!
        expected = true;
        auxiliar.setId_mecanico(1);
        auxiliar.setNome("Carlos");
        result = this.auxiliarBiz.validar(auxiliar);
        assertThat(result).isEqualTo(expected);

    }

}
