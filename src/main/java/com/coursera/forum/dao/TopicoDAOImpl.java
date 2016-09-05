package com.coursera.forum.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.coursera.forum.exception.DataAccessException;
import com.coursera.forum.model.Topico;
import com.coursera.forum.model.Usuario;

public class TopicoDAOImpl implements TopicoDAO, Serializable {
    private static final long serialVersionUID = 1L;
    private Connection connection;

    public TopicoDAOImpl(Connection connection) {
        // Recebe objeto Connection via Injeção de Dependêcia.
        this.connection = connection;
    }
    
    @Override
    public void inserir(Topico topico) {
        String sql = "INSERT INTO topico(titulo, conteudo, login) VALUES(?, ?, ?)";
        
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, topico.getTitulo());
            stmt.setString(2, topico.getConteudo());
            stmt.setString(3, topico.getUsuario().getLogin());
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }

    @Override
    public List<Topico> recuperaTopicosDoUsuario(Usuario usuario) {
        String sql = "SELECT * FROM topico t INNER JOIN usuario u ON t.login = u.login WHERE u.login = ?";
        List<Topico> topicos = new ArrayList<>();
        
        try {
            PreparedStatement stmt = this.connection.prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Usuario usuarioRecuperado = new Usuario();
                usuarioRecuperado.setLogin(rs.getString("login"));
                usuarioRecuperado.setEmail(rs.getString("email"));
                usuarioRecuperado.setNome(rs.getString("nome"));
                usuarioRecuperado.setSenha(rs.getString("senha"));
                usuarioRecuperado.setPontos(rs.getInt("pontos"));
                
                Topico topico = new Topico();
                topico.setId(rs.getInt("id_topico"));
                topico.setTitulo(rs.getString("titulo"));
                topico.setConteudo(rs.getString("conteudo"));
                topico.setUsuario(usuarioRecuperado);
                
                topicos.add(topico);
            }
            
            return topicos;
        } catch (SQLException e) {
            throw new DataAccessException(e.getMessage(), e);
        }
    }
}