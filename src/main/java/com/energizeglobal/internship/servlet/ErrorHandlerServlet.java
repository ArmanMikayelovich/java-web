package com.energizeglobal.internship.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ErrorHandlerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Throwable exception = (Throwable) req.getAttribute("javax.servlet.error.exception");
//        resp.getWriter().println(exception.toString());
//        resp.getWriter().flush();
        //TODO get your exceptions and send message with http error code
        //TODO with invalidCredentialsException forward to login.jsp with error message
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}
