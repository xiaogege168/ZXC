<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<title>约课表</title>
</head>
<body>
	<h3 align="center">约课表</h3>
	<table border="1" width="70%" align="center">
	<tr>${info}</tr>
		<tr>
			<th>约课时间</th>
			<th>小朋友</th>
			<th>上课时间</th>
			<th>XXXX</th>
		</tr>
		<c:forEach items="${ols}" var="order">
			<tr>
				<td>${order.orderTime}</td>
				<td>${order.orderTime}</td>
				<td>${order.skStudent.stuName}</td>
				<td>${order.ampm}</td>
				<td>${order.teachComment}</td>
			</tr>
		</c:forEach>
	</table>
	<br />
		<s:form action="/order/add" enctype="multipart/form-data"
		javascriptTooltip="true">
		<center>
			${user.stuName} 家长 你好，请选择上课时间，然后提交。<br />
			<s:radio label="今天约课时间"
				list="{'10:30-12:00', '17:30-19:00', '19:30-21:00'}" name="ampm"
				cssErrorClass="foo" />

			<br />
			<s:submit cssClass="btn btn-primary" value="确认提交"></s:submit>

			<br />
		</center>
	</s:form>
</body>
</html>
