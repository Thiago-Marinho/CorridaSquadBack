package com.treinamento.corridaSquad.controller;

import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.biz.ServicoBiz;
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
    public Mensagem alterar(@Valid @RequestBody Servico novoServico){
        ServicoBiz servicoBiz = new ServicoBiz(carroRepositorio);
        try {
            if (servicoBiz.validarServico(novoServico)) {
                servicoRepositorio.save(novoServico);
                servicoRepositorio.flush();
                servicoBiz.getMensagens().mensagem.add("CorridaCarroPiloto atualizado com sucesso!");
            } else {
                servicoBiz.getMensagens().mensagem.add("Erro ao alterar!");
            }
        } catch (Exception e) {
            servicoBiz.getMensagens().mensagem.add("Erro ao alterar: " + e.getMessage());
        }
        return servicoBiz.getMensagens();
    }
}
