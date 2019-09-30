<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>学生列表</title>
</head>
<body>
    <h3 align="center" >所有学生列表</h3>
<!--     <s:debug></s:debug> -->

<s:a action="findByMonth">本月报名学生</s:a>
    <table border="1" width="70%" align="center" bgcolor="#CCCCFE">
        <tr>
            <th>店名</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>联系方式</th>
            <th>课程</th>
            <th>价格</th>
            <th>报名时间</th>
        </tr>
        
        
        <c:forEach items="${stus}" var="stu">
        <tr<c:if test="${orders.skRepository.skShop.shopId%2==0}"> bgcolor="pink"</c:if>>
			
            <td>${stu.skShop.address}</td>
            <td>${stu.stuName}</td>
            <td>${stu.gender}</td>
            <td>${stu.age}</td>
            <td>${stu.parentNum}</td>
            <td>${stu.skCourse.courseTime}</td>
            <td>${stu.realPrice}</td>
            <td>${stu.createTime}
<!--             <s:date name="stu.createTime" format="yyyy-MM-dd " /> -->
            </td>
        </tr>
        </c:forEach>
    </table>
<br/>


</body>
</html>
