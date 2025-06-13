package com.biblioteca.view;

import com.biblioteca.dao.EmprestimoDAO;
import com.biblioteca.dao.LeitorDAO;
import com.biblioteca.dao.LivroDAO;
import com.biblioteca.model.Emprestimo;
import com.biblioteca.model.Leitor;
import com.biblioteca.model.Livro;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class EmprestimoFormDialog extends JDialog {

    private JComboBox<Leitor> comboLeitor;
    private JComboBox<Livro> comboLivro;
    private JFormattedTextField campoDataEmprestimo;
    private JFormattedTextField campoDataDevolucao;
    private JButton botaoSalvar;

    private EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    private LeitorDAO leitorDAO = new LeitorDAO();
    private LivroDAO livroDAO = new LivroDAO();

    public EmprestimoFormDialog(JFrame parent) {
        super(parent, "Novo Empréstimo", true);

        configurarComponentes();
        montarLayout();

        pack();
        setLocationRelativeTo(parent);
    }

    private void configurarComponentes() {
        comboLeitor = new JComboBox<>();
        comboLivro = new JComboBox<>();

        campoDataEmprestimo = new JFormattedTextField(new java.text.SimpleDateFormat("dd/MM/yyyy"));
        campoDataDevolucao = new JFormattedTextField(new java.text.SimpleDateFormat("dd/MM/yyyy"));

        campoDataEmprestimo.setColumns(10);
        campoDataDevolucao.setColumns(10);

        // Preenche datas
        campoDataEmprestimo.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        campoDataDevolucao.setText(LocalDate.now().plusDays(7).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Preenche combo de leitores
        List<Leitor> leitores = leitorDAO.buscarTodos();
        for (Leitor l : leitores) {
            comboLeitor.addItem(l);
        }

        // Preenche combo de livros disponíveis
        List<Livro> livros = livroDAO.buscarTodosDisponiveis();
        for (Livro l : livros) {
            comboLivro.addItem(l);
        }

        // Botão
        botaoSalvar = new JButton("Salvar");
        botaoSalvar.addActionListener(e -> salvarEmprestimo());
    }

    private void montarLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Leitor:"), gbc);
        gbc.gridx = 1;
        add(comboLeitor, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Livro:"), gbc);
        gbc.gridx = 1;
        add(comboLivro, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Data:"), gbc);
        gbc.gridx = 1;
        add(campoDataEmprestimo, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Data de Devolução:"), gbc);
        gbc.gridx = 1;
        add(campoDataDevolucao, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(botaoSalvar, gbc);
    }

    private void salvarEmprestimo() {
        Leitor leitor = (Leitor) comboLeitor.getSelectedItem();
        Livro livro = (Livro) comboLivro.getSelectedItem();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            Date dataEmprestimo = Date.valueOf(LocalDate.parse(campoDataEmprestimo.getText(), formatter));
            Date dataDevolucao = Date.valueOf(LocalDate.parse(campoDataDevolucao.getText(), formatter));

            if (leitor == null || livro == null) {
                JOptionPane.showMessageDialog(this, "Selecione um leitor e um livro.");
                return;
            }

            Emprestimo novo = new Emprestimo();
            novo.setId_leitor(leitor.getId_leitor());
            novo.setId_livro(livro.getId_livro());
            novo.setDataEmprestimo(dataEmprestimo);
            novo.setDataDevolucao(dataDevolucao);
            novo.setDevolvido(false);

            if (emprestimoDAO.adicionarEmprestimo(novo)) {
                JOptionPane.showMessageDialog(this, "Empréstimo cadastrado com sucesso!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar o empréstimo.");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Data inválida. Use o formato dd/MM/yyyy.");
        }
    }
}
