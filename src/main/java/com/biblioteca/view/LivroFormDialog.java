
package com.biblioteca.view;

import javax.swing.*;
import java.awt.*;
import com.biblioteca.model.Livro;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LivroFormDialog extends JFrame {
    
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtGenero;
    private JTextField txtEditora;
    private JTextField txtNum_Paginas;
    private JTextField txtAno;
    private JTextField txtIsbn;
    private JButton btnSalvar;
    private JButton btnCancelar;
    
    public LivroFormDialog(Livro livroExistente){
        
        boolean editar = livroExistente != null;
        
        setTitle("Sistema Biblioteca - Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null); // Centraliza a janela
        setLayout(new BorderLayout());

        JPanel painelCampos = new JPanel(new GridLayout(7, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtTitulo = new JTextField(20);
        txtAutor = new JTextField(20);
        txtGenero = new JTextField(20);
        txtEditora = new JTextField(20);
        txtNum_Paginas = new JTextField(20);
        txtAno = new JTextField(20);
        txtIsbn = new JTextField(20);

        painelCampos.add(new JLabel("Titulo:"));
        painelCampos.add(txtTitulo);
        painelCampos.add(new JLabel("Autor:"));
        painelCampos.add(txtAutor);
        painelCampos.add(new JLabel("Genero:"));
        painelCampos.add(txtGenero);
        painelCampos.add(new JLabel("Editora:"));
        painelCampos.add(txtEditora);
        painelCampos.add(new JLabel("Num. Paginas:"));
        painelCampos.add(txtNum_Paginas);
        painelCampos.add(new JLabel("Ano:"));
        painelCampos.add(txtAno);
        painelCampos.add(new JLabel("ISBN:"));
        painelCampos.add(txtIsbn);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        
        if (editar) {
            txtTitulo.setText(livroExistente.getTitulo());
            txtAutor.setText(livroExistente.getAutor());
            txtGenero.setText(livroExistente.getGenero());
            txtEditora.setText(livroExistente.getEditora());
            txtNum_Paginas.setText(livroExistente.getNum_paginas());
            txtAno.setText(livroExistente.getAno());
            txtIsbn.setText(livroExistente.getIsbn());

            //txtRegistro.setEditable(false); // Registro não pode ser alterado
        }

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Evento de clique no botão salvar        
        btnSalvar.addActionListener(e -> salvarLivro(livroExistente));
        
        // Evento cancelar
        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
        
    }

    private void salvarLivro(Livro livroExistente) {
        
        boolean editar = livroExistente != null;
        
        try {
            String titulo = txtTitulo.getText();
            String autor = txtAutor.getText();
            String genero = txtGenero.getText();
            String editora = txtEditora.getText();
            String num_paginas = txtNum_Paginas.getText();
            String ano = txtAno.getText();
            String isbn = txtIsbn.getText();

            if (titulo.isEmpty() || autor.isEmpty() || genero.isEmpty() || editora.isEmpty() || num_paginas.isEmpty() || ano.isEmpty() || isbn.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
                return;
            }
            
            if (editar) {
                Livro livroEditado = new Livro(livroExistente.getTitulo(), autor, genero, editora, num_paginas, ano, isbn);
                if(editarLivro(livroEditado)){
                    JOptionPane.showMessageDialog(this, "Livro atualizado com sucesso");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Falha 5.");
                }
                
            } else {
                Livro novoLivro = new Livro(titulo, autor, genero, editora, num_paginas, ano, isbn);
                if (adicionarLivro(novoLivro)) {
                    JOptionPane.showMessageDialog(this, "Livro salvo com sucesso!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Falha 3.");
                }
            }            
            
            
        }  catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Falha 1.");
        }
        
    }
    
    public boolean editarLivro(Livro livro){
        String sql = "UPDATE livro SET autor = ?, genero = ?, editora = ?, num_paginas = ?, ano = ?, isbn = ? WHERE titulo = ?";
        
        String urlBD = "jdbc:mysql://localhost:3306/biblioteca";
        String usuarioBD = "root";
        String senhaBD = "usjt";
        
        try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, senhaBD)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, livro.getAutor());
            stmt.setString(2, livro.getGenero());
            stmt.setString(3, livro.getEditora());
            stmt.setString(4, livro.getNum_paginas());
            stmt.setString(5, livro.getAno());
            stmt.setString(6, livro.getIsbn());
            stmt.setString(7, livro.getTitulo());
            
            int rows = stmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Falha 4.");
            return false;
        }
        
    }
    
    public boolean adicionarLivro(Livro livro) {
        
        String sql = "INSERT INTO livro (titulo, autor, genero, editora, num_paginas, ano, isbn) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        String urlBD = "jdbc:mysql://localhost:3306/biblioteca";
        String usuarioBD = "root";
        String senhaBD = "usjt";
        
        try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, senhaBD)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(2, livro.getAutor());
            stmt.setString(3, livro.getGenero());
            stmt.setString(4, livro.getEditora());
            stmt.setString(5, livro.getNum_paginas());
            stmt.setString(6, livro.getAno());
            stmt.setString(7, livro.getIsbn());
            stmt.setString(1, livro.getTitulo());
            
            int rows = stmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Falha 2.");
            return false;
        }        
        
    }
}
