<%@ page language="java" contentType="text/html; charset=UTF-8"%> 
<%@taglib prefix="s" uri="/struts-tags" %> 
<html>
<head>
<title>登陆失败</title>
<link href="/ssh_blog/admin/style.css" type="text/css" rel="stylesheet"> 	
</head>
<body>
<div id="container">
<div id="banner">
		<h1>老刘的博客</h1>
</div>
<div id="bar">
	<center>
	<font color="red" size="6"><s:fielderror/></font><br>
	<a href="#" onclick="history.go(-1)">返回上一页</a></center>
</div>
</div>
</body>
</html>