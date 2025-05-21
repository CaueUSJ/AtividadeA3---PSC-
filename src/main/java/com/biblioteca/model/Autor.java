
package com.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table (name = "autor")
public class Autor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_autor;
    
    private String nome;
    
    @ManyToOne
    @JoinColumn(name = "pais_id") // Cria a coluna de chave estrangeira para o país.
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
    
    public void setPais(Pais pais){
        this.pais = pais;
    }
    
}
