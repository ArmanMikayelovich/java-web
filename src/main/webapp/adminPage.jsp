<%@ page import="com.energizeglobal.internship.model.User" %>
<%@ page import="com.energizeglobal.internship.dao.UserDao" %>
<%@ page import="com.energizeglobal.internship.dao.UserDaoJDBCImpl" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome Admin - <%=session.getAttribute("username")%></title>
</head>
<body>
<%! UserDao userDao = new UserDaoJDBCImpl();%>
<table>
    <tr>
        <th>Username</th>
        <th>Birthday</th>
        <th>Email</th>
        <th>Country</th>
        <th>Is admin</th>
    </tr>
    <% for(User user : userDao.findAll()) { %>
    <tr>
        <td><%=user.getUsername()%></td>
        <td><%=user.getBirthday().toString()%></td>
        <td><%=user.getEmail()%></td>
        <td><%=user.getCountry()%></td>
        <td><%= Boolean.valueOf(user.isAdmin()).toString() %></td>
        <td><form action="admin/state-changer" method="post">
            <input type="text" hidden="hidden" name="username" value="<%=user.getUsername()%>">
            <input type="text" hidden="hidden" name="isAdmin" value="<%=!user.isAdmin()%>">
            <input type="submit" name="Change admin state." value="Change admin state.">
        </form>
        </td>
    </tr>
    <% } %>

</table>
</body>
</html>
