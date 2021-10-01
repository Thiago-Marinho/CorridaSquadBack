package com.treinamento.corridaSquad;

import com.treinamento.corridaSquad.biz.MecanicoBiz;
import com.treinamento.corridaSquad.controller.MecanicoController;
import com.treinamento.corridaSquad.entities.Mecanico;
import com.treinamento.corridaSquad.repositories.EquipeRepository;
import com.treinamento.corridaSquad.repositories.MecanicoRepository;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MecanicoTest {
    @Autowired
    MecanicoController mecanicoController;
    @Autowired
    MecanicoRepository mecanicoRepository;
    @Autowired
    EquipeRepository equipeRepository;
    @Test
    public void mecanicoListarTest(){
        int expected = (int) mecanicoRepository.count();
        int result = (int) mecanicoController.listar().size();
        assertThat(result).isEqualTo(expected);
    }
    @Test
    public void mecanicoIncluirTest(){
        int expected = (int) mecanicoRepository.count()+1;
        Mecanico mecanico = new Mecanico();
        mecanico.setNome("TestMecanico");
        mecanico.setId_equipe(1);
        mecanicoController.incluir(mecanico);
        int result = (int) mecanicoRepository.count();
        assertThat(result).isEqualTo(expected);
    }
    @Test
    public void mecanicoConsultarTest(){
        boolean expected =true;
        List<Mecanico> listaMecanicos = mecanicoController.listar();
        Mecanico mecanicoExpected = (Mecanico) listaMecanicos.get(1);
        Mecanico mecanicoResult = (Mecanico) mecanicoController.consultar(mecanicoExpected.getId());
        boolean result = mecanicoResult.equals(mecanicoExpected);
        assertThat(mecanicoResult.getId()).isEqualTo(mecanicoExpected.getId());
        assertThat(mecanicoResult.getNome()).isEqualTo(mecanicoExpected.getNome());
        assertThat(mecanicoResult.getId_equipe()).isEqualTo(mecanicoExpected.getId_equipe());
    }
    @Test
    public void mecanicoAlterarTest(){
        Mecanico mecanico = new Mecanico();
        mecanico.setId_equipe(1);
        mecanico.setNome("TestMecanico");

        Integer savedMecanicoId = mecanicoRepository.save(mecanico).getId();

        mecanico.setNome("TestMecanico11");

        mecanicoController.alterar(mecanico);
        Mecanico resultMecanico = mecanicoController.consultar(mecanico.getId());
        assertThat(resultMecanico.getNome()).isEqualTo(mecanico.getNome());
    }
    @Test
    public void mecanicoBizTest(){
        MecanicoBiz mecanicoBiz = new MecanicoBiz(mecanicoRepository,equipeRepository);
        int idEquipeValido = equipeRepository.findAll().get(0).getId();
        Mecanico mecanico = new Mecanico();
        mecanico.setNome("TestMecanico");
        mecanico.setId_equipe(idEquipeValido);
        boolean result = mecanicoBiz.validar(mecanico);
        boolean expected = true;
        assertThat(result).isEqualTo(expected); //Esperando result=true

        expected=false;
        mecanico.setId_equipe(-1);
        result = mecanicoBiz.validar(mecanico);
        assertThat(result).isEqualTo(expected); //Esperando result=false

        mecanico.setId_equipe(idEquipeValido);
        mecanico.setNome("");
        result = mecanicoBiz.validar(mecanico);
        assertThat(result).isEqualTo(expected); //Esperando result=false
        
    }
}
