package com.treinamento.corridaSquad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CorridaSquadApplication {

	public static void main(String[] args) {
		System.out.println("Iniciando conexão com o banco de dados");
		SpringApplication.run(CorridaSquadApplication.class, args);
		System.out.println("Conexão com o banco de dados iniciada com sucesso");
	}

}
