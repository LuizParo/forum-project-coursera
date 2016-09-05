package com.coursera.forum.action;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coursera.forum.model.Topico;
import com.coursera.forum.model.Usuario;
import com.coursera.forum.service.TopicoService;

public class InsereTopicoAction implements Action, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        TopicoService topicoService = (TopicoService) session.getAttribute("topicoService");
        
        Topico topico = new Topico();
        topico.setTitulo(request.getParameter("txtTitulo"));
        topico.setConteudo(request.getParameter("txtConteudo"));
        topico.setUsuario((Usuario) session.getAttribute("usuarioLogado"));
        
        topicoService.insereTopico(topico);
        return "redirect:?action=TelaListaTopicosAction";
    }
}