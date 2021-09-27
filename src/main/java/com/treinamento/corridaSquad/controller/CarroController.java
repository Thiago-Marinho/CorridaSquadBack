package com.treinamento.corridaSquad.controller;

import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.biz.CarroBiz;
import com.treinamento.corridaSquad.entities.Carro;
import com.treinamento.corridaSquad.repositories.CarroRepository;
import com.treinamento.corridaSquad.repositories.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("carro")
public class CarroController {
    @Autowired
    private CarroRepository carroRepositorio;
    @Autowired
    private EquipeRepository equipeRepositorio;
    @CrossOrigin
    @GetMapping("listar")
    public List<Carro> listar(){
        return carroRepositorio.findAll();
    }

    @CrossOrigin
    @PostMapping("incluir")
    public List<String> incluir(@Valid @RequestBody Carro novoCarro){
        Mensagem mensagem = new Mensagem();
        CarroBiz validadorCarro = new CarroBiz(equipeRepositorio);
        if(validadorCarro.validarCarro(novoCarro)){
            try{
                carroRepositorio.save(novoCarro);
                carroRepositorio.flush();
                mensagem.mensagem.add("Sucesso ao incluir carro!");

            }catch (Exception err){
                mensagem.mensagem.add("Erro ao incluir carro:");
                mensagem.mensagem.add(err.getMessage());
            }
        }else{
            mensagem.mensagem.addAll(validadorCarro.getMensagens().mensagem);
        }
        return mensagem.mensagem;
    }
}
