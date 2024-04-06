<%--
  Created by IntelliJ IDEA.
  User: tiennh
  Date: 3/9/24
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:if test="${ not empty sessionScope.error }">
        <span style="color: red">${ sessionScope.error }</span>
    </c:if>
    <form method="POST" action="/SD19306_SOF3011_war/mau-sac/update?id=${ms.id}">
        <div>
            <label>Mã</label>
            <input type="text" name="ma" value="${ ms.ma }" />
        </div>
        <div>
            <label>Tên</label>
            <input type="text" name="ten" value="${ ms.ten }" />
        </div>
        <div>
            <label>Trạng thái</label>
            <input type="radio" name="trangThai" value="1"
                ${ ms.trangThai == 1 ? "checked" : "" }/>
            <label>Đang hoạt động</label>
            <input type="radio" name="trangThai" value="0"
                ${ ms.trangThai == 0 ? "checked" : "" }/>
            <label>Ngừng hoạt động</label>
        </div>
        <div>
            <button>Cập nhật</button>
        </div>

    </form>
</body>
</html>
