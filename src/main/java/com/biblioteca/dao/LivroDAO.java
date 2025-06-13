package com.biblioteca.dao;

import com.biblioteca.model.Livro;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
    private String url = "jdbc:mysql://localhost:3306/biblioteca";
    private String usuario = "root";
    private String senha = "biblioteca2025";

    public Livro buscarPorId(int id) {
        String sql = "SELECT * FROM livro WHERE id_livro = ?";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Livro(
                    rs.getInt("id_livro"),
                    rs.getString("titulo"),
                    rs.getString("autor"),
                    rs.getString("editora"),
                    rs.getString("ano"),
                    rs.getString("genero")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Livro> buscarTodosDisponiveis() {
    List<Livro> livrosDisponiveis = new ArrayList<>();
    String sql = """
        SELECT * FROM livro
        WHERE id_livro NOT IN (
            SELECT id_livro FROM emprestimo WHERE devolvido = false
        )
        ORDER BY titulo
    """;

    try (Connection conn = DriverManager.getConnection(url, usuario, senha);
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            Livro livro = new Livro(
                rs.getInt("id_livro"),
                rs.getString("titulo"),
                rs.getString("autor"),
                rs.getString("editora"),
                rs.getString("ano"),
                rs.getString("genero")
            );
            livrosDisponiveis.add(livro);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }

    return livrosDisponiveis;
    }

}
