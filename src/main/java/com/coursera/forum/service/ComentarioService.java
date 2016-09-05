package com.coursera.forum.service;

import java.io.Serializable;
import java.util.List;

import com.coursera.forum.dao.ComentarioDAO;
import com.coursera.forum.model.Comentario;

public class ComentarioService implements Serializable {
    private static final long serialVersionUID = 1L;
    private ComentarioDAO comentarioDAO;

    public ComentarioService(ComentarioDAO comentarioDAO) {
        this.comentarioDAO = comentarioDAO;
    }
    
    public void insereComentario(Comentario comentario) {
        this.comentarioDAO.inserir(comentario);
    }
    
    public List<Comentario> recuperaComentariosPorTopico(int idTopico) {
        return this.comentarioDAO.recuperaComentariosPorTopico(idTopico);
    }
}