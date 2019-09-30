<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>我要约课</title>
</head>
<body>
	<h3 align="center">我要约课</h3>

	<!-- 	<s:actionerror cssClass="alert alert-error" /> -->
	<!-- 	<s:actionmessage cssClass="alert alert-info" /> -->
	<!-- 	<s:fielderror cssClass="alert alert-error" /> -->

	<s:form action="/stu/execute" enctype="multipart/form-data"
		javascriptTooltip="true">
		<center>
			<s:fielderror cssClass="alert alert-error" />
			小朋友名字:<s:textfield label="小朋友名字" name="stuName" tooltip="请输入小朋友的真实姓名" />
			<br/>
			登 录  密 码:<s:password label="登录密码" name="stuPassword" tooltip="初始密码: 111，请立即修改" />


			<br />
			<s:submit cssClass="btn btn-primary" value="登录"></s:submit>

			<br />
		</center>
	</s:form>


</body>
</html>
