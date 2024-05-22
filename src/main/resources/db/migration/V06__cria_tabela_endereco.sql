CREATE TABLE endereco (
id_endereco serial PRIMARY KEY,
cep varchar(10),
logradouro varchar(50),
complemento varchar(30),
bairro varchar(40),
localidade varchar(40),
uf varchar(2),
ibge integer
);

ALTER TABLE usuario
ADD COLUMN id_endereco bigint,
ADD CONSTRAINT fk_id_endereco FOREIGN KEY (id_endereco)
REFERENCES endereco;