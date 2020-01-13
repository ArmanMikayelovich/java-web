package com.energizeglobal.internship.servlet;

import com.energizeglobal.internship.model.RegistrationRequest;
import com.energizeglobal.internship.util.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Set;

public class Register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/registration.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String username = req.getParameter("username");
        final String password = req.getParameter("password");
        LocalDate birthday;
        try {
            birthday = LocalDate.parse(req.getParameter("birthday"));
        } catch (DateTimeParseException ignored) {
            birthday = null;
        }
        final String email = req.getParameter("email");
        final String country = req.getParameter("country");
        final RegistrationRequest registrationRequest =
                new RegistrationRequest(username, password, birthday, email, country);
        final Set<ConstraintViolation<RegistrationRequest>> constraintViolations =
                Validator.validate(registrationRequest);
        if (!constraintViolations.isEmpty()) {
            for (ConstraintViolation<RegistrationRequest> violation : constraintViolations) {
                req.setAttribute(violation.getPropertyPath().toString(),violation.getMessage());
            }
            req.getRequestDispatcher("/registration.jsp").forward(req,resp);
            return;
        }
        //TODO do register
        resp.sendRedirect("/login.jsp");

    }
}
