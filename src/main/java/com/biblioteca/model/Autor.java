
package com.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table (name = "autor")
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_autor;
    
    private String nome;
    
    @OneToOne
    @JoinColumn(name = "id_pais")
    private Pais pais;
    
    
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
    
    public Pais getPais(){
        return this.pais;
    }
    
    public void setPais(Pais id_pais){
        this.pais = id_pais;
    }
    
}
