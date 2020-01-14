<%--
  Created by IntelliJ IDEA.
  User: armanar
  Date: 1/14/2020
  Time: 3:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change info</title>
</head>
<body>
<h2>Change user info</h2>
<form action="${pageContext.request.contextPath}/user/change-user-info" method="post">
    <input type="text" hidden="hidden" name="username" value="<%=request.getParameter("username")%>">
    <label>
        Birthday
        <input type="date" name="birthday">
    </label>
    <label>
        Email
        <input type="email" name="email">
    </label>
    <label>
        country
        <input type="text" name="country">
    </label>
    <input type="submit" value="Send."/>
</form>
</body>
</html>
