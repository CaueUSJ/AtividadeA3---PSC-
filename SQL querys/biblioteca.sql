CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20),
    endereco VARCHAR(100),
    telefone VARCHAR(20),
    email VARCHAR(50),
    senha VARCHAR(20) not null,
    regisro int not null
);

CREATE TABLE IF NOT EXISTS editora (
    id_editora INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS genero (
    id_genero INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS pais (
    id_pais INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS autor(
    id_autor INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    id_pais INT,
    FOREIGN KEY (id_pais) REFERENCES pais(id_pais)
);

CREATE TABLE IF NOT EXISTS livro (
    id_livro INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(200),
    ano INT,
    num_paginas INT,
    isbn VARCHAR(50),
    id_editora INT,
    id_genero INT,
    id_autor INT,
    FOREIGN KEY (id_editora) REFERENCES editora(id_editora),
    FOREIGN KEY (id_genero) REFERENCES genero(id_genero),
    FOREIGN KEY (id_autor) REFERENCES autor(id_autor)
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

INSERT INTO Pais (ID_Pais, Nome) VALUES (1, 'Japão');
INSERT INTO Editora (Nome) VALUES ('Panini Comics');
INSERT INTO Autor (Nome, ID_Pais) VALUES ('Isaac Asimov', 1);
INSERT INTO Genero (ID_Genero, Nome) VALUES (1, 'Aventura');
SELECT * FROM autor WHERE id_autor = 2;
INSERT INTO autor (nome, id_pais) VALUES ('Tolkien', 1);
SELECT * FROM livro;
SELECT * FROM pais;
SELECT * FROM autor;
SELECT * FROM genero;
SELECT * FROM editora;
SELECT * FROM usuario;
DESCRIBE autor;

ALTER TABLE usuario CHANGE COLUMN senha senha varchar(20) not null;

alter table usuario add column registro varchar(20);

DELETE FROM livro WHERE id_autor = 2;
DELETE FROM autor WHERE id_autor = 2;

INSERT INTO Livro (Titulo, ano, num_paginas, isbn, id_editora, id_genero, id_autor) VALUES ('O hobbit', 1937, 310, "978857", 1, 1, 2);
INSERT INTO autor (nome, id_pais) VALUES ('Akira', 1);
