package com.coursera.forum.dao;

import java.util.List;

import com.coursera.forum.model.Comentario;

public interface ComentarioDAO {

    public void inserir(Comentario comentario);
    
    public List<Comentario> recuperaComentariosPorTopico(int idTopico);
}