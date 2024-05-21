create table foto (
id_foto serial primary key,
dados bytea,
tipo varchar(100),
nome varchar(100),
id_funcionario bigint,
foreign key (id_funcionario) references funcionario(id_funcionario)
);