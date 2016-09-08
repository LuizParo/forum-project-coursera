package com.coursera.forum.action;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coursera.forum.model.Usuario;
import com.coursera.forum.service.UsuarioService;

public class TelaRankingAction implements Action, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UsuarioService usuarioService = (UsuarioService) session.getAttribute("usuarioService");
        
        List<Usuario> rankingUsuarios = usuarioService.ranking();
        request.setAttribute("usuarios", rankingUsuarios);
        
        return "ranking";
    }
}