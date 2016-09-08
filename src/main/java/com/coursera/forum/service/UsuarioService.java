package com.coursera.forum.service;

import java.io.Serializable;
import java.util.List;

import com.coursera.forum.dao.UsuarioDAO;
import com.coursera.forum.model.Usuario;

public class UsuarioService implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private UsuarioDAO usuarioDAO;
    
    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public void cadastraUsuario(Usuario usuario) {
        this.usuarioDAO.inserir(usuario);
    }
    
    public Usuario autenticaUsuario(String login, String senha) {
        return this.usuarioDAO.recuperar(login, senha);
    }
    
    public void pontuaUsuario(Usuario usuario, int pontos) {
        this.usuarioDAO.adicionarPontos(usuario.getLogin(), pontos);
    }

    public List<Usuario> ranking() {
        return this.usuarioDAO.ranking();
    }
}