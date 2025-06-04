package com.biblioteca.Teste;

import com.biblioteca.Teste.Livro;
import com.biblioteca.view.LoginTela;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaPrincipal extends JFrame {

    public TelaPrincipal() {
        setTitle("Sistema Biblioteca - Dashboard");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JButton btnExibirLivros = new JButton("Ver Livros (Cards)");
        btnExibirLivros.addActionListener(e -> exibirLivrosComCards(criarLivrosExemplo()));

        JPanel painel = new JPanel();
        painel.add(btnExibirLivros);
        add(painel);

        setVisible(true);
    }

    private List<Livro> criarLivrosExemplo() {
        List<Livro> livros = new ArrayList<>();
        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia", 1954));
        livros.add(new Livro("1984", "George Orwell", "Distopia", 1949));
        livros.add(new Livro("Dom Casmurro", "Machado de Assis", "Romance", 1899));
        livros.add(new Livro("Cem Anos de Solidão", "Gabriel García Márquez", "Realismo Mágico", 1967));
        livros.add(new Livro("Cem Anos de Solidão", "Gabriel García Márquez", "Realismo Mágico", 1967));
        livros.add(new Livro("Cem Anos de Solidão", "Gabriel García Márquez", "Realismo Mágico", 1967));
        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia", 1954));
        livros.add(new Livro("1984", "George Orwell", "Distopia", 1949));
        livros.add(new Livro("Dom Casmurro", "Machado de Assis", "Romance", 1899));
        livros.add(new Livro("Cem Anos de Solidão", "Gabriel García Márquez", "Realismo Mágico", 1967));
        livros.add(new Livro("Cem Anos de Solidão", "Gabriel García Márquez", "Realismo Mágico", 1967));
        livros.add(new Livro("Cem Anos de Solidão", "Gabriel García Márquez", "Realismo Mágico", 1967));
        livros.add(new Livro("Cem Anos de Solidão", "Gabriel García Márquez", "Realismo Mágico", 1967));
        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia", 1954));
        livros.add(new Livro("1984", "George Orwell", "Distopia", 1949));
        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia", 1954));
        livros.add(new Livro("1984", "George Orwell", "Distopia", 1949));
        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia", 1954));
        livros.add(new Livro("1984", "George Orwell", "Distopia", 1949));
        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "Fantasia", 1954));
        livros.add(new Livro("1984", "George Orwell", "Distopia", 1949));
        return livros;
    }

    private void exibirLivrosComCards(List<Livro> livros) {
        JPanel painelLivros = new JPanel();
        painelLivros.setLayout(new GridLayout(0, 3, 15, 15));
        painelLivros.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        painelLivros.setBackground(new Color(240, 240, 245));
        
        // ===== TOPO - Header =====
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(220, 220, 220));
        header.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JLabel lblUsuario = new JLabel("Usuário: 1234  |   Registro: 1234");
        JButton btnSair = new JButton("Sair");

        header.add(lblUsuario, BorderLayout.WEST);
        header.add(btnSair, BorderLayout.EAST);

        // Ação do botão Sair
        btnSair.addActionListener(e -> {
            dispose(); // fecha a tela atual
            new LoginTela().setVisible(true); // volta à tela de login
        });
        
        

        for (Livro livro : livros) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(new Color(245, 245, 250));
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));

            // Hover effect
            card.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    card.setBackground(new Color(230, 240, 255));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    card.setBackground(new Color(245, 245, 250));
                }
            });

            JLabel titulo = new JLabel("📘 " + livro.getTitulo());
            titulo.setFont(new Font("SansSerif", Font.BOLD, 14));
            titulo.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel autor = new JLabel("Autor: " + livro.getAutor());
            autor.setFont(new Font("SansSerif", Font.PLAIN, 12));
            autor.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel genero = new JLabel("Gênero: " + livro.getGenero());
            genero.setFont(new Font("SansSerif", Font.PLAIN, 12));
            genero.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel ano = new JLabel("Ano: " + livro.getAno());
            ano.setFont(new Font("SansSerif", Font.PLAIN, 12));
            ano.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Botões
            JButton btnEditar = new JButton("Editar");
            btnEditar.setFont(new Font("SansSerif", Font.PLAIN, 12));
            btnEditar.setFocusPainted(false);
            btnEditar.setBackground(new Color(220, 230, 240));

            JButton btnExcluir = new JButton("Excluir");
            btnExcluir.setFont(new Font("SansSerif", Font.PLAIN, 12));
            btnExcluir.setFocusPainted(false);
            btnExcluir.setBackground(new Color(255, 220, 220));

            JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            painelBotoes.setOpaque(false);
            painelBotoes.add(btnEditar);
            painelBotoes.add(btnExcluir);

            card.add(titulo);
            card.add(Box.createVerticalStrut(5));
            card.add(autor);
            card.add(genero);
            card.add(ano);
            card.add(Box.createVerticalGlue());
            card.add(painelBotoes);

            painelLivros.add(card);
        }

        JFrame frame = new JFrame("Livros");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.add(new JScrollPane(painelLivros));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        add(header, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        new TelaPrincipal();
    }
}