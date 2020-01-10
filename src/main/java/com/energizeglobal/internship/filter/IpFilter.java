package com.energizeglobal.internship.filter;

import javax.servlet.*;
import java.io.IOException;
import java.net.InetAddress;

public class IpFilter implements Filter {
    private static final String LOCAL_IPV4 = "127.0.0.1";
    private static final String LOCAL_IPV6 = "0:0:0:0:0:0:0:1";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String remoteAddr = servletRequest.getRemoteHost();
        if (!LOCAL_IPV4.equals(remoteAddr) && !LOCAL_IPV6.equals(remoteAddr)) {
            InetAddress byName = InetAddress.getByName(remoteAddr);
            String hostAddress = byName.getHostAddress();
            servletResponse.getWriter().println("not from local address");
            servletResponse.getWriter().println(hostAddress);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
