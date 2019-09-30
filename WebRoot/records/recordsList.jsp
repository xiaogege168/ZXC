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
<title>库存列表</title>
</head>
<body>
	<%-- <s:debug/> --%>
	<h3 align="center">库存出入库记录</h3>

		<table border="1" width="70%" align="center" bgcolor="#CCCCFE">
			<tr>
				<th>店名</th>
				<th>物品名</th>
				<th>数量</th>
				<th>新增数量</th>
				<th>销售数量</th>
				<th>操作日期</th>
			</tr>


			<c:forEach items="${ols}" var="orders" varStatus="status">
				<tr<c:if test="${orders.skRepository.skShop.shopId%2==0}"> bgcolor="pink"</c:if>>
					<td>${orders.skRepository.skShop.address}
<!-- 					 + ${orders.skRepository.repositoryId%2}					 -->
					</td>
					<td>${orders.skRepository.name}</td>
					<td>${orders.skRepository.amount}</td>
					<td>${orders.plusMount}</td>
					<td>${orders.subMount}</td>  
					<td>${orders.createTime}</td>

				</tr>
			</c:forEach>
		</table> 
		<input type="submit"></input>

	<br />

</body>
</html>
