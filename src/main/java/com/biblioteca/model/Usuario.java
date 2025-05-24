
package com.biblioteca.model;

import jakarta.persistence.*;

@Entity                 // Diz ao Spring que essa classe representa uma tabela.
@Table(name = "usuario")  // Liga a entidade à tabela livro no banco.
public class Usuario {
    
    @Id                                                  // Indica qual atributo é a chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indica que o atributo é auto incrementado.
    private int id_usuario;
    
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private String registro;
    private String senha;
    
    public int getId_usuario(){
        return this.id_usuario;
    }

    public void setId_usuario(int id_usuario){
        this.id_usuario = id_usuario;
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
    
    public String getSenha(){
        return this.senha;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
}
