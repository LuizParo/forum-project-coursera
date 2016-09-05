package com.coursera.forum.filter;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Connection;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;

import com.coursera.forum.dao.JDBCConnectionFactory;
import com.coursera.forum.dao.UsuarioDAO;
import com.coursera.forum.dao.UsuarioDAOImpl;
import com.coursera.forum.service.UsuarioService;

@WebFilter(urlPatterns = "/")
public class UsuarioFilter implements Filter, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = FilterHelper.getSessionFromRequest(request);
        
        if(session != null) {
            if(session.getAttribute("usuarioService") == null) {
                if(session.getAttribute("connection") == null) {
                    Connection connection = JDBCConnectionFactory.getConnection();
                    session.setAttribute("connection", connection);
                }
                
                UsuarioDAO dao = new UsuarioDAOImpl((Connection) session.getAttribute("connection"));
                UsuarioService usuarioService = new UsuarioService(dao);
                
                session.setAttribute("usuarioService", usuarioService);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        
    }
}