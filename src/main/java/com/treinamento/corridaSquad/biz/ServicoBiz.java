package com.treinamento.corridaSquad.biz;
import com.treinamento.corridaSquad.Mensagem;
import com.treinamento.corridaSquad.entities.Servico;
import com.treinamento.corridaSquad.repositories.CarroRepository;
import com.treinamento.corridaSquad.repositories.MecanicoRepository;

public class ServicoBiz {
    private Mensagem mensagens = new Mensagem();
    private MecanicoRepository mecanicoRepositorio;
    private CarroRepository carroRepositorio;
    public ServicoBiz(MecanicoRepository mecanicoRepo, CarroRepository carroRepo){
        this.mecanicoRepositorio = mecanicoRepo;
        this.carroRepositorio = carroRepo;
    }
    public boolean validarServico(Servico servico){
        boolean valido = true;
        if(servico.getDescricao().isBlank()){
            this.mensagens.mensagem.add("A descrição inserida não deve ser nula");
            valido=false;
        }else if(servico.getDescricao().length()>255){
            this.mensagens.mensagem.add("A descrição inserida não deve possuir mais que 255 caracteres");
            valido=false;
        }
        if(servico.getId_mecanico()==null){
            this.mensagens.mensagem.add("O Id do mecanico inserido não deve ser nulo");
            valido=false;
        }else if(mecanicoRepositorio.findById(servico.getId_mecanico()).isEmpty()){
            this.mensagens.mensagem.add("Nenhum mecanico foi encontrado com o Id informado");
            valido=false;
        }
        if(servico.getId_carro()==null){
            this.mensagens.mensagem.add("O Id do carro inserido não deve ser nulo");
            valido=false;
        }else if(carroRepositorio.findById(servico.getId_carro()).isEmpty()){
            this.mensagens.mensagem.add("Nenhum carro foi encontrado com o Id informado");
            valido=false;
        }
        if(servico.getData()==null){
            this.mensagens.mensagem.add("A data inserida não deve ser nula");
            valido=false;
        }
        if(!valido){
            this.mensagens.mensagem.add("O servico informado não é válido!");
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

