
package com.biblioteca.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DashboardUsuarioTela extends JFrame {
    
    private String nomeUsuario;
    private String registroUsuario;
    
    public DashboardUsuarioTela(String nomeUsuario, String registroUsuario) {
        
        this.nomeUsuario = nomeUsuario;
        this.registroUsuario = registroUsuario;    
       
        setTitle("Sistema de Usuarios - Dashboard");
        setSize(500, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela
        
        // ===== TOPO - Header =====
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(220, 220, 220));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblUsuario = new JLabel("Usuário: " + nomeUsuario + "  |  Registro: " + registroUsuario);
        JButton btnSair = new JButton("Voltar");

        header.add(lblUsuario, BorderLayout.WEST);
        header.add(btnSair, BorderLayout.EAST);

        // Ação do botão Sair
        btnSair.addActionListener(e -> {
            dispose(); // fecha a tela atual
            new DashboardTela(nomeUsuario, registroUsuario).setVisible(true);
        });
        
        // Painel principal com layout de grade
        JPanel painel = new JPanel(new GridLayout(2, 1, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        
        JButton btnAdm = new JButton("Administradores");
        JButton btnLeitor = new JButton("Leitores");

        painel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50)); // Margem
        painel.add(btnAdm);
        painel.add(btnLeitor);

        add(header, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);
        
        // Ações dos botões
        btnAdm.addActionListener(e -> {
            dispose();
            new UsuarioTela(nomeUsuario, registroUsuario).setVisible(true); // Abrir tela de usuários
        });
        
        
        btnLeitor.addActionListener(e -> {
            dispose();
            new LeitorTela(nomeUsuario, registroUsuario).setVisible(true);
        });
        
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DashboardUsuarioTela("NomeUsuario", "123456").setVisible(true);
        });
    }
    
}
