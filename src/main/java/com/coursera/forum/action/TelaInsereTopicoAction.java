package com.coursera.forum.action;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coursera.forum.model.Comentario;
import com.coursera.forum.service.ComentarioService;

public class TelaInsereTopicoAction implements Action, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String txtIdTopico = request.getParameter("txtIdTopico");
        
        if(txtIdTopico != null) {
            HttpSession session = request.getSession();
            ComentarioService service = (ComentarioService) session.getAttribute("comentarioService");
            List<Comentario> comentariosRecuperados = service.recuperaComentariosPorTopico(Integer.parseInt(txtIdTopico));
            
            if(!comentariosRecuperados.isEmpty()) {
                request.setAttribute("comentarios", comentariosRecuperados);
            }
        }
        
        return "insereTopico";
    }
}