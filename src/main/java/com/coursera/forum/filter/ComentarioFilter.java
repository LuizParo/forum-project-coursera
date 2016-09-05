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
import javax.servlet.http.HttpSession;

import com.coursera.forum.dao.ComentarioDAO;
import com.coursera.forum.dao.ComentarioDAOImpl;
import com.coursera.forum.dao.JDBCConnectionFactory;
import com.coursera.forum.service.ComentarioService;

public class ComentarioFilter implements Filter, Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = FilterHelper.getSessionFromRequest(request);
        if(session != null) {
            if(session.getAttribute("comentarioService") == null) {
                if(session.getAttribute("connection") == null) {
                    Connection connection = JDBCConnectionFactory.getConnection();
                    session.setAttribute("connection", connection);
                }
                
                ComentarioDAO dao = new ComentarioDAOImpl((Connection) session.getAttribute("connection"));
                ComentarioService service = new ComentarioService(dao);
                
                session.setAttribute("comentarioService", service);
            }
        }
    }

    @Override
    public void destroy() {
        
    }
}