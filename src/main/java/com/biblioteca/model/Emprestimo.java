
package com.biblioteca.model;

import com.biblioteca.model.Livro;
import jakarta.persistence.*;
import java.sql.Date;

@Entity                 // Diz ao Spring que essa classe representa uma tabela.
@Table(name = "emprestimo")  // Liga a entidade à tabela livro no banco.
public class Emprestimo {
    
    @Id                                                  // Indica qual atributo é a chave primária.
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Indica que o atributo é auto incrementado.
    private int id_emprestimo;
    
    private Date dataEmprestimo;
    private Date dataDevolucao;
    private int id_leitor;
    private int id_livro;
    private boolean devolvido;

    
    public Emprestimo(int id_leitor, int id_livro, Date dataEmprestimo, Date dataDevolucao, boolean devolvido) {
        this.id_leitor = id_leitor;
        this.id_livro = id_livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
    }

    public Emprestimo(int id_emprestimo, int id_leitor, int id_livro, Date dataEmprestimo, Date dataDevolucao, boolean devolvido) {
        this.id_emprestimo = id_emprestimo;
        this.id_leitor = id_leitor;
        this.id_livro = id_livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
        this.devolvido = devolvido;
    }
    
    public int getId_emprestimo(){
        return this.id_emprestimo;
    }
    
    public void setId_emprestimo(int id_emprestimo){
        this.id_emprestimo = id_emprestimo;
    }
    
    public Date getDataEmprestimo(){
        return this.dataEmprestimo;
    }
    
    public void setDataEmprestimo(Date dataEmprestimo){
        this.dataEmprestimo = dataEmprestimo;
    }
    
    public Date getDataDevolucao(){
        return this.dataDevolucao;
    }
    
    public void setDataDevolucao(Date dataDevolucao){
        this.dataDevolucao = dataDevolucao;
    }
    
    public int getId_leitor(){
        return this.id_leitor;
    }
    
    public String getRegistroLeitor(){
        Leitor leitor = new Leitor();
        return leitor.getRegistro();
    }
    
    public void setId_leitor(int id_leitor){
        this.id_leitor = id_leitor;
    }
    
    public int getId_livro(){
        return this.id_livro;
    }
    
    public String getTituloLivro(){
        Livro livro = new Livro();
        return livro.getTitulo();
    }
    
    public void setId_livro(int id_livro){
        this.id_livro = id_livro;
    }
    
    public boolean isDevolvido() {
    return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
    
}
