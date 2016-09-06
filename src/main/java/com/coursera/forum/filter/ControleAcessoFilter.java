package com.coursera.forum.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = "/")
public class ControleAcessoFilter implements Filter, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = FilterHelper.getSessionFromRequest(request);
        if(session != null) {
            String action = request.getParameter("action");
            if(session.getAttribute("usuarioLogado") == null && !this.isActionAllowedToContinue(action)) {
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                return;
            }
        }
        
        chain.doFilter(request, response);
    }

    private boolean isActionAllowedToContinue(String action) {
        return action == null
                || action.equalsIgnoreCase("TelaCadastroUsuarioAction")
                || action.equalsIgnoreCase("CadastroUsuarioAction")
                || action.equalsIgnoreCase("AutenticacaoAction");
    }

    @Override
    public void destroy() {
        
    }
}