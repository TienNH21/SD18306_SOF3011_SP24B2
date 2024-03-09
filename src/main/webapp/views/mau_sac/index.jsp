<%--
  Created by IntelliJ IDEA.
  User: tiennh
  Date: 3/9/24
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <tr>
            <td>1</td>
            <td>${ ms.ma }</td>
            <td>${ ms.ten }</td>
            <td>${ ms.trangThai }</td>
            <td>
                <a href="#">Update</a>
            </td>
            <td>
                <a href="#">Delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
