package com.treinamento.corridaSquad.biz;

import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.entities.Carro;
import com.treinamento.corridaSquad.repositories.EquipeRepository;
 
public class CarroBiz {
    private Mensagem mensagens = new Mensagem();
    private EquipeRepository equipeRepositorio;
    public CarroBiz(EquipeRepository equipeRepo){
        this.equipeRepositorio = equipeRepo;
    }
    public boolean validarCarro(Carro carro){
        boolean valido = true;
        if(carro.getDescricao().isBlank()){
            this.mensagens.mensagem.add("A descrição inserida não deve ser nula");
            valido=false;
        }else if(carro.getDescricao().length()>255){
            this.mensagens.mensagem.add("A descrição inserida não deve possuir mais que 255 caracteres");
            valido=false;
        }
        if(carro.getId_equipe()==null){
            this.mensagens.mensagem.add("O Id da equipe inserido não deve ser nulo");
            valido=false;
        }else if(equipeRepositorio.findById(carro.getId_equipe()).isEmpty()){
            this.mensagens.mensagem.add("Nenhum uma equipe foi encontrada com o Id informado");
            valido=false;
        }
        if(carro.getNumero().isBlank()){
            this.mensagens.mensagem.add("O número do carro inserido não deve ser nulo");
            valido=false;
        }else if(carro.getNumero().length()>7){
            this.mensagens.mensagem.add("O número do carro não deve possuir mais que 7 caracteres");
            valido=false;
        }
        if(!valido){
            this.mensagens.mensagem.add("O carro informado não é válido!");
        }
        return valido;
    }

    public Mensagem getMensagens() {
        return mensagens;
    }
    public void setMensagens(Mensagem mensagens) {
        this.mensagens = mensagens;
    }
}
