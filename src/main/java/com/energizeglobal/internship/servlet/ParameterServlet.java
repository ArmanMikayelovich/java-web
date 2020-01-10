package com.energizeglobal.internship.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class ParameterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        writer.println("{ \n ");
        writer.println("\"name\":\""+name+"\"\n");

        writer.println("\"dateTime\" :\"" + LocalDateTime.now().toString() + "\"\n}");
    }
}
