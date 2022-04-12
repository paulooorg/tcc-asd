CREATE TABLE endereco (
	id INT AUTO_INCREMENT,
	endereco VARCHAR(70) NOT NULL,
	cidade VARCHAR(50) NOT NULL,
	estado VARCHAR(2) NOT NULL,
	cep INT NOT NULL,
	complemento VARCHAR(70),
	contato VARCHAR(50) NOT NULL,
	PRIMARY KEY (id)
);