INSERT INTO equipe(nome)VALUES('Rocket')
GO
INSERT INTO piloto(nome,id_equipe)VALUES('Marcus Schumacher', 1)
GO
INSERT INTO carro(id_equipe, descricao, numero)VALUES(1, 'Ferrari SF90', 16)
GO
INSERT INTO corrida(descricao, data_corrida)VALUES('Corrida em Singapura', '2021-08-25')
GO
INSERT INTO carro_corrida_piloto(id_carro, id_corrida, id_piloto)VALUES(1, 1, 1)
GO
INSERT INTO mecanico(nome,id_equipe)VALUES('Lucas Sobrinho', 1)
GO
INSERT INTO servicos(id_carro, id_mecanico, descricao, data_servico)VALUES(1, 1, 'Trocar Pneu', '2021-08-25')
GO
INSERT INTO auxiliar(id_equipe, id_mecanico, nome)VALUES(1, 1, 'Thiago')
GO

SELECT * FROM equipe
SELECT * FROM piloto
SELECT * FROM carro
SELECT * FROM corrida
SELECT * FROM carro_corrida_piloto
SELECT * FROM mecanico
SELECT * FROM servicos
SELECT * FROM auxiliar





