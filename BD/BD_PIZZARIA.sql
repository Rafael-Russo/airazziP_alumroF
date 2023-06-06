CREATE DATABASE pizzaria;
USE pizzaria;

CREATE TABLE Cliente (
id_cliente INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
nome_cliente VARCHAR(100),
telefone_cliente VARCHAR(20),
endereco_cliente VARCHAR(200)
);

CREATE TABLE Ingrediente (
id_ingrediente INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
nome_ingrediente VARCHAR(100),
preco_ingrediente DOUBLE (4,2)
);

CREATE TABLE Cardapio (
id_pizza INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
nome_pizza VARCHAR(100),
preco_pizza DOUBLE (4,2)
);

CREATE TABLE Pedido (
id_pedido INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
id_cliente INT NOT NULL,
has_borda BIT DEFAULT 0,
id_pizza INT NOT NULL,
qnt_pizza INT NOT NULL,
preco_pedido DOUBLE(5,2),

FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
FOREIGN KEY (id_pizza) REFERENCES Cardapio(id_pizza)
);

CREATE TABLE qnt_Adicionais(
id_qnt_adicionais INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
id_ingrediente INT NOT NULL,
id_pedido INT NOT NULL,

FOREIGN KEY (id_ingrediente) REFERENCES Ingrediente(id_ingrediente),
FOREIGN KEY (id_pedido) REFERENCES Pedido(id_pedido)
);

INSERT INTO Cardapio (nome_pizza, preco_pizza)
VALUES ('Pizza Margherita', 29.99);

INSERT INTO Cardapio (nome_pizza, preco_pizza)
VALUES ('Pizza Pepperoni', 34.99);

INSERT INTO Cardapio (nome_pizza, preco_pizza)
VALUES ('Minecraft', 31.99);