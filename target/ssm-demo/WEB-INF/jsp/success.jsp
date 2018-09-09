<%--
  Created by IntelliJ IDEA.
  User: smfx1314
  Date: 2018/9/4
  Time: 22:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:forEach items="${users}" var="user">
    ${user.id}
    ${user.username}
    ${user.password}
</c:forEach>
</body>
</html>
