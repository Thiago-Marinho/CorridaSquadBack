package com.treinamento.corridaSquad;

import com.treinamento.corridaSquad.biz.ServicoBiz;
import com.treinamento.corridaSquad.controller.ServicoController;
import com.treinamento.corridaSquad.entities.Servico;
import com.treinamento.corridaSquad.repositories.ServicoRepository;
import com.treinamento.corridaSquad.repositories.CarroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ServicoTest {

    @Autowired
    ServicoRepository servicoRepository;
    @Autowired
    ServicoController servicoController;
    @Autowired
    CarroRepository carroRepository;

    ServicoBiz servicoBiz = new ServicoBiz(carroRepository);

    @Test
    public void servicoControllerListarTest() {
        Integer expected = 0;
        expected = (int) this.servicoRepository.count();
        Integer result = this.servicoController.listarServico().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void servicoControllerConsultarTest() {
        List<Servico> ListServico = this.servicoRepository.findAll();
        Servico expectedObject = ListServico.get(1);
        Servico resultObject = this.servicoController.consultar(expectedObject.getId());
        assertThat(expectedObject.getId()).isEqualTo(resultObject.getId());
        assertThat(expectedObject.getDescricao()).isEqualTo(resultObject.getDescricao());
        assertThat(expectedObject.getId_carro()).isEqualTo(resultObject.getId_carro());
    }

    @Test
    public void servicoControllerInserirTest() {
        Integer expected = (int) this.servicoRepository.count()+1;
        Servico novoServico = new Servico();
        novoServico.setDescricao("Polimento caprichado");
        novoServico.setId_carro(3);
        this.servicoController.incluir(novoServico);
        Integer result = this.servicoController.listarServico().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void servicoControllerAlterarTest() {

        List<Servico> ListServico = this.servicoRepository.findAll();
        Servico servicoAntigo = ListServico.get(1);
        String expectedDescription = "Polimento caprichado Plus";

        Servico servicoAtualizado = new Servico();
        servicoAtualizado.setId(servicoAntigo.getId());
        servicoAtualizado.setDescricao(expectedDescription);
        servicoAtualizado.setId_carro(servicoAntigo.getId_carro());

        this.servicoController.alterar(servicoAtualizado);
        servicoAntigo  = servicoController.consultar(servicoAntigo.getId());

        String resultDescription = servicoAntigo.getDescricao();
        assertThat(expectedDescription).isEqualTo(resultDescription);
    }

    @Test
    public void servicoBizValidarTest() {
        Boolean result = true;
        Boolean expected = false;

        Servico servico = new Servico();
       
        // esperamos receber falso!
        servico.setId_carro(10000);
        servico.setDescricao("");
        result = this.servicoBiz.validarServico(servico);
        assertThat(result).isEqualTo(expected);

        // esperamos receber falso!
        servico.setId_carro(1);
        servico.setDescricao("");
        result = this.servicoBiz.validarServico(servico);
        assertThat(result).isEqualTo(expected);

        // esperamos receber falso!
        servico.setId_carro(10000);
        servico.setDescricao("Polimento top!");
        result = this.servicoBiz.validarServico(servico);
        assertThat(result).isEqualTo(expected);

        //esperamos receber trues!
        expected = true;
        servico.setId_carro(1);
        servico.setDescricao("Polimento moderno");
        result = this.servicoBiz.validarServico(servico);
        assertThat(result).isEqualTo(expected);

    }

}
