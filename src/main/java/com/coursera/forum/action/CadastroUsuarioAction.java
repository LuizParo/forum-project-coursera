package com.coursera.forum.action;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coursera.forum.model.Usuario;
import com.coursera.forum.service.UsuarioService;

public class CadastroUsuarioAction implements Action, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Usuario usuario = new Usuario();
        usuario.setLogin(request.getParameter("txtLogin"));
        usuario.setSenha(request.getParameter("txtSenha"));
        usuario.setNome(request.getParameter("txtNome"));
        usuario.setEmail(request.getParameter("txtEmail"));
        usuario.setPontos(0);
        
        UsuarioService usuarioService = (UsuarioService) request.getSession().getAttribute("usuarioService");
        usuarioService.cadastraUsuario(usuario);
        
        request.setAttribute("mensagem", "Usuário cadastrado com sucesso!");
        return "login";
    }
}