package com.coursera.forum.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coursera.forum.exception.DataAccessException;
import com.coursera.forum.model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO, Serializable {
    private static final long serialVersionUID = 1L;
    private Connection connection;

    public UsuarioDAOImpl(Connection connection) {
        // Recebe objeto Connection via Injeção de Dependêcia.
        this.connection = connection;
    }

    @Override
    public void inserir(Usuario u) {
        String sql = "INSERT INTO usuario(login, email, nome, senha, pontos) VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, u.getLogin());
            stmt.setString(2, u.getEmail());
            stmt.setString(3, u.getNome());
            stmt.setString(4, u.getSenha());
            stmt.setInt(5, u.getPontos());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public Usuario recuperar(String login, String senha) {
        String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
        Usuario usuario = new Usuario();
        
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, login);
            stmt.setString(2, senha);
            
            ResultSet rs = stmt.executeQuery();
            if(rs.next()) {
                usuario.setLogin(rs.getString("login"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPontos(rs.getInt("pontos"));
            }
            return usuario;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public void adicionarPontos(String login, int pontos) {
        String sql = "UPDATE usuario SET pontos = pontos + ? WHERE login = ?";
        
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setInt(1, pontos);
            stmt.setString(2, login);
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Usuario> ranking() {
        String sql = "SELECT * FROM usuario ORDER BY pontos DESC";
        List<Usuario> usuariosRecuperados = new ArrayList<>();
        
        try {
            ResultSet rs = this.connection.prepareStatement(sql).executeQuery();
            
            while(rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setLogin(rs.getString("login"));
                usuario.setEmail(rs.getString("email"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPontos(rs.getInt("pontos"));
                
                usuariosRecuperados.add(usuario);
            }
            
            return usuariosRecuperados;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}