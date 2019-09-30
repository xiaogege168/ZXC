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
	<h3 align="center">各店库存列表</h3>

	<form action="<c:url value='/records/add'/>" method="post">
		<table border="1" width="70%" align="center" bgcolor="gray">
			<tr>
				<th>店名</th>
				<th>物品名</th>
				<th>数量</th>
				<th>新增入库数量</th>
				<th>销售出库数量</th>
			</tr>

			<c:forEach items="${ols}" var="skRepository" varStatus="status">
				<tr<c:if test="${skRepository.repositoryId%2==0 }">bgcolor="#CCCCFE"</c:if>>
						<input type="hidden" name="repositoryId" value="${skRepository.repositoryId}"> 
						
						<input type="hidden" name="shopId" value="${skRepository.skShop.shopId}">
					<td>${skRepository.skShop.address}<!--             <input type="text" name="" value="${skRepository.skShop.address}" readonly="readonly"> -->

					</td>
					<td>${skRepository.name}</td>
					<td>${skRepository.amount}</td>
					<td><input type="text" name="${skRepository.name}plusMount"></td>
					<td><input type="text" name="${skRepository.name}subMount"></td>

				</tr>
			</c:forEach>
		</table>
		<input type="submit"></input>

	</form>
	<br />
	

</body>
</html>
