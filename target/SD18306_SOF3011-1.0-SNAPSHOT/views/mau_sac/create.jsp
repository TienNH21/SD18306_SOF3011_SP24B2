<%--
  Created by IntelliJ IDEA.
  User: tiennh
  Date: 3/9/24
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="POST" action="/SD19306_SOF3011_war/mau-sac/store">
        <div>
            <label>Mã</label>
            <input type="text" name="ma" />
        </div>
        <div>
            <label>Tên</label>
            <input type="text" name="ten" />
        </div>
        <div>
            <label>Trạng thái</label>
            <input type="radio" name="trangThai" value="1" checked />
            <label>Đang hoạt động</label>
            <input type="radio" name="trangThai" value="0" />
            <label>Ngừng hoạt động</label>
        </div>
        <div>
            <button>Thêm</button>
        </div>

    </form>
</body>
</html>
