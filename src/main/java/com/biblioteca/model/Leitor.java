
package com.biblioteca.model;

import jakarta.persistence.*;

@Entity                 // Diz ao Spring que essa classe representa uma tabela.
@Table(name = "leitor")  // Liga a entidade à tabela livro no banco.
public class Leitor {
    
    @Id                                                  // Indica qual atributo é a chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indica que o atributo é auto incrementado.
    private int id_leitor;
    
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String registro;
    
    public Leitor(int id_leitor, String nome, String registro, String telefone, String email, String endereco){
        this.id_leitor = id_leitor;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.registro = registro;
    }

    
    public Leitor(String nome, String endereco, String telefone, String email, String registro){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.registro = registro;
    }
    
    public Leitor(){
        
    }
    
    @Override
    public String toString() {
        return nome; // ou return nome + " (" + registro + ")";
    }
    
    public int getId_leitor(){
        return this.id_leitor;
    }

    public void setId_usuario(int id_usuario){
        this.id_leitor = id_usuario;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getEndereco(){
        return this.endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    public String getTelefone(){
        return this.telefone;
    }
    
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getRegistro(){
        return this.registro;
    }
    
    public void setRegistro(String registro){
        this.registro = registro;
    }
    
}
