package com.coursera.forum.dao;

import java.util.List;

import com.coursera.forum.model.Topico;

public interface TopicoDAO {

    public List<Topico> recuperaTopicos();

    public void inserir(Topico topico);

    public Topico recuperaTopico(int idTopico);
}