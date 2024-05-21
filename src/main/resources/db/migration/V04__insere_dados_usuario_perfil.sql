DELETE from usuario_perfil;

DELETE from perfil;

DELETE from usuario;

INSERT INTO usuario (nome, email, senha) VALUES
('Joao da Silva', 'joao@email.com', '$2a$12$sPPV9up/RlaZGUBA1AU7ju66f4o.eNSGhhCaWUdr4rnvDZ.QjaMtK'),
('Andre das coves', 'andre@email.com', '$2a$12$G7ibc/sJRL0BWCpVCBcRxudHZ2aV8uHbMhHbu/Y6Zpz3Dw1X4.B2S');
INSERT INTO perfil (nome) VALUES
('ADMIN'),
('USER');

INSERT INTO usuario_perfil (id_usuario, id_perfil) VALUES
( (SELECT id_usuario FROM usuario WHERE email='joao@email.com'),
(SELECT id_perfil FROM perfil WHERE nome='ADMIN') ),
( (SELECT id_usuario FROM usuario WHERE email='joao@email.com'),
(SELECT id_perfil FROM perfil WHERE nome='USER') ),
( (SELECT id_usuario FROM usuario WHERE email='andre@email.com'),
(SELECT id_perfil FROM perfil WHERE nome='ADMIN') );