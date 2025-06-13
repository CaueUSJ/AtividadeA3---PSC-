package com.biblioteca.dao;

import com.biblioteca.model.Leitor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LeitorDAO {
    private String url = "jdbc:mysql://localhost:3306/biblioteca";
    private String usuario = "root";
    private String senha = "biblioteca2025";

    public Leitor buscarPorId(int id) {
        String sql = "SELECT * FROM leitor WHERE id_leitor = ?";
        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Leitor(
                    rs.getInt("id_leitor"),
                    rs.getString("nome"),
                    rs.getString("registro"),
                    rs.getString("telefone"),
                    rs.getString("email"),
                    rs.getString("endereco")
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public List<Leitor> buscarTodos() {
    List<Leitor> leitores = new ArrayList<>();
    String sql = "SELECT * FROM leitor ORDER BY nome";
    
    try (Connection conn = DriverManager.getConnection(url, usuario, senha);
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            Leitor leitor = new Leitor(
                rs.getInt("id_leitor"),
                rs.getString("nome"),
                rs.getString("registro"),
                rs.getString("telefone"),
                rs.getString("email"),
                rs.getString("endereco")
            );
            leitores.add(leitor);
        }
        
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    
    return leitores;
    }
    
}

