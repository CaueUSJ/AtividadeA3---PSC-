
package com.biblioteca.view;

import javax.swing.*;
import java.awt.*;
import com.biblioteca.model.Leitor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LeitorFormDialog extends JFrame {
    
    private JTextField txtRegistro;
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JButton btnSalvar;
    private JButton btnCancelar;
    
    public LeitorFormDialog(Leitor leitorExistente){
        
        boolean editar = leitorExistente != null;
        
        setTitle("Sistema Biblioteca - Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null); // Centraliza a janela
        setLayout(new BorderLayout());

        JPanel painelCampos = new JPanel(new GridLayout(5, 2, 10, 10));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtRegistro = new JTextField(20);
        txtNome = new JTextField(20);
        txtEndereco = new JTextField(20);
        txtTelefone = new JTextField(20);
        txtEmail = new JTextField(20);

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


        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        
        if (editar) {
            txtRegistro.setText(leitorExistente.getRegistro());
            txtNome.setText(leitorExistente.getNome());
            txtEndereco.setText(leitorExistente.getEndereco());
            txtTelefone.setText(leitorExistente.getTelefone());
            txtEmail.setText(leitorExistente.getEmail());

            txtRegistro.setEditable(false); // Registro não pode ser alterado
        }
        
        painelBotoes.add(btnSalvar);
        painelBotoes.add(btnCancelar);

        add(painelCampos, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // Evento de clique no botão salvar        
        btnSalvar.addActionListener(e -> salvarLeitor(leitorExistente));
        
        // Evento cancelar
        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
        
    }
    
    private void salvarLeitor(Leitor leitorExistente) {
        
        boolean editar = leitorExistente != null;
        
        try {
            String registro = txtRegistro.getText();
            String nome = txtNome.getText();
            String endereco = txtEndereco.getText();
            String telefone = txtTelefone.getText();
            String email = txtEmail.getText();

            if (registro.isEmpty() || nome.isEmpty() || endereco.isEmpty() || telefone.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos.");
                return;
            }
            
            if (editar) {
                Leitor leitorEditado = new Leitor(nome, endereco, telefone, email, leitorExistente.getRegistro());
                if(editarLeitor(leitorEditado)){
                    JOptionPane.showMessageDialog(this, "Leitor atualizado com sucesso");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Falha 5.");
                }
                
            } else {
                Leitor novoLeitor = new Leitor(nome, endereco, telefone, email, registro);
                if (adicionarLeitor(novoLeitor)) {
                    JOptionPane.showMessageDialog(this, "Leitor salvo com sucesso!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Falha 3.");
                }
            }            
            
            
        }  catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Falha 1.");
        }
        
    }
    
    public boolean editarLeitor(Leitor leitor){
        String sql = "UPDATE leitor SET nome = ?, endereco = ?, telefone = ?, email = ? WHERE registro = ?";
        
        String urlBD = "jdbc:mysql://localhost:3306/biblioteca";
        String usuarioBD = "root";
        String senhaBD = "biblioteca2025";
        
        try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, senhaBD)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, leitor.getNome());
            stmt.setString(2, leitor.getEndereco());
            stmt.setString(3, leitor.getTelefone());
            stmt.setString(4, leitor.getEmail());
            stmt.setString(5, leitor.getRegistro());
            
            int rows = stmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Falha 4.");
            return false;
        }
        
    }
    
    public boolean adicionarLeitor(Leitor leitor) {
        
        String sql = "INSERT INTO leitor (nome, endereco, telefone, email, registro) VALUES (?, ?, ?, ?, ?)";
        
        String urlBD = "jdbc:mysql://localhost:3306/biblioteca";
        String usuarioBD = "root";
        String senhaBD = "biblioteca2025";
        
        try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, senhaBD)) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            
            stmt.setString(1, leitor.getNome());
            stmt.setString(2, leitor.getEndereco());
            stmt.setString(3, leitor.getTelefone());
            stmt.setString(4, leitor.getEmail());
            stmt.setString(5, leitor.getRegistro());
            
            int rows = stmt.executeUpdate();
            return rows > 0;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Falha 2.");
            return false;
        }        
        
    }
    
}
