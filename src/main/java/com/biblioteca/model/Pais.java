
package com.biblioteca.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table (name ="pais")
public class Pais {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_pais;
    
    private String nome;
    
    @OneToMany(mappedBy = "pais") // Isso permite que um país tenha varios autores.
    private List<Autor> autores;
    
    
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
    
    public List<Autor> getAutores(){
        return this.autores;
    }
    
    public void setAutores(List<Autor> autores){
        this.autores = autores;
    }
}
