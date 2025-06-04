package com.biblioteca.Teste;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaLivros extends JFrame {

    private JPanel painelCards;

    public TelaLivros() {
        setTitle("Gerenciamento de Livros");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        // Layout principal: vertical
        setLayout(new BorderLayout());

        // HEADER
        JPanel header = new JPanel();
        header.setBackground(new Color(44, 62, 80));
        header.setPreferredSize(new Dimension(0, 60));
        header.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel titulo = new JLabel("📚 Livros");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 20));
        header.add(titulo);
        add(header, BorderLayout.NORTH);

        // Painel principal dividido em cards (LEFT) + botões (RIGHT)
        JPanel painelConteudo = new JPanel(new BorderLayout());
        add(painelConteudo, BorderLayout.CENTER);

        // Painel dos cards (lado esquerdo)
        painelCards = new JPanel(new GridLayout(0, 3, 15, 15));
        painelCards.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelCards.setBackground(new Color(240, 240, 245));

        JScrollPane scroll = new JScrollPane(painelCards);
        scroll.setBorder(null);
        painelConteudo.add(scroll, BorderLayout.CENTER);

        // Painel lateral com botões
        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(new BoxLayout(painelDireito, BoxLayout.Y_AXIS));
        painelDireito.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelDireito.setPreferredSize(new Dimension(180, 0));
        painelDireito.setBackground(new Color(245, 245, 250));

        JButton btnAdicionar = new JButton("➕ Adicionar");
        JButton btnAtualizar = new JButton("🔄 Atualizar");

        for (JButton btn : new JButton[]{btnAdicionar, btnAtualizar}) {
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            btn.setMaximumSize(new Dimension(160, 40));
            btn.setFont(new Font("SansSerif", Font.PLAIN, 14));
            btn.setFocusPainted(false);
            btn.setBackground(new Color(220, 230, 240));
            painelDireito.add(btn);
            painelDireito.add(Box.createVerticalStrut(20));
        }

        painelConteudo.add(painelDireito, BorderLayout.EAST);

        // Exibir os livros iniciais
        atualizarListaLivros(criarLivrosExemplo());

        setVisible(true);
    }

    private List<Livro> criarLivrosExemplo() {
        List<Livro> livros = new ArrayList<>();
        livros.add(new Livro("O Hobbit", "J.R.R. Tolkien", "Fantasia", 1937));
        livros.add(new Livro("A Revolução dos Bichos", "George Orwell", "Fábula", 1945));
        livros.add(new Livro("Dom Casmurro", "Machado de Assis", "Romance", 1899));
        livros.add(new Livro("It: A Coisa", "Stephen King", "Terror", 1986));
        livros.add(new Livro("A Arte da Guerra", "Sun Tzu", "Estratégia", -500));
        return livros;
    }

    private void atualizarListaLivros(List<Livro> livros) {
        painelCards.removeAll();

        for (Livro livro : livros) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(Color.WHITE);
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));

            card.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    card.setBackground(new Color(230, 240, 255));
                }

                public void mouseExited(java.awt.event.MouseEvent evt) {
                    card.setBackground(Color.WHITE);
                }
            });

            JLabel lblTitulo = new JLabel("📘 " + livro.getTitulo());
            lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 14));

            JLabel lblAutor = new JLabel("Autor: " + livro.getAutor());
            JLabel lblGenero = new JLabel("Gênero: " + livro.getGenero());
            JLabel lblAno = new JLabel("Ano: " + livro.getAno());

            lblAutor.setFont(lblGenero.getFont().deriveFont(12f));
            lblGenero.setFont(lblGenero.getFont().deriveFont(12f));
            lblAno.setFont(lblGenero.getFont().deriveFont(12f));

            JButton btnEditar = new JButton("Editar");
            JButton btnExcluir = new JButton("Excluir");
            for (JButton btn : new JButton[]{btnEditar, btnExcluir}) {
                btn.setFont(new Font("SansSerif", Font.PLAIN, 12));
                btn.setFocusPainted(false);
                btn.setBackground(new Color(240, 240, 240));
            }

            JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            painelBotoes.setOpaque(false);
            painelBotoes.add(btnEditar);
            painelBotoes.add(btnExcluir);

            card.add(lblTitulo);
            card.add(Box.createVerticalStrut(5));
            card.add(lblAutor);
            card.add(lblGenero);
            card.add(lblAno);
            card.add(Box.createVerticalGlue());
            card.add(painelBotoes);

            painelCards.add(card);
        }

        painelCards.revalidate();
        painelCards.repaint();
    }

    public static void main(String[] args) {
        new TelaLivros();
    }
}