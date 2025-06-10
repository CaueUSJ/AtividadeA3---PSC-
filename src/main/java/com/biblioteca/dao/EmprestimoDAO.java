
package com.biblioteca.dao;

import com.biblioteca.model.Emprestimo;
import java.sql.*;
import java.util.*;

public class EmprestimoDAO {
    
    private String url = "jdbc:mysql://localhost:3306/biblioteca";
    private String usuario = "root";
    private String senha = "biblioteca2025";
    
    public boolean adicionarEmprestimo(Emprestimo e) {
        String sql = "INSERT INTO emprestimo (id_leitor, id_livro, data_emprestimo, data_devolucao, devolvido) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, e.getId_leitor());
            stmt.setInt(2, e.getId_livro());
            stmt.setDate(3, new java.sql.Date(e.getDataEmprestimo().getTime()));
            stmt.setDate(4, new java.sql.Date(e.getDataDevolucao().getTime()));
            stmt.setBoolean(5, e.isDevolvido());

            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public List<Emprestimo> listarTodos() {
        List<Emprestimo> lista = new ArrayList<>();
        String sql = "SELECT * FROM emprestimo";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Emprestimo e = new Emprestimo(
                        rs.getInt("id_emprestimo"),
                        rs.getInt("id_leitor"),
                        rs.getInt("id_livro"),
                        rs.getDate("data_emprestimo"),
                        rs.getDate("data_devolucao"),
                        rs.getBoolean("devolvido")
                );
                lista.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return lista;
    }
    
    public boolean marcarComoDevolvido(int idEmprestimo) {
        String sql = "UPDATE emprestimo SET devolvido = TRUE WHERE id_emprestimo = ?";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEmprestimo);
            return stmt.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public List<Integer> livrosEmprestados() {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT id_livro FROM emprestimo WHERE devolvido = false";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ids.add(rs.getInt("id_livro"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return ids;
    }
    
}
