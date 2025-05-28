
package com.biblioteca.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


public class DashboardTela extends JFrame{
        
    private String nomeUsuario;
    private String registroUsuario;
    
    public DashboardTela(String nomeUsuario, String registroUsuario) {
        
        this.nomeUsuario = nomeUsuario;
        this.registroUsuario = registroUsuario;
        
        setTitle("Sistema Biblioteca - Dashboard");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        
        
        // ===== TOPO - Header =====
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(220, 220, 220));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblUsuario = new JLabel("Usuário: " + nomeUsuario + "  |  Registro: " + registroUsuario);
        JButton btnSair = new JButton("Sair");

        header.add(lblUsuario, BorderLayout.WEST);
        header.add(btnSair, BorderLayout.EAST);

        // Ação do botão Sair
        btnSair.addActionListener(e -> {
            dispose(); // fecha a tela atual
            new LoginTela().setVisible(true); // volta à tela de login
        });
        

        // Painel principal com layout de grade
        JPanel painel = new JPanel(new GridLayout(3, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        JButton btnLivros = new JButton("Livros");
        JButton btnEmprestimos = new JButton("Empréstimos");
        JButton btnUsuarios = new JButton("Usuários");

        painel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // Margem
        painel.add(btnLivros);
        painel.add(btnEmprestimos);
        painel.add(btnUsuarios);

        add(header, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);
        
        /*
        // Ações dos botões
        btnUsuarios.addActionListener(e -> {
            new UsuarioTela().setVisible(true); // Abrir tela de usuários
        });

        btnLivros.addActionListener(e -> {
            new LivroTela().setVisible(true); // Abrir tela de livros
        });

        btnEmprestimos.addActionListener(e -> {
            new EmprestimoTela().setVisible(true); // Abrir tela de empréstimos
        });
        */
        
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DashboardTela("NomeUsuario", "123456").setVisible(true);
        });
    }
    
}
