package com.coursera.forum.service;

import java.io.Serializable;
import java.util.List;

import com.coursera.forum.dao.TopicoDAO;
import com.coursera.forum.model.Topico;

public class TopicoService implements Serializable {
    private static final long serialVersionUID = 1L;
    private TopicoDAO topicoDAO;

    public TopicoService(TopicoDAO topicoDAO) {
        this.topicoDAO = topicoDAO;
    }
    
    public Topico recuperaTopico(int idTopico) {
        return this.topicoDAO.recuperaTopico(idTopico);
    }

    public List<Topico> recuperaTopicos() {
        return this.topicoDAO.recuperaTopicos();
    }
    
    public void insereTopico(Topico topico) {
        this.topicoDAO.inserir(topico);
    }
}