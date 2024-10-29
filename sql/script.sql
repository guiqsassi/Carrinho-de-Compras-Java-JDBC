CREATE DATABASE IF NOT EXISTS desafio01;

USE desafio01;

CREATE TABLE stock (
	id INT AUTO_INCREMENT NOT NULL,
    nome VARCHAR(150) NOT NULL,
    category VARCHAR(100) NOT NULL,
    valor DOUBLE NOT NULL,
    quantity LONG NOT NULL,
    PRIMARY KEY(id)
    
);

CREATE TABLE cart(
	id INT AUTO_INCREMENT NOT NULL,
    quantity INT,
    valor_total DOUBLE,
    PRIMARY KEY(id)
);

CREATE TABLE cart_items (
	id INT AUTO_INCREMENT NOT NULL,
	cartId INT NOT NULL,
    stockId INT NOT NULL,
    quantity INT NOT NULL,
	PRIMARY KEY(id),
    FOREIGN KEY(cartId) REFERENCES cart(id),
    FOREIGN KEY(stockId) REFERENCES stock(id)
    
)