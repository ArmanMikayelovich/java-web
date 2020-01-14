<%@ page import="com.energizeglobal.internship.model.User" %>
<%@ page import="com.energizeglobal.internship.dao.UserDao" %>
<%@ page import="com.energizeglobal.internship.dao.UserDaoJDBCImpl" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome <%= session.getAttribute("username")%></title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

</head>
<body>
<%! UserDao userDao = new UserDaoJDBCImpl();%>
<% User user  = userDao.findByUsername((String)session.getAttribute("username"));%>
<table style="width:100%">
    <tr>
        <th>Username</th>
        <th>Birthday</th>
        <th>Email</th>
        <th>Country</th>
        <th>Is admin</th>
    </tr>
    <tr>
        <td><%=user.getUsername()%></td>
        <td><%=user.getBirthday().toString()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCountry()%></td>
        <td><%= Boolean.valueOf(user.isAdmin()).toString() %></td>
    </tr>

</table>

</body>
</html>
