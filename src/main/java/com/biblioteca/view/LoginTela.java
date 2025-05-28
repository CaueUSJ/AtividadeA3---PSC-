
package com.biblioteca.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

        
public class LoginTela extends JFrame {
    
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnEntrar;
    
    
    public LoginTela() {

        setTitle("Login");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2, 5, 5));


        txtUsuario = new JTextField(15);
        txtSenha = new JPasswordField(15);
        btnEntrar = new JButton("Entrar");


        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(3, 2));
        painel.add(new JLabel("Usuário: "));
        painel.add(txtUsuario);
        painel.add(new JLabel("Senha: "));
        painel.add(txtSenha);
        painel.add(new JLabel(""));
        painel.add(btnEntrar);
        
        btnEntrar.addActionListener(e -> validarLogin());
        
        add(painel);
        setVisible(true);

    }
    
  
    private void validarLogin(){
        
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());
        
        
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "biblioteca2025")) {
            String sql = "SELECT * FROM usuario WHERE registro = ? AND senha = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Login bem-sucedido! ");
                
                dispose(); // Fecha a tela de login
                // Abre o sistema principal
                
            } else {
                JOptionPane.showMessageDialog(null, "Registro ou senha inválidos! ");
            }            
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados. ");
        }
    }
    
}
