CREATE DATABASE CorridaSquad
GO

USE CorridaSquad
GO

CREATE TABLE equipe(
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	nome varchar(50) NOT NULL
)
GO

CREATE TABLE piloto (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	nome varchar(255) NOT NULL,
	id_equipe int NOT NULL REFERENCES equipe(id)
)
GO

CREATE TABLE carro (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	id_equipe int NOT NULL REFERENCES equipe(id),
	descricao varchar(255) NOT NULL,
	numero int NOT NULL
)
GO

CREATE TABLE corrida (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	descricao varchar(255) NOT NULL,
)
GO

CREATE TABLE carro_corrida_piloto (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	id_carro int NOT NULL REFERENCES carro(id),
	id_corrida int NOT NULL REFERENCES corrida(id),
	id_piloto int NOT NULL REFERENCES piloto(id)
)
GO

CREATE TABLE mecanico (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	nome varchar(255) NOT NULL,
	id_equipe int NOT NULL REFERENCES equipe(id)
)
GO


CREATE TABLE servicos (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	id_mecanico int NOT NULL REFERENCES mecanico(id),
	id_carro int NOT NULL REFERENCES carro(id),
	descricao varchar(255) NOT NULL,
)
GO

CREATE TABLE auxiliar (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	nome varchar(255) NOT NULL,
	id_mecanico int NOT NULL REFERENCES mecanico(id),
	id_equipe int NOT NULL REFERENCES equipe(id)
)
GO