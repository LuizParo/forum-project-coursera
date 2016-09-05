package com.coursera.forum.action;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coursera.forum.model.Usuario;
import com.coursera.forum.service.UsuarioService;

public class AutenticacaoAction implements Serializable, Action {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        UsuarioService service = (UsuarioService) session.getAttribute("usuarioService");
        
        String txtUsuario = request.getParameter("txtUsuario");
        String txtPassword = request.getParameter("txtPassword");
        
        Usuario usuarioAutenticado = service.autenticaUsuario(txtUsuario, txtPassword);
        if(usuarioAutenticado.getLogin() == null) {
            request.setAttribute("mensagem", "Login inválido! Verifique credenciais!");
            return "login";
        }
        
        session.setAttribute("usuarioLogado", usuarioAutenticado);
        return "redirect:?action=TelaListaTopicosAction";
    }
}