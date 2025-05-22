
package com.biblioteca.model;

import jakarta.persistence.*;

@Entity
@Table(name = "editora")
public class Editora {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_editora;

    private String nome;

    public int getId_editora() {
        return this.id_editora;
    }

    public void setId_editora(int id_editora) {
        this.id_editora = id_editora;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
