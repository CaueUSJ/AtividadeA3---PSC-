
package com.biblioteca.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

        
public class LoginTela extends JFrame {
    
    private JTextField txtRegistro;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    
    
    public LoginTela() {

        setTitle("Login");
        setSize(250, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); // layout principal da janela
        
        // Painel dos campos
        
        JPanel painelCampos = new JPanel(new GridLayout(2, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));
        
        txtRegistro = new JTextField(20);
        txtSenha = new JPasswordField(20);
  
        painelCampos.add(new JLabel("Registro: "));
        painelCampos.add(txtRegistro);
        painelCampos.add(new JLabel("Senha: "));
        painelCampos.add(txtSenha);
        
        // Painel do botão
        btnEntrar = new JButton("Entrar");
        JPanel painelBotao = new JPanel();
        
        painelBotao.setLayout(new FlowLayout(FlowLayout.CENTER));
        painelBotao.add(btnEntrar);
        
        btnEntrar.addActionListener(e -> validarLogin());
        
        // Adicionar tudo à tela
        add(painelCampos, BorderLayout.CENTER);
        add(painelBotao, BorderLayout.SOUTH);
        
        setVisible(true);

    }
    
  
    private void validarLogin(){
        
        String registro = txtRegistro.getText();
        String senha = new String(txtSenha.getPassword());
        
        String urlBD = "jdbc:mysql://localhost:3306/biblioteca";
        String usuarioBD = "root";
        String senhaBD = "usjt";
        
        
        try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, senhaBD)) {
            String sql = "SELECT * FROM usuario WHERE registro = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, registro);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                
                String nome = rs.getString("nome");
                
                //JOptionPane.showMessageDialog(null, "Login bem-sucedido! ");
                
                dispose(); // Fecha a tela de login
                // Abre o sistema principal
                DashboardTela dashboardTela = new DashboardTela(nome, registro);
                dashboardTela.setVisible(true);
                
            } else {
                JOptionPane.showMessageDialog(null, "Registro ou senha inválidos! ");
            }            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados. ");
        }
    }
    
}
