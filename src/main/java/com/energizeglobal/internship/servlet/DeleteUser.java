package com.energizeglobal.internship.servlet;

import com.energizeglobal.internship.dao.UserDao;
import com.energizeglobal.internship.dao.UserDaoJDBCImpl;
import com.energizeglobal.internship.util.exception.IllegalAccessException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUser extends HttpServlet {
    private final UserDao userDao = UserDaoJDBCImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        final String loggedInUsername = (String) req.getSession().getAttribute("username");
        final String deletingUsername = req.getParameter("username");
        if (loggedInUsername.equalsIgnoreCase(deletingUsername)) {
            userDao.remove(deletingUsername);
            req.getSession().invalidate();
            resp.sendRedirect("/");
        } else if (userDao.isAdmin(loggedInUsername)) {
            userDao.remove(deletingUsername);
            resp.sendRedirect("/");
        } else {
            throw new IllegalAccessException();
        }
        throw new IllegalAccessException();
    }
}
