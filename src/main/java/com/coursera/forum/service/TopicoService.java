package com.coursera.forum.service;

import java.io.Serializable;
import java.util.List;

import com.coursera.forum.dao.TopicoDAO;
import com.coursera.forum.model.Topico;
import com.coursera.forum.model.Usuario;

public class TopicoService implements Serializable {
    private static final long serialVersionUID = 1L;
    private TopicoDAO topicoDAO;

    public TopicoService(TopicoDAO topicoDAO) {
        this.topicoDAO = topicoDAO;
    }

    public List<Topico> recuperaTopicosDoUsuario(Usuario usuario) {
        return this.topicoDAO.recuperaTopicosDoUsuario(usuario);
    }
    
    public void insereTopico(Topico topico) {
        this.topicoDAO.inserir(topico);
    }
}