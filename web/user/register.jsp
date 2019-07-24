<%--
  Created by IntelliJ IDEA.
  User: YT
  Date: 2019/7/24
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    table{text-align: center;}
</style>
<body>
<center>
<h1>用户注册页面</h1>
<hr>
    <br>
<form action="${pageContext.request.contextPath}/register" method="post">
    <table border="1">
        <tr>
            <td>用户名:</td>
            <td><input type="text" name="userName"></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>邮箱:</td>
            <td><input type="email" name="email"></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" value="注册">&nbsp;&nbsp;<input type="reset" value="重置"></td>
        </tr>
    </table>
</form>
</center>
</body>
</html>
