package com.coursera.forum.action;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coursera.forum.model.Comentario;
import com.coursera.forum.service.ComentarioService;
import com.coursera.forum.service.TopicoService;

public class TelaInsereTopicoAction implements Action, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String txtIdTopico = request.getParameter("topico");
        
        if(txtIdTopico != null) {
            HttpSession session = request.getSession();
            ComentarioService comentarioService = (ComentarioService) session.getAttribute("comentarioService");
            TopicoService topicoService = (TopicoService) session.getAttribute("topicoService");
            
            int idTopico = Integer.parseInt(txtIdTopico);
            List<Comentario> comentariosRecuperados = comentarioService.recuperaComentariosPorTopico(idTopico);
            request.setAttribute("topico", topicoService.recuperaTopico(idTopico));
            
            if(!comentariosRecuperados.isEmpty()) {
                request.setAttribute("comentarios", comentariosRecuperados);
            }
            
            String exibicao = request.getParameter("exibicao");
            if(exibicao != null && exibicao.equals("true")) {
                request.setAttribute("exibicao", true);
            }
        }
        
        return "insereTopico";
    }
}