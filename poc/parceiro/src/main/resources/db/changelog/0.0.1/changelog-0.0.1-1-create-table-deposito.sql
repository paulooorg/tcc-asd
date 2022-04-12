CREATE TABLE parceiro_deposito (
	parceiro_id INT NOT NULL,
	deposito_id INT NOT NULL,
	FOREIGN KEY (parceiro_id) REFERENCES parceiro(id),
	PRIMARY KEY (parceiro_id, deposito_id)
);