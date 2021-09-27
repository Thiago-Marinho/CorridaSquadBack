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
import java.util.Optional;

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
    @CrossOrigin
    @PutMapping("alterar")
    public List<String> alterar(@Valid @RequestBody Carro novoCarro){
        Mensagem mensagem = new Mensagem();
        CarroBiz validadorCarro = new CarroBiz(equipeRepositorio);
        if(validadorCarro.validarCarro(novoCarro)){
            try{
                Optional<Carro> carroSearch = carroRepositorio.findById(novoCarro.getId());
                if(carroSearch.isPresent()){
                    Carro oldCarro = carroSearch.get();
                    oldCarro.setDescricao(novoCarro.getDescricao());
                    oldCarro.setId_equipe(novoCarro.getId_equipe());
                    oldCarro.setNumero(novoCarro.getNumero());
                    carroRepositorio.save(oldCarro);
                    carroRepositorio.flush();
                    mensagem.mensagem.add("Sucesso ao atualizar o carro com o número ("+oldCarro.getNumero()+") !");
                }else{
                    mensagem.mensagem.add("Nem um carro foi encontrado com o Id informado");
                }

            }catch (Exception err){
                mensagem.mensagem.add("Erro ao atualizar carro:");
                mensagem.mensagem.add(err.getMessage());
            }
        }else{
            mensagem.mensagem.addAll(validadorCarro.getMensagens().mensagem);
        }
        return mensagem.mensagem;
    }
}
