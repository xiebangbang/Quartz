<%--
  Created by IntelliJ IDEA.
  User: bangb
  Date: 2024/8/11
  Time: 10:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>底部内容</title>
</head>
<body>

<h2>底部内容</h2>

<%
   String username = request.getParameter("username");
   String msg = request.getParameter("msg");

   out.print(username + ";" + msg);
%>

</body>
</html>
