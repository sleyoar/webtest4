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
    table{text-align: center;}
    table tr {height: 30px;}
    table tr th {width: 130px;}
</style>
<%
    String context=request.getContextPath();
%>
<script type="text/javascript">
    //当前显示第几页数据
    var currentPage=${result.currentPage};
    var totalPage=${result.totalPage};

    function submitForm(actionUrl) {
        var formElement=document.getElementById("stuForm");
        formElement.action=actionUrl;
        formElement.submit();
    }

    function firstPage() {
        if(currentPage==1){
            alert("已经是第一页数据");
            return false;
        }else{
            submitForm("${pageContext.request.contextPath}/SublistServlet?pageNum=1");
            return true;
        }
    }
    function nextPage() {
        if(currentPage==totalPage){
            alert("已经是最后一页数据");
            return false;
        }else{
            submitForm("${pageContext.request.contextPath}/SublistServlet?pageNum=${result.currentPage+1}");
            return true;
        }
    }
    function previousPage() {
        if(currentPage==1){
            alert("已经是第一页数据");
            return false;
        }else {
            submitForm("${pageContext.request.contextPath}/SublistServlet?pageNum=${result.currentPage-1}");
            return true;
        }
    }
    function lastPage() {
        if(currentPage==totalPage){
            alert("已经是最后一页数据");
            return false;
        }else{
            submitForm("${pageContext.request.contextPath}/SublistServlet?pageNum=${result.totalPage}");
            return true;
        }
    }
    function initPage(){
        var genderRequest = "${gender}" ;
        var genderVal = 0;
        var genderElement = document.getElementById("gender");
        if(genderRequest != ""){
            genderVal = parseInt(genderRequest);
        }

        var options = genderElement.options;
        var i = 0;
        for(i = 0; i < options.length; i++){
            if(options[i].value == genderVal){
                options[i].selected=true;
                break;
            }
        }
    }

</script>
<body onload="initPage();">
<div style="margin-left: 100px;margin-top: 100px">
    <div>
        <font color="red">${errorMsg}</font>
    </div>
    <div>
        <form action="<%=context%>/SublistServlet" id="stuForm" method="post">
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
        <br>共${result.totalRecord}条记录共${result.totalPage}页&nbsp;&nbsp;当前第${result.currentPage}页&nbsp;&nbsp;
        <a href="#" onclick="firstPage();">首页</a>&nbsp;&nbsp;
        <a href="#" onclick="nextPage();">下一页</a>&nbsp;&nbsp;
        <a href="#" onclick="previousPage();">上一页</a>&nbsp;&nbsp;
        <a href="#" onclick="lastPage();">尾页</a>
    </c:if>
</div>
</body>
</html>
