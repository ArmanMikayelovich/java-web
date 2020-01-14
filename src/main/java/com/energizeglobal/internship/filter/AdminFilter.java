package com.energizeglobal.internship.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;
import java.net.InetAddress;
@Slf4j
public class AdminFilter implements Filter {
    private static final int UNAUTHORIZED = 403;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Admin filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
       //TODO get username from request, check isAdmin from Database, and go ahead
    }

    @Override
    public void destroy() {
        log.debug("Admin filter destroyed");
    }
}
