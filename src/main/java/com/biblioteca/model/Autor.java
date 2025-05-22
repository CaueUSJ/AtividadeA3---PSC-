
package com.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table (name = "autor")
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_autor;
    
    private String nome;
    private int id_pais;
    
    
    public int getId_autor(){
        return this.id_autor;
    }
    
    public void setId_autor(int id_autor){
        this.id_autor = id_autor;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public int getId_pais(){
        return this.id_pais;
    }
    
    public void setId_pais(int id_pais){
        this.id_pais = id_pais;
    }
    
}
