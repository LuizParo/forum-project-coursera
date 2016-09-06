package com.coursera.forum.action;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coursera.forum.model.Comentario;
import com.coursera.forum.model.Topico;
import com.coursera.forum.model.Usuario;
import com.coursera.forum.service.ComentarioService;

public class InsereComentarioAction implements Action, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ComentarioService service = (ComentarioService) session.getAttribute("comentarioService");
        
        Topico topico = new Topico();
        topico.setId(Integer.parseInt(request.getParameter("topico")));
        
        Comentario comentario = new Comentario();
        comentario.setComentario(request.getParameter("txtComentario"));
        comentario.setUsuario((Usuario) session.getAttribute("usuarioLogado"));
        comentario.setTopico(topico);
        
        service.insereComentario(comentario);
        
        return "redirect:?action=TelaTopicoAction&topico=" + topico.getId() + "&exibicao=true";
    }
}