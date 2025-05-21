
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
       private int ano;
       private int num_paginas;
       private String isbn;
       private int id_editora;
       private int id_genero;
       private int id_autor;
       
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
       
       public int getAno(){
           return this.ano;
       }
       
       public void setAno(int ano){
           this.ano = ano;
       }
       
       public int getNum_paginas(){
           return this.num_paginas;
       }
       
       public void setNum_pagina(int num_paginas){
           this.num_paginas = num_paginas;
       }
       
       public String getIsbn(){
           return this.isbn;
       }
       
       public void setIsbn(String isbn){
           this.isbn = isbn;
       }
       
       public int getId_editora(){
           return this.id_editora;
       }
       
       public void setId_editora(int id_editora){
           this.id_editora = id_editora;
       }
       
       public int getId_genero(){
           return this.id_genero;
       }
       
       public void setId_genero(int id_genero){
           this.id_genero = id_genero;
       }
       
       public int getId_autor(){
           return this.id_autor;
       }
       
       public void setId_autor(int id_autor){
           this.id_autor = id_autor;
       }
       
}
