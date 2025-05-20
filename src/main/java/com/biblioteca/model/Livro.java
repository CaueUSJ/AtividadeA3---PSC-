
package com.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livro")
public class Livro {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private int id_livro;
       
       private String titulo;
       private int ano;
       private int num_pagina;
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
       
       public int getNum_pagina(){
           return this.num_pagina;
       }
       
       public void setNum_pagina(int num_pagina){
           this.num_pagina = num_pagina;
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
