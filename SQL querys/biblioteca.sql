CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

Drop table pais;

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20),
    endereco VARCHAR(100),
    telefone VARCHAR(20),
    email VARCHAR(50),
    senha VARCHAR(20) not null,
    registro int not null
);

CREATE TABLE IF NOT EXISTS leitor (
    id_leitor INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20),
    endereco VARCHAR(100),
    telefone VARCHAR(20),
    email VARCHAR(50),
    registro int not null
);

ALTER TABLE usuario ADD COLUMN registro VARCHAR(20);

CREATE TABLE IF NOT EXISTS livro (
    id_livro INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(200),
    ano INT,
    num_paginas INT,
    isbn VARCHAR(50),
    editora VARCHAR(50),
    genero VARCHAR(50),
    autor VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS emprestimo (
    id_emprestimo INT PRIMARY KEY AUTO_INCREMENT,
    data_emprestimo DATE,
    data_devolucao DATE,
    id_livro INT,
    id_usuario INT,
    FOREIGN KEY (id_livro) REFERENCES livro(id_livro),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

SELECT * FROM livro;
SELECT * FROM usuario;
SELECT * FROM leitor;

ALTER TABLE usuario CHANGE COLUMN senha senha varchar(20) not null;

DELETE FROM livro WHERE id_autor = 2;

INSERT INTO Livro (Titulo, ano, num_paginas, isbn, editora, genero, autor) VALUES ('Dragon Ball Vol.3 ', 1984, 300, "978857", "Panini", "Aventura", "Akira");
INSERT INTO usuario (nome, endereco, telefone, email, registro, senha) VALUES ('ADM', 'Rua Y', '(11) 123456789', 'danilo@email.com', 'adm', 'adm');
INSERT INTO leitor (nome, endereco, telefone, email, registro) VALUES ('Leitor', 'Rua Y', '(11) 123456789', 'leitor@email.com', '12345');

#Realizar o o que, o porque e o como do projeto