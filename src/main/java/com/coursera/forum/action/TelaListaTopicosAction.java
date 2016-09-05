package com.coursera.forum.action;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coursera.forum.model.Topico;
import com.coursera.forum.model.Usuario;
import com.coursera.forum.service.TopicoService;

public class TelaListaTopicosAction implements Action, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        TopicoService service = (TopicoService) session.getAttribute("topicoService");
        
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        List<Topico> topicos = service.recuperaTopicosDoUsuario(usuarioLogado);
        request.setAttribute("topicos", topicos);
        
        return "listaTopicos";
    }
}