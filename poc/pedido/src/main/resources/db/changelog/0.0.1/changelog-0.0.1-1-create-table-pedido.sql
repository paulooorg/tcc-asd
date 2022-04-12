CREATE TABLE pedido (
	id INT AUTO_INCREMENT,
	cliente_id INT NOT NULL,
	codigo VARCHAR(40) NOT NULL,
    data_emissao DATETIME NOT NULL,
    data_entrega_prevista DATE NOT NULL,
    custo_frete NUMERIC(19, 2) NOT NULL,
    nome_destinatario VARCHAR(50) NOT NULL,
    documento_destinatario VARCHAR(20) NOT NULL,
    endereco_destinatario_id INT NOT NULL,
    responsavel_entrega VARCHAR(50),
    situacao VARCHAR(20) NOT NULL,
    nome_remetente VARCHAR(50) NOT NULL,
    endereco_remetente_id INT NOT NULL,
    deposito_id INT,
	PRIMARY KEY (id),
	FOREIGN KEY (endereco_destinatario_id) REFERENCES endereco(id),
	FOREIGN KEY (endereco_remetente_id) REFERENCES endereco(id)
);