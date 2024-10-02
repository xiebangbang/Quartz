<%--
  Created by IntelliJ IDEA.
  User: bangb
  Date: 2024/8/11
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>include包含</title>
</head>
<body>

<%-- include静态包含 --%>
<%@ include file="03-header.jsp"%>

<h2>主体内容</h2>

<%
    String str = "msgStr";
%>

<%-- 动态包含 --%>
<jsp:include page="03-footer.jsp">
    <jsp:param name="username" value="admin"/>
    <jsp:param name="msg" value="<%=str%>"/>
</jsp:include>

</body>
</html>
