package com.energizeglobal.internship.filter;

import com.energizeglobal.internship.dao.UserDao;
import com.energizeglobal.internship.dao.UserDaoJDBCImpl;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.UserDataHandler;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
@Slf4j
public class AdminFilter implements Filter {
    private final UserDao userDao =UserDaoJDBCImpl.getInstance();

    private static final int UNAUTHORIZED = 403;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Admin filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        final String username = (String) req.getSession().getAttribute("username");
        if (userDao.isAdmin(username)) {
            filterChain.doFilter(servletRequest,servletResponse);
        }
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        resp.setStatus(UNAUTHORIZED);
    }

    @Override
    public void destroy() {
        log.debug("Admin filter destroyed");
    }
}
