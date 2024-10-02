<%--
  Created by IntelliJ IDEA.
  User: bangb
  Date: 2024/8/11
  Time: 9:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Scriptlet</title>
</head>
<body>

<%-- Scriptlet --%>
<%
    String str = "Hello JSP!";
    System.out.println(str);
    response.getWriter().write(str);
%>

<%!
    String memberStr = "a member string";
%>

<%=memberStr%>

</body>
</html>
