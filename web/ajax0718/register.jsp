<%--
  Created by IntelliJ IDEA.
  User: YT
  Date: 2019/7/18
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
    <!--
       1、导入Jquery库
       2、获取name="username"的节点
       3、为username添加change响应函数
       3.1 获取username的value属性值，去除前后空格且不为空，准备发送ajax请求
       3.2 发送ajax请求检验username是否可用
       3.3 在服务器端直接返回一个Html的片段 <font color="red">该用户名已经被使用</font>
       3.4 在客户端浏览器把其直接添加到#message 的Html中
        -->
    <style type="text/css">
        #message{
            display: inline;
        }
    </style>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
    <script type="text/javascript">
        $(function(){
            //通过jquery选择器定位username
            $(":input[name='username']").change(function(){
                //获取Username的value值
                var val=$(this).val();//value有空格
                val=$.trim(val);//去掉空格
                //使用绝对路径
                var url="${pageContext.request.contextPath}/check";
                //去掉缓存的影响
                var args={"name":val,"time":new Date()};
                //ajax
                $.post(url,args,function(data){
                    // alert(data);
                    //使用空白div显示
                    $("#message").html(data);
                });
            });
        });//window.onload事件
    </script>
</head>
<body>
<h1>用户注册</h1>
<hr>
用户名：<input id="username" type="text" name="username" placeholder="输入用户名">
<span id="message"> &nbsp;</span><br>


</body>
</html>
