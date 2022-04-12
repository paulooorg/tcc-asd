CREATE TABLE volume (
	id INT AUTO_INCREMENT,
	formato VARCHAR(10) NOT NULL,
    peso NUMERIC(19, 2) NOT NULL,
    unidade_medida_peso VARCHAR(2) NOT NULL,
    altura NUMERIC(19, 2) NOT NULL,
    largura NUMERIC(19, 2) NOT NULL,
    comprimento NUMERIC(19, 2) NOT NULL,
    quantidade INT NOT NULL,
    pedido_id INT NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (pedido_id) REFERENCES pedido(id)
);