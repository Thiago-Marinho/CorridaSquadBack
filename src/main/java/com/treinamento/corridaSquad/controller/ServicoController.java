package com.treinamento.corridaSquad.controller;

import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.biz.ServicoBiz;
import com.treinamento.corridaSquad.entities.Servico;
import com.treinamento.corridaSquad.repositories.CarroRepository;
import com.treinamento.corridaSquad.repositories.ServicoRepository;
import com.treinamento.corridaSquad.repositories.MecanicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("servico")
public class ServicoController {
    @Autowired
    ServicoRepository servicoRepositorio;
    @Autowired
    CarroRepository carroRepositorio;
    @Autowired
    MecanicoRepository mecanicoRepository;
    @CrossOrigin
    @GetMapping("listar")
    public List<Servico> listarServico() {
        List<Servico> lista = servicoRepositorio.findAll();
        return lista;
    }
    @CrossOrigin
    @PostMapping("incluir")
    public List<String> incluir(@Valid @RequestBody Servico novoServico){
        Mensagem mensagem = new Mensagem();
        ServicoBiz validadorServico = new ServicoBiz(carroRepositorio);
        if(validadorServico.validarServico(novoServico)){
            try{
                servicoRepositorio.save(novoServico);
                servicoRepositorio.flush();
                mensagem.mensagem.add("Sucesso ao incluir servico!");

            }catch (Exception err){
                mensagem.mensagem.add("Erro ao incluir servico:");
                mensagem.mensagem.add(err.getMessage());
            }
        }else{
            mensagem.mensagem.addAll(validadorServico.getMensagens().mensagem);
        }
        return mensagem.mensagem;
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Servico consultar(@PathVariable int id) {
        return this.servicoRepositorio.findById(id).get();
    }

    @CrossOrigin
    @PutMapping("alterar")
    public List<String> alterar(@Valid @RequestBody Servico novoServico){
        Mensagem mensagem = new Mensagem();
        ServicoBiz validadorServico = new ServicoBiz(carroRepositorio);
        if(validadorServico.validarServico(novoServico)){
            try{
                Optional<Servico> servicoSearch = servicoRepositorio.findById(novoServico.getId());
                if(servicoSearch.isPresent()){
                    Servico oldServico = servicoSearch.get();
                    oldServico.setDescricao(novoServico.getDescricao());
                    oldServico.setId_carro(novoServico.getId_carro());
                    servicoRepositorio.save(oldServico);
                    servicoRepositorio.flush();
                    mensagem.mensagem.add("Sucesso ao atualizar o servico com o Id ("+oldServico.getId()+") !");
                }else{
                    mensagem.mensagem.add("Nem um servico foi encontrado com o Id informado");
                }

            }catch (Exception err){
                mensagem.mensagem.add("Erro ao atualizar servico:");
                mensagem.mensagem.add(err.getMessage());
            }
        }else{
            mensagem.mensagem.addAll(validadorServico.getMensagens().mensagem);
        }
        return mensagem.mensagem;
    }
}
