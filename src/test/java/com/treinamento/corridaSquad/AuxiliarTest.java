package com.treinamento.corridaSquad;

import com.treinamento.corridaSquad.biz.AuxiliarBiz;
import com.treinamento.corridaSquad.controller.AuxiliarController;
import com.treinamento.corridaSquad.entities.Auxiliar;
import com.treinamento.corridaSquad.entities.CarroCorridaPiloto;
import com.treinamento.corridaSquad.repositories.AuxiliarRepository;
import com.treinamento.corridaSquad.repositories.MecanicoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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

    @Test
    public void auxiliarControllerListarTest() {
        Integer expected = 0;
        expected = (int) this.auxiliarRepository.count();
        Integer result = this.auxiliarController.listarAuxiliar().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void auxiliarControllerConsultarTest() {
        Auxiliar expected= this.obterPrimeiroRegistro();
        Auxiliar result = this.auxiliarController.consultar(expected.getId());
        assertThat(expected.getId()).isEqualTo(result.getId());
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
        Boolean expected = true;
        Boolean result = false;

        Auxiliar auxiliarUpdate = this.obterPrimeiroRegistro();

        auxiliarUpdate.setNome("Lucas");
        auxiliarUpdate.setId_mecanico(1);
        Mensagem msg = this.auxiliarController.alterarAuxiliar(auxiliarUpdate);

        if(msg.ContemErro()) {
            result = false;
        } else {
            Auxiliar auxiliar = this.auxiliarController.consultar(auxiliarUpdate.getId());

            if(auxiliar.getId() == auxiliarUpdate.getId() && auxiliar.getId_mecanico() == auxiliarUpdate.getId_mecanico() && auxiliar.getNome().equals(auxiliarUpdate.getNome())) {
                result = true;
            }
        }

        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void auxiliarBizValidarTest() {
        AuxiliarBiz auxiliarBiz = new AuxiliarBiz(mecanicoRepository);
        Boolean result = true;
        Boolean expected = false;

        Auxiliar auxiliar = new Auxiliar();
       
        // esperamos receber falso!
        auxiliar.setId_mecanico(10000);
        auxiliar.setNome("");
        if(auxiliarBiz.validar(auxiliar)) result = false;

        // esperamos receber falso!
        auxiliar.setId_mecanico(1);
        auxiliar.setNome("");
        if(auxiliarBiz.validar(auxiliar)) result = false;

        // esperamos receber falso!
        auxiliar.setId_mecanico(10000);
        auxiliar.setNome("Carlos");
        if(auxiliarBiz.validar(auxiliar)) result = false;

        //esperamos receber true!
        expected = true;
        auxiliar.setId_mecanico(1);
        auxiliar.setNome("Carlos");
        if(auxiliarBiz.validar(auxiliar)) result = true;

        assertThat(result).isEqualTo(expected);
    }

    public Auxiliar obterPrimeiroRegistro() {
        Page<Auxiliar> pagina = auxiliarRepository.findAll(
                PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id")));
        return pagina.toList().get(0);
    }

}
