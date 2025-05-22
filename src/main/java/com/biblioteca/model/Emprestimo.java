
package com.biblioteca.model;

import jakarta.persistence.*;

@Entity                 // Diz ao Spring que essa classe representa uma tabela.
@Table(name = "emprestimo")  // Liga a entidade à tabela livro no banco.
public class Emprestimo {
    @Id                                                  // Indica qual atributo é a chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indica que o atributo é auto incrementado.
    private int id_emprestimo;
    
    private String data_emprestimo;
    private String data_devolucao;
    private int id_usuario;
    private int id_livro;
    
    public int getId_emprestimo(){
        return this.id_emprestimo;
    }
    
    public void setId_emprestimo(int id_emprestimo){
        this.id_emprestimo = id_emprestimo;
    }
    
    public String getData_emprestimo(){
        return this.data_emprestimo;
    }
    
    public void setData_emprestimo(String data_emprestimo){
        this.data_emprestimo = data_emprestimo;
    }
    
    public String getData_devolucao(){
        return this.data_devolucao;
    }
    
    public void setData_devolucao(String data_devolucao){
        this.data_devolucao = data_devolucao;
    }
    
    public int getId_usuario(){
        return this.id_usuario;
    }
    
    public void setId_usuario(int id_usuario){
        this.id_usuario = id_usuario;
    }
    
    public int getId_livro(){
        return this.id_livro;
    }
    
    public void setId_livro(int id_livro){
        this.id_livro = id_livro;
    }
    
}
