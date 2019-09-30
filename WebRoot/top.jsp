<%--
  Created by IntelliJ IDEA.
  User: codingBoy
  Date: 16/10/23
  Time: 下午12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 他的作用是为本页面所有的表单和超链接指定显示内容的框架-->
    <base target="main">
    <title>My JSP 'top.jsp' starting page</title>
</head>
<body style="text-align: center;">
    <h1>ERP管理系统</h1>
    <a href="<c:url value='/user/toadd.jsp'/>">添加员工</a>&nbsp;&nbsp;
    <a href="<c:url value='/user/findAll'/>">查询员工</a>&nbsp;&nbsp;
    <a href="<c:url value='/shop/findAll'/>">查询店名</a>&nbsp;&nbsp;
    <a href="<c:url value='/stu/findAll'/>">查询学生</a>&nbsp;&nbsp;
    <a href="<c:url value='/course/findAll'/>">查询课程</a>&nbsp;&nbsp;
    <a href="<c:url value='/order/findAll'/>">查询约课</a>
<!--     <a href="<c:url value='/query.jsp'/>">高级搜索</a> -->

</body>
</html>
