package com.energizeglobal.internship.servlet;

import com.energizeglobal.internship.dao.UserDao;
import com.energizeglobal.internship.dao.UserDaoJDBCImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminStateChanger extends HttpServlet {
    private final UserDao userDao = new UserDaoJDBCImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String username = req.getParameter("username");
        final String isAdminString = req.getParameter("isAdmin");
        final boolean isAdmin = Boolean.parseBoolean(isAdminString);
        userDao.changeAdminState(username, isAdmin);
        resp.sendRedirect("/adminPage.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/adminPage.jsp");
    }
}
