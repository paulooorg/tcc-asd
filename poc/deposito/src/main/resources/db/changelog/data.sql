--liquibase formatted sql
--changeset cliente:data splitStatements:true endDelimiter:;
INSERT INTO endereco(endereco, cidade, estado, cep, complemento, contato) VALUES ('Rua XYZ', 'Chapecó', 'SC', 89801000, 'Complemento XYZ', '49 9 0000-1111');
INSERT INTO deposito(nome, endereco_id) VALUES ('Depósito 1', LAST_INSERT_ID());

