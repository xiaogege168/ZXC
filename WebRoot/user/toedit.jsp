<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>用户修改</title>
</head>
<body>
	<h3 align="center">用户修改</h3>
	<input type="hidden" name="shopId" value="${skUser.skShop.shopId}">
	<form action="<c:url value='/user/modify'/>" method="post">
		<input type="hidden" name="userId" value="${skUser.userId}" />
		<table border="0" align="center" width="40%"
			style="margin-left: 100px">
			<tr>
				<td width="100px">用户名</td>
				<td width="40%"><input type="text" name="userName" value="${skUser.userName}"/></td>
				<td align="left"><label id="nameError" class="error">&nbsp;</label>
				</td>
			</tr>
			<tr>
				<td width="100px">密 码</td> 
				<td width="40%"><input type="password" name="userPassword" "/>
				</td>
				<td align="left"><label id="nameError" class="error">&nbsp;</label>
				</td>
			</tr>
			<tr>
				<td>店 名</td>
				<td><input type="text" name="shopId" value="${skUser.skShop.shopId}" />
				</td>
				<td><label id="genderError" class="error">&nbsp;</label></td>
			</tr>
			<!--         <tr> -->
			<!--             <td>性别</td> -->
			<!--             <td> -->
			<!--                  <input type="radio" name="gender" value="male" id="male" <c:if test="${customer.gender eq 'male'}">checked="checked"</c:if>/> -->
			<!--                 <label for="male">男</label> -->
			<!--                 <input type="radio" name="gender" value="female" id="female" <c:if test="${customer.gender eq 'female'}">checked="checked"</c:if> /> -->
			<!--                 <label for="female">女</label> -->
			<!--             </td> -->
			<!--             <td> -->
			<!--                 <label id="genderError"class="error">&nbsp;</label> -->
			<!--             </td> -->
			<!--         </tr> -->
			<tr>
				<td>手机</td>
				<td><input type="text" name="phoneNum" id="phone" value="${skUser.phoneNum}" /></td>
				<td><label id="phoneError" class="error">&nbsp;</label></td>
			</tr>
			<tr>
				<td>职位</td>
				<td><input type="text" name="job" id="phone" value="${skUser.job}"></input></td>
				<td><label id="discriptionError" class="error"></label></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="submit" value="确认修改" /> <input
					type="reset" name="reset" /></td>
			</tr>
		</table>
	</form>

</body>
</html>
