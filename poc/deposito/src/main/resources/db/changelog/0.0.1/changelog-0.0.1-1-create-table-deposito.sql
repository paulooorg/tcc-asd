CREATE TABLE deposito (
	id INT AUTO_INCREMENT,
	nome VARCHAR(70) NOT NULL,
	endereco_id INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);