package com.energizeglobal.internship.servlet;

import com.energizeglobal.internship.util.exception.InvalidCredentialsException;
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
        System.out.println("s");
        //TODO get your exceptions and send message with http error code
        //TODO with invalidCredentialsException forward to login.jsp with error message
        final Class exceptionClass = (Class) req.getAttribute("javax.servlet.error.exception_type");
        if (exceptionClass != null) {

            if (exceptionClass.equals(InvalidCredentialsException.class)) {
                log.debug("Handled InvalidCredentialsException and redirected to /login.jsp?error=true");
                resp.sendRedirect("/login.jsp?error=true");
                log.debug(exceptionClass.toString() + " handled");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
