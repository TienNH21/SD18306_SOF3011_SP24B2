<%--
  Created by IntelliJ IDEA.
  User: tiennh
  Date: 3/9/24
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Mã</th>
        <th>Tên</th>
        <th>Trạng thái</th>
        <th colspan="2">Thao tác</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${ ds }" var="ms">
        AAAAA
        <tr>
            <td>1</td>
            <td>${ms.ma}</td>
            <td>${ms.ten}</td>
            <td>${ms.trangThai}</td>
            <td>1</td>
            <td>1</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
