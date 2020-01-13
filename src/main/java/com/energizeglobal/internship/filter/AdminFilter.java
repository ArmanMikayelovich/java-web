package com.energizeglobal.internship.filter;

import javax.servlet.*;
import java.io.IOException;
import java.net.InetAddress;

public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
       //TODO get username from request, check isAdmin from Database, and go ahead
    }
}
