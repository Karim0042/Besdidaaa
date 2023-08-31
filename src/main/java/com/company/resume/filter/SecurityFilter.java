package com.company.resume.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "SecurityFilter", urlPatterns ="*")

public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletResponse res = (HttpServletResponse) response;
            HttpServletRequest req = (HttpServletRequest)request;
            if(!req.getRequestURI().contains("/login") && req.getSession().getAttribute("loggedInUser") == null && !req.getRequestURI().contains("/error")){
                res.sendRedirect("login");
            }else {
                chain.doFilter(request,response);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}