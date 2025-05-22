
package com.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "genero")
public class Genero {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_genero;

    private String nome;

    public int getId_genero() {
        return this.id_genero;
    }

    public void setId_genero(int id_genero) {
        this.id_genero = id_genero;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
