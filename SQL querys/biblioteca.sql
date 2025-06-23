CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

Drop table emprestimo;

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20),
    endereco VARCHAR(100),
    telefone VARCHAR(20),
    email VARCHAR(50),
    senha VARCHAR(20) not null,
    registro varchar(20) not null
);

CREATE TABLE IF NOT EXISTS leitor (
    id_leitor INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20),
    endereco VARCHAR(100),
    telefone VARCHAR(20),
    email VARCHAR(50),
    registro int not null
);

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

CREATE TABLE emprestimo (
    id_emprestimo INT PRIMARY KEY AUTO_INCREMENT,
    id_leitor int,
    id_livro INT,
    data_emprestimo DATE,
    data_devolucao DATE,
    devolvido BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (id_leitor) REFERENCES leitor(id_leitor),
    FOREIGN KEY (id_livro) REFERENCES livro(id_livro)
);

SELECT * FROM livro;
SELECT * FROM usuario;
SELECT * FROM leitor;
SELECT * FROM emprestimo;

ALTER TABLE usuario CHANGE COLUMN registro registro varchar(20) not null;

DELETE FROM livro WHERE id_autor = 2;

INSERT INTO Livro (Titulo, ano, num_paginas, isbn, editora, genero, autor) VALUES ('Dragon Ball Vol.3 ', 1984, 300, "978857", "Panini", "Aventura", "Akira");
INSERT INTO usuario (nome, endereco, telefone, email, registro, senha) VALUES ('ADM', 'Rua Y', '(11) 123456789', 'danilo@email.com', 'adm', 'adm');
INSERT INTO leitor (nome, endereco, telefone, email, registro) VALUES ('Leitor', 'Rua Y', '(11) 123456789', 'leitor@email.com', '12345');
INSERT INTO emprestimo (id_leitor, id_livro, data_emprestimo, data_devolucao, devolvido) VALUES (1, 3, "2025-05-01", null , FALSE);

DELETE FROM leitor WHERE registro = 1596;

#Realizar o o que, o porque e o como do projeto