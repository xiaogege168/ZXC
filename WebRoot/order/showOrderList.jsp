<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title>约课表</title>
</head>
<body>
	<h3 align="center">约课表</h3>
	<table border="1" width="70%" align="center">
		<tr>
		<th>店 名</th>
			<th>历史约课</th>
			<th>小朋友</th>
			<th>上课时间</th>
			<th>删除</th>
		</tr>
		<c:forEach items="${ols}" var="order" varStatus="status">
			<tr <c:if test="${status.count%2==0 }">bgcolor="#CCCCFE"</c:if>>
				<!-- 				<td><s:date name="orderTime"  format="yyyy-MM-dd hh:mm" nice="false"  /></td> -->
				<td></td>
				<td>${order.orderTime }</td>
				<td>${order.skStudent.stuName}</td>
				<td>${order.ampm}</td>  
				<td><c:if test="${order.orderTime==today}">
						<a href="<%=basePath%>order/del?orderId=${order.orderId }">删除</a>
					</c:if></td>
			</tr>
		</c:forEach>

	</table>
	<br />
	<s:form action="/order/add" enctype="multipart/form-data"
		javascriptTooltip="true">
		<center>


			 <c:if test="${ok}">
			<s:radio label="今天约课时间" list="#{'10:30-12:00':'10:30-12:00','17:30-19:00':'17:30-19:00','19:30-21:00':'19:30-21:00'}" name="ampm"
				value='3' cssErrorClass="foo"/>
			<br/>
			 ${user.stuName} 家长 你好，请选择上课时间，然后提交。<br />
				<s:submit cssClass="btn btn-primary" value="确认提交"></s:submit>
			</c:if>
			<c:if test="${okk}">
			<br />
			  今天您已经预定过一节课了，如需修改时间，请删除后重新预定！！！
			<br />
			今天${shopName} <br />
			10:30-12:00 已有  ${n1}位小朋友报名上課
			<br />
			 17:30-19:00 已有  ${n2}位小朋友报名上課
			<br />
			 19:30-21:00 已有  ${n3} 位小朋友报名上課
			
			</c:if>
			<br />
		</center>
	</s:form>


</body>
</html>
