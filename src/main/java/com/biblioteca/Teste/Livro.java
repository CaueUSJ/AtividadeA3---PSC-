package com.biblioteca.Teste;

public class Livro {
    private String titulo;
    private String autor;
    private String genero;
    private int ano;

    public Livro(String titulo, String autor, String genero, int ano) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.ano = ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getGenero() {
        return genero;
    }

    public int getAno() {
        return ano;
    }
}