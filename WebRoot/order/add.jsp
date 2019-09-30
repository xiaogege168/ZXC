<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
<title>我要约课</title>
</head>
<body>
	<h3 align="center">我要约课</h3>

<!-- 	<s:actionerror cssClass="alert alert-error" /> -->
<!-- 	<s:actionmessage cssClass="alert alert-info" /> -->
<!-- 	<s:fielderror cssClass="alert alert-error" /> -->



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
