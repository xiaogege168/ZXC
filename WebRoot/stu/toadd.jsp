<%--
  Created by IntelliJ IDEA.
  User: codingBoy
  Date: 16/10/23
  Time: 下午12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Title</title>
</head>
<body>
		<h3 align="center">添加学员</h3>
	
		<form action="<c:url value='/stu/add'/>" method="post" align="center">

			<input type="hidden" name="createTime" value="${createTime }">


			<table border="0" align="center" width="40%"
				style="margin-left: 100px">
				<tr>
					<td>店名</td>
					<td><select name="skShopId">
							<option value="1">杏坛店</option>
							<option value="2">九江店</option>
							<option value="3">天佑城店</option>
							<option value="4">西樵店</option>
							<option value="5">风度广场店</option>
							<option value="6">小塘店</option>
							<option value="7">九江店</option>
							<option value="8">丹灶店</option>
					</select></td>
					<td><label id="genderError" class="error">&nbsp;</label></td>
				</tr>
				<tr>
					<td width="100px">小朋友名字</td>
					<td width="40%"><input type="text" name="stuName" /></td>
					<td align="left"><label id="nameError" class="error">&nbsp;</label>
					</td>
				</tr>
				<tr>
					<td width="100px">登录密码</td>
					<td width="40%"><input type="password" name="stuPassword" /></td>
					<td align="left"><label id="nameError" class="error">&nbsp;</label>
					</td>
				</tr>
				<tr>
					<td width="100px">年龄</td>
					<td width="40%"><input type="text" name="age" /></td>
					<td align="left"><label id="nameError" class="error">&nbsp;</label>
					</td>
				</tr>
				<tr>
					<td>性别</td>
					<td><input type="radio" name="gender" value="男" id="male" />
						<label for="male">男</label> <input type="radio" name="gender"
						value="女" id="female" /> <label for="female">女</label></td>
					<td><label id="genderError" class="error">&nbsp;</label></td>
				</tr>


				<tr>
					<td>家长电话</td>
					<td><input type="text" name="parentNum" id="parentNum" /></td>
					<td><label id="phoneError" class="error">&nbsp;</label></td>
				</tr>
				<tr>
					<td>所报课程</td>
					<td><select name="skCourseId">
							<option value="1">15节</option>
							<option value="2">35节</option>
							<option value="3">一个月</option>
							<option value="4">二个月</option>
							<option value="5">半年</option>
							<option value="6">一年</option>
					</select></td>
					<td><label id="genderError" class="error">&nbsp;</label></td>
				</tr>

				<tr>
					<td>备注</td>
					<td><textarea rows="5" cols="30" name="others"></textarea></td>
					<td><label id="descriptionError" class="error">&nbsp;</label></td>
				</tr>
				<tr>
					<td>报名价格</td>
					<td><input type="text" name="realPrice" id="email" /></td>
					<td><label id="emailError" class="error">&nbsp;</label></td>
				</tr>

				<tr>
					<td></td>
					<td><input type="submit" name="submit" /> <input type="reset"
						name="reset" /></td>
				</tr>
			</table>
		</form>
</body>
</html>
