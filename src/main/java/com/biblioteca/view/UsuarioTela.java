
package com.biblioteca.view;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import com.biblioteca.model.Usuario;


public class UsuarioTela extends JFrame {

    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;
    
    public UsuarioTela(String nomeUsuario, String registroUsuario) {
        setTitle("Gerenciamento de Usuários");
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
            new DashboardUsuarioTela(nomeUsuario, registroUsuario).setVisible(true); // Retorna para tela de Dashboard
        });

        header.add(lblUsuario, BorderLayout.WEST);
        header.add(btnVoltar, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // ===== PAINEL CENTRAL (tabela à esquerda, botões à direita) =====
        JPanel painelCentro = new JPanel(new BorderLayout());

        // === TABELA DE USUÁRIOS ===
        modeloTabela = new DefaultTableModel(new String[]{"Registro", "Nome", "Endereço", "Telefone", "E-mail", "Senha"}, 0);
        tabelaUsuarios = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaUsuarios);
        painelCentro.add(scrollTabela, BorderLayout.CENTER);

        // === PAINEL DE BOTÕES ===
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
            new UsuarioFormDialog(null).setVisible(true);
            carregarUsuarios();
        });
        
        btnEditar.addActionListener(e -> {
            int linhaSelecionada = tabelaUsuarios.getSelectedRow();
            if (linhaSelecionada >= 0) {
                Usuario usuarioSelecionado = obterDadosLinha(linhaSelecionada);
                new UsuarioFormDialog(usuarioSelecionado).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um usuário para editar.");
                return;
            }
            carregarUsuarios();
        });
        
        btnExcluir.addActionListener(e -> excluirUsuario());
        btnAtualizar.addActionListener(e -> carregarUsuarios());
        

        carregarUsuarios();

        setVisible(true);
    }

    // === Função para carregar dados do banco na tabela ===

    public void carregarUsuarios() {
        modeloTabela.setRowCount(0); // Limpa a tabela
        
        String urlBD = "jdbc:mysql://localhost:3306/biblioteca";
        String usuarioBD = "root";
        String senhaBD = "biblioteca2025";
        
        try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, senhaBD)) {
            String sql = "SELECT * FROM usuario";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                modeloTabela.addRow(new Object[]{
                    rs.getString("registro"),
                    rs.getString("nome"),
                    rs.getString("endereco"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("Senha")
                });
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar usuários.");
        }
    }

    private Usuario obterDadosLinha(int linha) {        
        String registro = (String) modeloTabela.getValueAt(linha, 0);        
        String nome = (String) modeloTabela.getValueAt(linha, 1); 
        String endereco = (String) modeloTabela.getValueAt(linha, 2); 
        String telefone = (String) modeloTabela.getValueAt(linha, 3); 
        String email = (String) modeloTabela.getValueAt(linha, 4); 
        String senha = (String) modeloTabela.getValueAt(linha, 5);
        
        return new Usuario(nome, endereco, telefone, email, registro, senha);
    }    

    private void excluirUsuario() {
        int linha = tabelaUsuarios.getSelectedRow();
        
        String urlBD = "jdbc:mysql://localhost:3306/biblioteca";
        String usuarioBD = "root";
        String senhaBD = "biblioteca2025";        
        
        if (linha < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para excluir.");
            return;
        }
        String registro = modeloTabela.getValueAt(linha, 0).toString();
        int confirm = JOptionPane.showConfirmDialog(this, "Deseja realmente excluir?", "Confirmação", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (Connection conn = DriverManager.getConnection(urlBD, usuarioBD, senhaBD)) {
                String sql = "DELETE FROM usuario WHERE registro = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, registro);
                stmt.executeUpdate();
                carregarUsuarios();
                JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir usuário.");
            }
        }
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new UsuarioTela("NomeUsuario", "123456").setVisible(true);
        });
    }
}
