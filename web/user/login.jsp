<%--
  Created by IntelliJ IDEA.
  User: YT
  Date: 2019/7/24
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<script type="text/javascript">
    function changeCode() {
        //得到图片元素
        var img=document.getElementsByTagName("img")[0];
        img.src="code?time="+new Date().getTime();
    }
</script>
<body>
<%
    String msg=(String)request.getAttribute("msg");
    if(msg!=null){
        out.print(msg);
    }
%>
<center>
    <form action="${pageContext.request.contextPath}/login" method="post">
        用户名：<input type="text" name="userName"/><br>
        密码：<input type="password" name="password"/><br>
        验证码：<input type="text" name="code"/>
        <img src="code" onclick="changeCode()"/><a href="javascript:changeCode()" >看不清换一张</a><br>
        <input type="submit" value="登录"/><br>
    </form>
</center>
</body>
</html>
