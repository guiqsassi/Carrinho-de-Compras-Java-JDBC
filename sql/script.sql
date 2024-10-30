CREATE DATABASE IF NOT EXISTS desafio01;

USE desafio01;

CREATE TABLE stock (
	id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(150) NOT NULL,
    category VARCHAR(100) NOT NULL,
    value DOUBLE NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY(id)
    
);

CREATE TABLE cart(
	id INT AUTO_INCREMENT NOT NULL,
    quantity INT,
    total_value DOUBLE,
    PRIMARY KEY(id)
);

CREATE TABLE cart_item (
	id INT AUTO_INCREMENT NOT NULL,
	cartId INT NOT NULL,
    stockId INT NOT NULL,
    quantity INT NOT NULL,
	PRIMARY KEY(id),
    FOREIGN KEY(cartId) REFERENCES cart(id),
    FOREIGN KEY(stockId) REFERENCES stock(id)
); 

