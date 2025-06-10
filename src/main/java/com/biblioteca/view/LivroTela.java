
package com.biblioteca.view;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import com.biblioteca.model.Usuario;
import com.biblioteca.model.Livro;

public class LivroTela extends JFrame {
    
    private JTable tabelaLivros;
    private DefaultTableModel modeloTabela;
    
    public LivroTela(String nomeUsuario, String registroUsuario) {
        
        setTitle("Gerenciamento de Livros");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // ----Header----
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(220,220,220));
        header.setBorder(BorderFactory.createEmptyBorder(10,10,10,15));
        
        JLabel lblUsuario = new JLabel("Usuário: " + nomeUsuario + "  |  Registro: " + registroUsuario);
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.addActionListener(e -> {
            dispose(); // Fecha a tela atual
            new DashboardTela(nomeUsuario, registroUsuario).setVisible(true); // Retorna para tela de Dashboard
        });
        
        header.add(lblUsuario, BorderLayout.WEST);
        header.add(btnVoltar, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);
        
        // ===== PAINEL CENTRAL (tabela à esquerda, botões à direita) =====
        JPanel painelCentro = new JPanel(new BorderLayout());
        
        // === TABELA DE USUÁRIOS ===
        modeloTabela = new DefaultTableModel(new String[]{"Titulo", "Autor", "Genero", "Editora", "Num. Págs.", "Ano", "ISBN"}, 0);
        tabelaLivros = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaLivros);
        painelCentro.add(scrollTabela, BorderLayout.CENTER);
        
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Dimension tamanhoBotao = new Dimension(150, 40);
        
        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnExcluir = new JButton("Excluir");
        JButton btnAtualizar = new JButton("Atualizar");

        // Lista de botões para aplicar estilo
        JButton[] botoes = {btnAdicionar, btnEditar, btnExcluir, btnAtualizar};

        for (JButton btn : botoes) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza no eixo X
            btn.setPreferredSize(tamanhoBotao);
            btn.setMaximumSize(tamanhoBotao); // Garante que não passe do tamanho
            btn.setFocusPainted(false); // Remove contorno do foco
            painelBotoes.add(Box.createVerticalStrut(10)); // Espaço entre os botões
            painelBotoes.add(btn);
        }

        painelCentro.add(painelBotoes, BorderLayout.EAST);
        add(painelCentro, BorderLayout.CENTER);
        
        // === Eventos dos botões ===
        btnAdicionar.addActionListener(e -> {
            new LivroFormDialog(null).setVisible(true);
            carregarLivros();
        });
        
       
        btnEditar.addActionListener(e -> {
            int linhaSelecionada = tabelaLivros.getSelectedRow();
            if (linhaSelecionada >= 0) {
                Livro livroSelecionado = obterDadosLinha(linhaSelecionada);
                new LivroFormDialog(livroSelecionado).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um usuário para editar.");
                return;
            }
            carregarLivros();
        });
        
        btnExcluir.addActionListener(e -> excluirLivro());
        btnAtualizar.addActionListener(e -> carregarLivros());
        

        carregarLivros();

        setVisible(true);
    }
    
    public void carregarLivros() {
        modeloTabela.setRowCount(0); // Limpa a tabela
        
        String urlBD = "jdbc:mysql://localhost:3306/biblioteca";
        String usuarioBD = "root";
        String senhaBD = "biblioteca2025";
        
        try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, senhaBD)) {
            String sql = "SELECT * FROM livro";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                modeloTabela.addRow(new Object[]{
                    rs.getString("titulo"),
                    rs.getString("Autor"),
                    rs.getString("Genero"),
                    rs.getString("Editora"),
                    rs.getString("num_paginas"),
                    rs.getString("Ano"),
                    rs.getString("isbn")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar o livro.");
        }
    }
    
    private Livro obterDadosLinha(int linha) {        
        String titulo = (String) modeloTabela.getValueAt(linha, 0);        
        String autor = (String) modeloTabela.getValueAt(linha, 1); 
        String genero = (String) modeloTabela.getValueAt(linha, 2); 
        String editora = (String) modeloTabela.getValueAt(linha, 3); 
        String num_paginas = (String) modeloTabela.getValueAt(linha, 4); 
        String ano = (String) modeloTabela.getValueAt(linha, 5);
        String isbn = (String) modeloTabela.getValueAt(linha, 6);
        
        return new Livro(titulo, autor, genero, editora, num_paginas, ano, isbn);
    }    

    
    private void excluirLivro() {
        int linha = tabelaLivros.getSelectedRow();
        
        String urlBD = "jdbc:mysql://localhost:3306/biblioteca";
        String usuarioBD = "root";
        String senhaBD = "biblioteca2025";        
        
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um livro para excluir.");
            return;
        }
        String titulo = modeloTabela.getValueAt(linha, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, senhaBD)) {
                String sql = "DELETE FROM livro WHERE titulo = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, titulo);
                stmt.executeUpdate();
                carregarLivros();
                JOptionPane.showMessageDialog(this, "Livro excluído com sucesso.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir o livro.");
            }
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LivroTela("NomeUsuario", "123456").setVisible(true);
        });
    }
    
}
