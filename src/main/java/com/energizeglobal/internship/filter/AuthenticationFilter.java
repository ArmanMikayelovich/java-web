package com.energizeglobal.internship.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class AuthenticationFilter implements Filter {
    private static final int UNAUTHENTICATED = 401;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.debug(servletRequest + "|" + servletResponse);
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if (req.getSession(false) == null || req.getSession().getAttribute("username") == null) {
            resp.setStatus(UNAUTHENTICATED);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Authentication filter initialized.");
    }

    @Override
    public void destroy() {
        log.debug("Authentication filter destroyed.");
    }
}
