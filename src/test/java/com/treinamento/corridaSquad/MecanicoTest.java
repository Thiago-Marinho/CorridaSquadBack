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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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
        Mecanico mecanicoExpected = getPrimeiroMecanico();
        Mecanico mecanicoResult = (Mecanico) mecanicoController.consultar(mecanicoExpected.getId());
        boolean result = mecanicoResult.getId()==mecanicoExpected.getId();
        assertThat(result).isEqualTo(expected);
    }
    @Test
    public void mecanicoAlterarTest(){
        Mecanico mecanico = new Mecanico();
        boolean expected = true;
        mecanico.setId(getPrimeiroMecanico().getId());
        mecanico.setId_equipe(1);
        mecanico.setNome("TestMecanico");
        mecanicoController.alterar(mecanico);
        Mecanico resultMecanico = mecanicoController.consultar(mecanico.getId());
        boolean result = resultMecanico.getNome().equals(mecanico.getNome());
        assertThat(result).isEqualTo(expected);
    }
    @Test
    public void mecanicoBizTest(){
        boolean expected = true;
        boolean result = true;

        MecanicoBiz mecanicoBiz = new MecanicoBiz(mecanicoRepository,equipeRepository);
        int idEquipeValido = equipeRepository.findAll().get(0).getId();
        Mecanico mecanico = new Mecanico();
        mecanico.setNome("TestMecanico");
        mecanico.setId_equipe(idEquipeValido);
        boolean test = mecanicoBiz.validar(mecanico);
        if(!test){
            result=false;
        }

        mecanico.setId_equipe(-1);
        test = mecanicoBiz.validar(mecanico);
        if(test){
            result =false;
        }

        mecanico.setId_equipe(idEquipeValido);
        mecanico.setNome("");
        test = mecanicoBiz.validar(mecanico);
        if(test){
            result =false;
        }
        assertThat(result).isEqualTo(expected); //Esperando result=false
    }
    public Mecanico getPrimeiroMecanico(){
        return mecanicoRepository.findAll(
                PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id"))).toList().get(0);
    }
}
