package com.coursera.forum.filter;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

class FilterHelper {

    public static HttpSession getSessionFromRequest(ServletRequest request) {
        if(!(request instanceof HttpServletRequest)) {
            return null;
        }
        return ((HttpServletRequest) request).getSession();
    }
}