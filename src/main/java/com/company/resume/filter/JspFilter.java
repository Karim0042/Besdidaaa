package com.company.resume.filter;

import com.company.dao.inter.UserDaoInter;
import com.company.main.Context;
import jakarta.servlet.*;

import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(filterName = "JspFilter" ,urlPatterns ="*.jsp")

public class JspFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletResponse res =(HttpServletResponse)response;
            res.sendRedirect("error?msg=not found");
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
