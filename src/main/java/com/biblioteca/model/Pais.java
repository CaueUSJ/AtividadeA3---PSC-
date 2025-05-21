
package com.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table (name ="pais")
public class Pais {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pais;
    
    private String nome;
    
    
    public int getId_pais(){
        return this.id_pais;
    }
    
    public void setId_pais(int id_pais){
        this.id_pais = id_pais;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
}
