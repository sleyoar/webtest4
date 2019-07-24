<%--
  Created by IntelliJ IDEA.
  User: YT
  Date: 2019/7/17
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>学生信息</title>
</head>
<style>
    table {
        text-align: center;
    }

    table tr {
        height: 30px;
    }

    table tr th {
        width: 130px;
    }
</style>
<%
    String context = request.getContextPath();
%>
<link href="../css/pagination.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="../js/jquery-3.4.1.js"></script>
<script type="text/javascript" src="../js/jquery.pagination.js"></script>
<script type="text/javascript">
    //点击分页按钮后触发的动作
    function handlePaginationClick(new_page_index, pagination_container) {
        $("#stuForm").attr("action", "<%=context%>/SqlStudentServlet?pageNum=" + (new_page_index + 1));
        $("#stuForm").submit();
        return false;
    }

    $(function () {
        $("#News-Pagination").pagination(${result.totalRecord}, {
            items_per_page:${result.pageSize},//每页数据
            current_page: ${result.currentPage}-1,
            num_diaplay_entries: 8,//分页显示条数
            next_text: "下一页",
            prev_text: "上一页",
            num_edge_entries: 2,//连接分页主体，显示的条目数
            callback: handlePaginationClick
        });

        $("#gender").val("${gender}");

    })
</script>
<body>
<div style="margin-left: 100px;margin-top: 100px">
    <div>
        <font color="red">${errorMsg}</font>
    </div>
    <div>
        <form action="<%=context%>/SqlStudentServlet" id="stuForm" method="post">
            姓名<input type="text" name="stuName" style="width: 120px">
            &nbsp;
            性别
            <select name="gender" id="gender" style="width: 80px">
                <option value="0">全部</option>
                <option value="1">男</option>
                <option value="2">女</option>
            </select>
            &nbsp;&nbsp;
            <input type="submit" value="查询">
        </form>
    </div>
    <br>
    学生信息表：<br>
    <br>
    <!-- 后台返回结果为空-->
    <c:if test="${fn:length(result.dataList) eq 0}">
        <span>查询结果不存在</span>
    </c:if>
    <!-- 后台返回结果不为空-->
    <c:if test="${fn:length(result.dataList) gt 0}">
        <table border="1" cellspacing="0" style="border-collapse: collapse">
            <thead>
            <tr>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>家庭地址</th>
            </tr>
            </thead>
            <c:forEach items="${result.dataList}" var="student" varStatus="status">
                <tr <c:if test="${status.index%2==1}">style="background-color:rgb(219,241,212);" </c:if>>
                    <td>${student.stuName}</td>
                    <td>
                        <c:if test="${student.gender eq 1}">男</c:if>
                        <c:if test="${student.gender eq 2}">女</c:if>
                    </td>
                    <td>${student.age}</td>
                    <td>${student.address}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <div id="News-Pagination"></div>
    </c:if>
</div>
</body>
</html>
