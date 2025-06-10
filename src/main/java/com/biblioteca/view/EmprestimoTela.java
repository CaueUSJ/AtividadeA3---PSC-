
package com.biblioteca.view;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class EmprestimoTela extends JFrame {
    
    private JTable tabelaEmprestimos;
    private DefaultTableModel modeloTabela;
    private JButton btnNovo, btnAtualizar, btnDevolver;
    
    public EmprestimoTela(String nomeUsuario, String registroUsuario) {
        setTitle("Gerenciamento dos empréstimos");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // ===== HEADER =====
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(220, 220, 220));
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 15));

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
        
        // Esquerda - Tabela
        modeloTabela = new DefaultTableModel(new String[]{"Leitor", "Livro", "Data Empréstimo", "Data Devolução"}, 0);
        tabelaEmprestimos = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaEmprestimos);
        painelCentro.add(scrollTabela, BorderLayout.CENTER);
        
        // === PAINEL DE BOTÕES ===
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new BoxLayout(painelBotoes, BoxLayout.Y_AXIS));
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        Dimension tamanhoBotao = new Dimension(150, 40);
        
        btnNovo = new JButton("Novo Empréstimo");
        btnDevolver = new JButton("Devolver Livro");
        btnAtualizar = new JButton("Atualizar");


        // Lista de botões para aplicar estilo
        JButton[] botoes = {btnNovo, btnDevolver, btnAtualizar};

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
        
        
        // Ações
        //btnNovo.addActionListener(e -> new EmprestimoFormDialog());
        //btnAtualizar.addActionListener(e -> atualizarTabela());

        //btnDevolver.addActionListener(e -> marcarComoDevolvido());

       // add(scroll, BorderLayout.CENTER);
       // painelCentro.add(painelBotoes, BorderLayout.EAST);

        setVisible(true);
        
    }
}
