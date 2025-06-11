package com.biblioteca.dao;

import com.biblioteca.model.Livro;
import java.sql.*;

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
}
