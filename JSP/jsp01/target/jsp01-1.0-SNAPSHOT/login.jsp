<%--
  Created by IntelliJ IDEA.
  User: bangb
  Date: 2024/8/11
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
</head>
<body>
<form action="loginservlet" method="post" style="text-align: center;">
    姓名：<input type="text" name="username" value="${user.name}"/> </br>
    密码：<input type="password" name="password" value="${user.password}" /> </br>
    <button type="submit">登录</button> <span style="color: red">${msg}</span>
</form>
</body>
</html>
