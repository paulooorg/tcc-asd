CREATE TABLE entrega (
	id INT AUTO_INCREMENT,
	codigo_pedido VARCHAR(40) NOT NULL,
	responsavel_entrega VARCHAR(70) NOT NULL,
	data_entrega DATETIME NOT NULL,
	situacao VARCHAR(20) NOT NULL,
	motivo VARCHAR(30),
	assinatura_id INT,
	observacao VARCHAR(200),
	latitude NUMERIC(19, 6) NOT NULL,
	longitude NUMERIC(19, 6) NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (assinatura_id) REFERENCES assinatura(id)
);