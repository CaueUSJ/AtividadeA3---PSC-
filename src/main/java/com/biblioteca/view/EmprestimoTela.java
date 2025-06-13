
package com.biblioteca.view;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import com.biblioteca.dao.EmprestimoDAO;
import com.biblioteca.dao.LeitorDAO;
import com.biblioteca.dao.LivroDAO;
import com.biblioteca.model.Emprestimo;
import java.text.SimpleDateFormat;
import java.util.List;

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
        modeloTabela = new DefaultTableModel(new String[]{"ID", "Leitor", "Livro", "Data Empréstimo", "Data Devolução", "Devolvido"}, 0) {
            @Override
             public boolean isCellEditable(int row, int column) { 
            return false;
             }            
        };
        
        tabelaEmprestimos = new JTable(modeloTabela);
        tabelaEmprestimos.getColumnModel().getColumn(0).setMinWidth(0);
        tabelaEmprestimos.getColumnModel().getColumn(0).setMaxWidth(0);
        tabelaEmprestimos.getColumnModel().getColumn(0).setPreferredWidth(0);
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
        btnNovo.addActionListener(e -> {
            EmprestimoFormDialog dialog = new EmprestimoFormDialog(this);
            dialog.setVisible(true);
            atualizarTabela(); // Atualiza a lista após o cadastro
        });
        
        btnAtualizar.addActionListener(e -> atualizarTabela());

        btnDevolver.addActionListener(e -> marcarComoDevolvido());

       // add(scroll, BorderLayout.CENTER);
       // painelCentro.add(painelBotoes, BorderLayout.EAST);

        atualizarTabela();
        setVisible(true);
        
    }
    
    private void atualizarTabela() {
        modeloTabela.setRowCount(0); // Limpa a tabela

        EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
        LeitorDAO leitorDAO = new LeitorDAO();
        LivroDAO livroDAO = new LivroDAO();

        List<Emprestimo> lista = emprestimoDAO.listarTodos(); 
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        for (Emprestimo emp : lista) {
            String registroLeitor = "Desconhecido";
            String tituloLivro = "Desconhecido";

            var leitor = leitorDAO.buscarPorId(emp.getId_leitor());
            var livro = livroDAO.buscarPorId(emp.getId_livro());

            if (leitor != null) {
                registroLeitor = leitor.getRegistro();
            }

            if (livro != null) {
                tituloLivro = livro.getTitulo();
            }

            String dataEmprestimo = sdf.format(emp.getDataEmprestimo());
            String dataDevolucao = emp.getDataDevolucao() != null
                    ? sdf.format(emp.getDataDevolucao())
                    : "Pendente";
            String devolvido = emp.isDevolvido() ? "Sim" : "Não";
            modeloTabela.addRow(new Object[]{emp.getId_emprestimo(), registroLeitor, tituloLivro, dataEmprestimo, dataDevolucao, devolvido});
        }
    }
    
    private void marcarComoDevolvido() {
        int linha = tabelaEmprestimos.getSelectedRow();
        if (linha == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um empréstimo na tabela.");
            return;
        }

        // Coluna 0 é o ID (oculta); coluna 2 é o título
        int idEmprestimo = (Integer) modeloTabela.getValueAt(linha, 0);
        String tituloLivro = (String) modeloTabela.getValueAt(linha, 2);

        int conf = JOptionPane.showConfirmDialog(
                this,
                "Marcar o livro \"" + tituloLivro + "\" como devolvido?",
                "Confirmar Devolução",
                JOptionPane.YES_NO_OPTION);

        if (conf == JOptionPane.YES_OPTION) {
            EmprestimoDAO dao = new EmprestimoDAO();
            if (dao.marcarComoDevolvido(idEmprestimo)) {
                JOptionPane.showMessageDialog(this, "Devolução registrada com sucesso!");
                atualizarTabela();   // recarrega a lista
            } else {
                JOptionPane.showMessageDialog(this, "Falha ao registrar devolução.");
            }
        }
    }
    
}
