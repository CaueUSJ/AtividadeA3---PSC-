# 📚 Sistema de Gerenciamento de Biblioteca

Este é um sistema desktop simples para gerenciamento de empréstimos de livros em uma biblioteca.
Desenvolvido em Java com Swing, o projeto utiliza banco de dados MySQL para armazenar leitores, livros e empréstimos realizados.

## 🛠️ Tecnologias Utilizadas

- **Java** (JDK 8+)
- **Swing** (para a interface gráfica)
- **JDBC** (para conexão com o MySQL)
- **MySQL** (como banco de dados relacional)
- **NetBeans** (IDE recomendada para rodar o projeto)

---

## ✅ Funcionalidades

- Cadastro de **leitores**
- Cadastro de **livros**
- Registro de **empréstimos**
- Marcar **devoluções**
- Interface com **tabelas
- Controle de status de devolução (devolvido ou pendente)

---

## 🗃️ Banco de Dados

O banco de dados MySQL deve conter as seguintes tabelas:

sql
CREATE TABLE leitor (
    id_leitor INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100),
    registro VARCHAR(20),
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco TEXT
);

CREATE TABLE livro (
    id_livro INT PRIMARY KEY AUTO_INCREMENT,
    titulo VARCHAR(100),
    autor VARCHAR(100),
    editora VARCHAR(100),
    ano VARCHAR(10),
    genero VARCHAR(50)
);

CREATE TABLE emprestimo (
    id_emprestimo INT PRIMARY KEY AUTO_INCREMENT,
    id_leitor INT,
    id_livro INT,
    data_emprestimo DATE,
    data_devolucao DATE,
    devolvido BOOLEAN,
    FOREIGN KEY (id_leitor) REFERENCES leitor(id_leitor),
    FOREIGN KEY (id_livro) REFERENCES livro(id_livro)
);

Este projeto foi desenvolvido como prática de aprendizado em Java desktop, com foco em boas práticas de programação, organização em camadas e uso de banco de dados relacional.

---

🙋‍♂️ Autor
Cauê Lopes Justo
RA: 82329174

