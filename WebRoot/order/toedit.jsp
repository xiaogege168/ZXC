<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>约课表</title>
</head>
<body>
	<h3 align="center">约课表</h3>
	<table border="1" width="70%" align="center">
		<tr>
			<th>ID</th>
			<th>小朋友</th>
			<th>约课时间</th>
			<th>上课时间</th>
		</tr>
		<c:forEach items="${ols}" var="order">
			<tr>
				<td>${order.orderTime}</td>
				<td>${order.skStudent.stuName}</td>
				<td>${order.teachComment}</td>
				<td>${order.ampm}</td>
			</tr>
		</c:forEach>
	</table>
	<br />


</body>
</html>
