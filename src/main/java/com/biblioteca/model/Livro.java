
package com.biblioteca.model;

import jakarta.persistence.*;

@Entity                 // Diz ao Spring que essa classe representa uma tabela.
@Table(name = "livro")  // Liga a entidade à tabela livro no banco.
public class Livro {
       @Id                                                  // Indica qual atributo é a chave primária.
       @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indica que o atributo é auto incrementado.
       private int id_livro;
       
       // Caso o nome do atributo esteja escrito diferente no banco de dados, utilizar o comando @Column(name = "...") para ligar o atributo a coluna da tabela.
       private String titulo;
       private String ano;
       private String num_paginas;
       private String isbn;
       private String editora;       
       private String genero;       
       private String autor;
       
       public Livro(String titulo, String autor, String genero, String editora, String num_paginas, String ano, String isbn){
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.editora = editora;
        this.num_paginas = num_paginas;
        this.ano = ano;
        this.isbn = isbn;
    }
       
       public int getId_livro(){
           return this.id_livro;
       }
       
       public void setId_livro(int id_livro){
           this.id_livro = id_livro;
       }
       
       public String getTitulo(){
           return this.titulo;
       }
       
       public void setTitulo(String titulo){
           this.titulo = titulo;
       }
       
       public String getAno(){
           return this.ano;
       }
       
       public void setAno(String ano){
           this.ano = ano;
       }
       
       public String getNum_paginas(){
           return this.num_paginas;
       }
       
       public void setNum_pagina(String num_paginas){
           this.num_paginas = num_paginas;
       }
       
       public String getIsbn(){
           return this.isbn;
       }
       
       public void setIsbn(String isbn){
           this.isbn = isbn;
       }
       
       public String getEditora(){
           return this.editora;
       }
       
       public void setEditora(String editora){
           this.editora = editora;
       }
       
       public String getGenero(){
           return this.genero;
       }
       
       public void setGenero(String genero){
           this.genero = genero;
       }
       
       public String getAutor(){
           return this.autor;
       }
       
       public void setAutor(String autor){
           this.autor = autor;
       }
       
}
