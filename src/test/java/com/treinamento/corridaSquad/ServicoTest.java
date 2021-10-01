package com.treinamento.corridaSquad;

import com.treinamento.corridaSquad.biz.ServicoBiz;
import com.treinamento.corridaSquad.controller.ServicoController;
import com.treinamento.corridaSquad.entities.Servico;
import com.treinamento.corridaSquad.entities.Servico;
import com.treinamento.corridaSquad.repositories.ServicoRepository;
import com.treinamento.corridaSquad.repositories.CarroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

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

    @Test
    public void servicoControllerListarTest() {
        Integer expected = 0;
        expected = (int) this.servicoRepository.count();
        Integer result = this.servicoController.listarServico().size();
        assertThat(expected).isEqualTo(result);
    }

    @Test
    public void servicoControllerConsultarTest() {
        Servico expected = this.obterPrimeiroRegistro();
        Servico result = this.servicoController.consultar(expected.getId());
        assertThat(expected.getId()).isEqualTo(result.getId());
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
    public void ServicoControllerAlterarTest() {

        Boolean expected = true;
        Boolean result = false;

        Servico servicoUpdate = obterPrimeiroRegistro();

        servicoUpdate.setId_carro(3);
        servicoUpdate.setDescricao("troca de pneu top");
        Mensagem msg = this.servicoController.alterar(servicoUpdate);

        if (msg.ContemErro()){
            result = false;
        } else {
            Servico servico  =
                    servicoController.consultar(servicoUpdate.getId());

            if (  servico.getId() == servicoUpdate.getId() &&
                    servico.getId_carro() == servicoUpdate.getId_carro() &&
                    servico.getDescricao().equals(servicoUpdate.getDescricao())
            ) {
                result = true;
            }
        }
        assertThat(result).isEqualTo(expected);

    }

    @Test
    public void servicoBizValidarTest() {
        ServicoBiz servicoBiz = new ServicoBiz(carroRepository);
        Boolean result = true;
        Boolean expected = true;

        Servico servico = new Servico();
       
        // esperamos receber falso!
        servico.setId_carro(10000);
        servico.setDescricao("");
        if(servicoBiz.validarServico(servico)) result = false;

        // esperamos receber falso!
        servico.setId_carro(1);
        servico.setDescricao("");
        if(servicoBiz.validarServico(servico)) result = false;

        // esperamos receber falso!
        servico.setId_carro(10000);
        servico.setDescricao("Polimento top!");
        if(servicoBiz.validarServico(servico)) result = false;

        //esperamos receber trues!
        servico.setId_carro(1);
        servico.setDescricao("Polimento moderno");
        if(servicoBiz.validarServico(servico)) result = true;

        assertThat(result).isEqualTo(expected);

    }

    public Servico obterPrimeiroRegistro() {
        Page<Servico> pagina = servicoRepository.findAll(
                PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "id")));
        return pagina.toList().get(0);
    }

}
