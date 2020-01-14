package com.energizeglobal.internship.servlet;

import com.energizeglobal.internship.dao.UserDao;
import com.energizeglobal.internship.dao.UserDaoJDBCImpl;
import com.energizeglobal.internship.model.User;
import com.energizeglobal.internship.util.exception.IllegalAccessException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

public class UserInfoChanger extends HttpServlet {
    private final UserDao userDao = new UserDaoJDBCImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/user/userPage.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String username = req.getParameter("username");

        if (!userDao.isAdmin(username)) {
            final String loggedInUsername =(String) req.getSession().getAttribute("username");
            if (!username.equals(loggedInUsername)) {
                throw new IllegalAccessException();
            }
        }

        final LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
        final String email = req.getParameter("email");
        final String country = req.getParameter("country");
        userDao.updateUserInfo(new User(username, birthday, email, country));

        if (userDao.isAdmin(username)) {
            resp.sendRedirect("/admin/adminPage.jsp");
        } else {
            resp.sendRedirect("/user/userPage.jsp");
        }
    }
}
