<%--
  Created by IntelliJ IDEA.
  User: YT
  Date: 2019/7/18
  Time: 20:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="js/jquery-3.2.1.js"></script>
    <script type="text/javascript">
        $(function(){
            load();//加载数据
            $("#aid").click(function(){
                $("#fill").show();
                $("#myform").show();
            });

        });//$(document).ready(function(){});
        function load(){
            var url="query";
            var args={"time":new Date()};
            $.get(url,args,function(data){
                var json=eval(data);//函数可计算某个字符串，并执行其中的的 JavaScript 代码。
                var arr=json.stu;//通过Key stu，拿到最外面的jsonobject
                //alert(arr);
                var str="";
                //遍历数组
                $.each(arr,function(index,obj){
                    //alert(index+","+obj.name);
                    str+= "<tr>" + "<td>" + obj.no + "</td>" + "<td>" + obj.name
                        + "</td>" + "<td>" + obj.age + "</td>" + "<td>"
                        + obj.sex + "</td>" + "<td>" + obj.classNo + "</td>"
                        + "<td><a href=\"javascript:void(0);\" onclick='doDel("
                        + obj.no + ",this)'>删除</a></td>"+"<td><input type='button' value='编辑' onclick='doEdit("
                        + obj.no + ",this)'></td>"
                        + "</tr>";
                });
                //$("#tbody").html(str);
                $("#tbody").empty();
                $("#tbody").append(str);
            },"json");//当服务器返回的json格式，一定要指定json
        }
        function doDel(sno,cell){
            if(confirm("确定删除吗?")){
                // alert("执行了删除");
                //请求服务器
                var url="query?oper=del";
                var args={"sno":sno};
                //传递学号给服务器，服务器通过学号去数据库删除数据
                $.post(url,args,function(data){
                    var json=eval(data);
                    var res=json.res;
                    //说明服务器返回的影响函数为大于1
                    if(res>0){
                        //alert("影响函数大于1");
                        //单元格所对应的行要删除
                        $(cell).parent().parent().remove();
                    }
                },"json");
            }

        }
        //增加方法
        function doAdd(form){
            $.ajax({
                url : "query?oper=add",
                type : "POST",
                data : $(form).serialize(),
                dataType : "json",
                success : function(data) {
                    var json=eval(data);
                    if (json.res > 0) {
                        $('#fill').hide();
                        $('#myform').hide();
                        $(form).find(":reset").trigger("click");
                        load();//重新加载

                    } else {
                        alert('添加失败！');
                    }
                }
            });
            return false;
        }
    </script>
</head>
<body>
<h2>jQuery实例--ajax信息的增删改查等操作</h2>
<h4>学生信息</h4>
<input type="button" value="添加" id="aid">
<br>
<table border="1">
    <thead>
    <th>学号</th>
    <th>姓名</th>
    <th>年龄</th>
    <th>性别</th>
    <th>班级号</th>
    <th>操作</th>
    </thead>
    <tbody id="tbody">

    </tbody>
</table>
<div id="fill"
     style="width: 100%; height: 100%; background-color: #000; position: absolute; top: 0px; left: 0px; opacity: 0.3; display: none; z-index: 100">
</div>

<div id="myform"
     style="width: 100%; height: 100%; position: absolute; top: 0px; left: 0px; z-index: 101; display: none">
    <form action="" method="post" onsubmit="return doAdd(this)">
        <table width="340" border="0"
               style="margin: 20% auto; background-color: #fff">
            <tr>
                <td>姓名：</td>
                <td><input type="text" name="name" /></td>
            </tr>
            <tr>
                <td>年龄：</td>
                <td><input type="text" name="age" /></td>
            </tr>
            <tr>
                <td>性别：</td>
                <td><input type="radio" name="sex" value="男" />男 <input
                        type="radio" name="sex" value="女" />女</td>
            </tr>
            <tr>
                <td>班级：</td>
                <td><input type="text" name="classno" /></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="添加" /> <input type="reset"
                                                              value="重置" /> <input type="button"
                                                                                   onclick="$('#fill').hide();$('#myform').hide();" value="关闭" /></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>