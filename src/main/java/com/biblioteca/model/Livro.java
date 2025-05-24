
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
       
       @OneToOne
       @JoinColumn(name = "id_editora")
       private Editora editora;       
       
       @OneToOne
       @JoinColumn(name = "id_genero")
       private Genero genero;       
       
       @OneToOne
       @JoinColumn(name = "id_autor")
       private Autor autor;
       
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
       
       public Editora getEditora(){
           return this.editora;
       }
       
       public void setEditora(Editora editora){
           this.editora = editora;
       }
       
       public Genero getGenero(){
           return this.genero;
       }
       
       public void setGenero(Genero genero){
           this.genero = genero;
       }
       
       public Autor getAutor(){
           return this.autor;
       }
       
       public void setAutor(Autor autor){
           this.autor = autor;
       }
       
}
