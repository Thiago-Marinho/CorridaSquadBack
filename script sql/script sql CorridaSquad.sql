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


CREATE TABLE servico (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	id_carro int NOT NULL REFERENCES carro(id),
	descricao varchar(255) NOT NULL,
)
GO

CREATE TABLE auxiliar (
	id int NOT NULL PRIMARY KEY IDENTITY(1,1),
	nome varchar(255) NOT NULL,
	id_mecanico int NOT NULL REFERENCES mecanico(id)
)
GO

CREATE VIEW view_quantidade_carro_corrida as
SELECT
    cor.id, cor.descricao as corrida, COUNT(*) as quant_carro
FROM
    carro as car
INNER JOIN carro_corrida_piloto as ccp on ccp.id_carro = car.id
INNER JOIN corrida as cor on cor.id = ccp.id_corrida
GROUP BY cor.id,cor.descricao
GO

CREATE VIEW View_corrida_equipe as
SELECT
    cor.id, e.nome as equipe, car.descricao as carro, cor.descricao as corrida
FROM
    equipe as e
INNER JOIN carro as car on car.id_equipe = e.id
INNER JOIN carro_corrida_piloto as ccp on ccp.id_carro = car.id
INNER JOIN corrida as cor on cor.id = ccp.id_corrida
GO

CREATE VIEW view_mecanico_auxiliar as
SELECT
    mec.id, mec.nome, COUNT(*) as quantidade_auxiliares
FROM
    auxiliar as aux
INNER JOIN mecanico as mec on mec.id = aux.id_mecanico
GROUP BY mec.id, mec.nome
GO