--liquibase formatted sql
--changeset cliente:data splitStatements:true endDelimiter:;
INSERT INTO desconto(valor) VALUES (5);
INSERT INTO cliente(nome, desconto_id) VALUES ('Cliente 1', LAST_INSERT_ID());
INSERT INTO endereco(endereco, cidade, estado, cep, complemento, contato, cliente_id) VALUES ('Rua XYZ', 'Chapecó', 'SC', 89801000, 'Complemento XYZ', '49 9 0000-1111', LAST_INSERT_ID());
