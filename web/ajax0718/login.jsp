<%--
  Created by IntelliJ IDEA.
  User: YT
  Date: 2019/7/18
  Time: 20:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <style type="text/css">
        input{
            width:300px;
            height:30px;
        }
        .bigContainer tr{
            height:35px;
        }
        #btnLogin{
            width:300px;
            height: 30px;
        }
    </style>
</head>
<body>
<div class="bigContainer">
    <div id="login-page">
        <center>
            <h1>欢迎登陆</h1>
            <form id="login" method="post">
                <table>
                    <tr>
                        <td>用户名：</td>
                        <td><input id="userName" type="text" name="userName" size="16"/></td>
                    </tr>
                    <tr>
                        <td>密码：</td>
                        <td><input id="password" type="password" name="password" size="16"/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <button id="btnLogin" type="button">登录</button>
                            <a href="#" style="color: #3d444c">忘记密码？</a>
                        </td>
                    </tr>
                </table>
            </form>
        </center>
    </div>
</div>
<script src="../js/jquery-3.4.1.js"></script>
<script language="JavaScript">
    //监听登录按钮，执行登录
    $('#btnLogin').click(function () {
        check();
    });
    //监听回车，执行登录按钮
    $("body").keydown(function() {
        if (event.keyCode == "13") {// keyCode=13是回车键
            $('#btnLogin').click();
        }
    });
    //执行登录检查
    function check() {
        var userName=$("input[name='userName']").val(); //获取input里的值
        var password=$("input[name='password']").val();
        $.ajax({
            type:"POST",
            url:"/ServletLogin",
            data:$('#login').serialize(),  //直接传表单里的数据
            // data:{
            //     userName:userName,
            //     password:password
            // },
            success:function (result) {
                if("Yes"==result){
                    alert("登录成功！");
                }else{
                    alert("用户名或密码错误");
                    $("#password").val("");  //将密码input清空
                    $("#password").focus();  //将光标定位到密码input
                }
            },
            error:function (err) {
                alert("系统错误-loginPage.jsp-ajax");
            }
        });
    };
</script>
</body>
</html>
