package com.treinamento.corridaSquad;


import com.treinamento.corridaSquad.controller.AuxiliarController;
import com.treinamento.corridaSquad.entities.Auxiliar;
import com.treinamento.corridaSquad.repositories.AuxiliarRepository;
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

    @Test
    public void AuxiliarControllerListarTest() {
        Integer expected = 0;
        expected = (int) this.auxiliarRepository.count();
        Integer result = this.auxiliarController.listarAuxiliar().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void AuxiliarControllerConsultarTest() {
        List<Auxiliar> ListAuxiliar = this.auxiliarRepository.findAll();
        Auxiliar expectedObject = ListAuxiliar.get(1);
        Auxiliar resultObject = this.auxiliarController.consultar(expectedObject.getId());
        assertThat(expectedObject.getId()).isEqualTo(resultObject.getId());
        assertThat(expectedObject.getNome()).isEqualTo(resultObject.getNome());
        assertThat(expectedObject.getId_mecanico()).isEqualTo(resultObject.getId_mecanico());
    }

    @Test
    public void AuxiliarControllerInserirTest() {
        Integer expected = (int) this.auxiliarRepository.count()+1;
        Auxiliar novoAuxiliar = new Auxiliar();
        novoAuxiliar.setNome("Carlos");
        novoAuxiliar.setId_mecanico(2);
        this.auxiliarController.incluir(novoAuxiliar);
        Integer result = this.auxiliarController.listarAuxiliar().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void AuxiliarControllerAlterarTest() {

        List<Auxiliar> ListAuxiliar = this.auxiliarRepository.findAll();
        Auxiliar auxiliarAntigo = ListAuxiliar.get(1);
        String expectedName = "Marcelinho";

        Auxiliar auxiliarAtualizado = new Auxiliar();
        auxiliarAtualizado.setId(auxiliarAntigo.getId());
        auxiliarAtualizado.setNome("Marcelinho");
        auxiliarAtualizado.setId_mecanico(auxiliarAntigo.getId_mecanico());

        this.auxiliarController.alterarAuxiliar(auxiliarAtualizado);
        auxiliarAntigo = ListAuxiliar.get(1);

        String resultName = auxiliarAntigo.getNome();
        assertThat(expectedName).isEqualTo(resultName);
    }

}
