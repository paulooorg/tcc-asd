CREATE TABLE cliente (
	id INT AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    desconto_id INT,
	PRIMARY KEY (id),
	FOREIGN KEY (desconto_id) REFERENCES desconto(id)
);