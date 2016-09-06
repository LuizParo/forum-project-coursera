package com.coursera.forum.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coursera.forum.action.Action;

@WebServlet(urlPatterns = "/")
public final class FrontController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Package actionBasePackage = Action.class.getPackage();
        String requestAction = req.getParameter("action");
        
        if(requestAction == null) {
            req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
            return;
        }
        
        try {
            Class<?> clazz = Class.forName(actionBasePackage.getName() + "." + requestAction);
            Action action = (Action) clazz.newInstance();
            
            String outcome = action.execute(req, resp);
            if(this.isRedirect(outcome)) {
                String normalizedOutcome = this.normalizeOutcome(outcome);
                resp.sendRedirect(normalizedOutcome);
                return;
            }
            
            req.getRequestDispatcher("/WEB-INF/views/" + outcome + ".jsp").forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Erro na aplicação: " + e.getMessage(), e);
        }
    }
    
    private boolean isRedirect(String outcome) {
        return outcome.contains("redirect:");
    }
    
    private String normalizeOutcome(String outcome) {
        return outcome.replace("redirect:", "");
    }
}