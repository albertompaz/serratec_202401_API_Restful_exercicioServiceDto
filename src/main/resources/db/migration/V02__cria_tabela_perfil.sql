CREATE TABLE perfil (
id_perfil serial primary key,
nome varchar(40)
);

CREATE TABLE usuario_perfil (
id_usuario int references usuario (id_usuario),
id_perfil int references perfil(id_perfil),
data_criacao date,
constraint pk_usuario_perfil primary key (id_usuario, id_perfil)
);

insert into perfil (id_perfil, nome) values
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');