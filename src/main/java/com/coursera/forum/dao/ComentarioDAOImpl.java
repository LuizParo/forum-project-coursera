package com.coursera.forum.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coursera.forum.exception.DataAccessException;
import com.coursera.forum.model.Comentario;
import com.coursera.forum.model.Topico;
import com.coursera.forum.model.Usuario;

public class ComentarioDAOImpl implements ComentarioDAO, Serializable {
    private static final long serialVersionUID = 1L;
    private Connection connection;

    public ComentarioDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void inserir(Comentario comentario) {
        String sql = "INSERT INTO comentario(comentario, login, id_topico) VALUES(?, ?, ?)";
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, comentario.getComentario());
            stmt.setString(2, comentario.getUsuario().getLogin());
            stmt.setInt(3, comentario.getTopico().getId());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Comentario> recuperaComentariosPorTopico(int idTopico) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM comentario c ");
        sql.append("INNER JOIN usuario u ON c.login = u.login ");
        sql.append("INNER JOIN topico t ON t.id_topico = c.id_topico ");
        sql.append("WHERE c.id_topico = ?");
        
        List<Comentario> comentarios = new ArrayList<>();
        
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql.toString());
            stmt.setInt(1, idTopico);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Usuario usuarioRecuperado = new Usuario();
                usuarioRecuperado.setLogin(rs.getString("login"));
                usuarioRecuperado.setEmail(rs.getString("email"));
                usuarioRecuperado.setNome(rs.getString("nome"));
                usuarioRecuperado.setSenha(rs.getString("senha"));
                usuarioRecuperado.setPontos(rs.getInt("pontos"));
                
                Topico topicoRecuperado = new Topico();
                topicoRecuperado.setId(rs.getInt("id_topico"));
                topicoRecuperado.setTitulo(rs.getString("titulo"));
                topicoRecuperado.setConteudo(rs.getString("conteudo"));
                topicoRecuperado.setUsuario(usuarioRecuperado);
                
                Comentario comentario = new Comentario();
                comentario.setComentario(rs.getString("comentario"));
                comentario.setUsuario(usuarioRecuperado);
                comentario.setTopico(topicoRecuperado);
                
                comentarios.add(comentario);
            }
            
            return comentarios;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}