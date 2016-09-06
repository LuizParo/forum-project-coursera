package com.coursera.forum.dao;

import java.util.List;

import com.coursera.forum.model.Topico;
import com.coursera.forum.model.Usuario;

public interface TopicoDAO {

    public List<Topico> recuperaTopicosDoUsuario(Usuario usuario);

    public void inserir(Topico topico);

    public Topico recuperaTopico(int idTopico);
}