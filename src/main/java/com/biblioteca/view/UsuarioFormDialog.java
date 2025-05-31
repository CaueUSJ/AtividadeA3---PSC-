
package com.biblioteca.view;

import javax.swing.*;
import java.awt.*;
import com.biblioteca.model.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioFormDialog extends JDialog {

    private JTextField txtRegistro;
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JTextField txtSenha;
    private JButton btnSalvar;
    private JButton btnCancelar;

    public UsuarioFormDialog(Frame parent) {
        
        super(parent, "Cadastro de Usuário", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel painelCampos = new JPanel(new GridLayout(6, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtRegistro = new JTextField(20);
        txtNome = new JTextField(20);
        txtEndereco = new JTextField(20);
        txtTelefone = new JTextField(20);
        txtEmail = new JTextField(20);
        txtSenha = new JTextField(20);

        painelCampos.add(new JLabel("Registro:"));
        painelCampos.add(txtRegistro);
        painelCampos.add(new JLabel("Nome:"));
        painelCampos.add(txtNome);
        painelCampos.add(new JLabel("Endereço:"));
        painelCampos.add(txtEndereco);
        painelCampos.add(new JLabel("Telefone:"));
        painelCampos.add(txtTelefone);
        painelCampos.add(new JLabel("Email:"));
        painelCampos.add(txtEmail);
        painelCampos.add(new JLabel("Senha:"));
        painelCampos.add(txtSenha);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Evento de clique no botão salvar        
        btnSalvar.addActionListener(e -> salvarUsuario());
        
        // Evento cancelar
        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
        
    }

    private void salvarUsuario() {
        
        try {
            String registro = txtRegistro.getText();
            String nome = txtNome.getText();
            String endereco = txtEndereco.getText();
            String telefone = txtTelefone.getText();
            String email = txtEmail.getText();
            String senha = txtSenha.getText();

            if (registro.isEmpty() || nome.isEmpty() || endereco.isEmpty() || telefone.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
                return;
            }
            
            Usuario novoUsuario = new Usuario(nome, endereco, telefone, email, registro, senha);
            if (adicionar(novoUsuario)) {
                JOptionPane.showMessageDialog(this, "Usuário salvo com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Falha 3.");
            }
        }  catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Falha 1.");
        }
        
    }
    
    public boolean adicionar(Usuario usuario) {
        
        String sql = "INSERT INTO usuario (nome, endereco, telefone, email, registro, senha) VALUES (?, ?, ?, ?, ?, ?)";
        
        String urlBD = "jdbc:mysql://localhost:3306/biblioteca";
        String usuarioBD = "root";
        String senhaBD = "biblioteca2025";
        
        try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, senhaBD)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEndereco());
            stmt.setString(3, usuario.getTelefone());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getRegistro());
            stmt.setString(6, usuario.getSenha());
            
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Falha 2.");
            return false;
        }        
        
    }
    
}