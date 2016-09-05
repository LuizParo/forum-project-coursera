package com.coursera.forum.model;

import java.io.Serializable;

public class Comentario implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String comentario;
    private Usuario usuario;
    private Topico topico;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Topico getTopico() {
        return topico;
    }

    public void setTopico(Topico topico) {
        this.topico = topico;
    }

    @Override
    public String toString() {
        return "Comentario [id=" + id + ", comentario=" + comentario + ", usuario=" + usuario + ", topico=" + topico
                + "]";
    }
}