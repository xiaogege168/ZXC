<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>用户添加</title>
</head>
<body>
	<h3 align="center">用户添加</h3>
	<form action="<c:url value='/user/add'/>" method="post" align="center">
		<!--     <input type="hidden" name="method" value="edit"/> -->
		<!--     <input type="hidden" name="id" value="${customer.id}"/> -->
		<table border="0" width="40%"
			style="margin-left: 100px">
			<tr>
				<td width="100px">姓名</td>
				<td width="40%"><input type="text" name="userName" "/></td>
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
				<td><select name="shopId">
						    <option value="1">杏坛店</option>
							<option value="2">九江店</option>
							<option value="3">天佑城店</option>
							<option value="4">西樵店</option>
							<option value="5">风度广场店</option>
							<option value="6">小塘店</option>
							<option value="7">九江店</option>
							<option value="8">丹灶店</option>
				</select></td>   
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
				<td><input type="text" name="phoneNum" id="phone" /></td>
				<td><label id="phoneError" class="error">&nbsp;</label></td>
			</tr>
			<tr>
				<td>职位</td>
				<td><select name="job" >
						<option value="教练">教练</option>
						<option value="主教练">主教练</option>
						<option value="店长">店长</option>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="submit" value="确认添加" /> <input
					type="reset" name="reset" /></td>
			</tr>
		</table>
	</form>
</body>
</html>
