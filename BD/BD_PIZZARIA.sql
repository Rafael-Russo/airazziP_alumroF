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
preco_ingrediente DOUBLE (10,10)
);

CREATE TABLE Cardapio (
id_pizza INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
nome_pizza VARCHAR(100),
preco_pizza DOUBLE (4,2)
);

CREATE TABLE Pedido (
id_pedido INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
id_cliente INT NOT NULL,
has_borda BOOL NOT NULL,
id_qnt_pedido INT NOT NULL,
preco_pedido DOUBLE(4,2),

FOREIGN KEY (id_cliente) REFERENCES Cliente(id_cliente),
FOREIGN KEY (id_qnt_pedido) REFERENCES Qnt_Pedido(id_qnt_pedido)
);

CREATE TABLE Qnt_Pedido(
id_qnt_pedido INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
id_pizza INT NOT NULL,
qnt_pizza INT NOT NULL,
id_qnt_adicionais INT,

FOREIGN KEY (id_pizza) REFERENCES Cardapio(id_pizza),
FOREIGN KEY (id_qnt_adicionais) REFERENCES Qnt_Adicionais(id_adicionais)
);

CREATE TABLE Qnt_Adicionais (
id_adicionais INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
id_ingredientes INT NOT NULL
);

INSERT INTO Cardapio (nome_pizza, preco_pizza)
VALUES ('Pizza Margherita', 29.99);

INSERT INTO Cardapio (nome_pizza, preco_pizza)
VALUES ('Pizza Pepperoni', 34.99);

INSERT INTO Cardapio (nome_pizza, preco_pizza)
VALUES ('Mminecraft', 31.99);
