<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>客户列表</title>
</head>
<body>
<%-- <s:debug/> --%>
    <h3 align="center" >各店业绩列表</h3>
   本月总业绩: ${monthIncome}元
    <table border="1" width="70%" align="center" bgcolor="pink">
        <tr>
            <th>店名</th>
            <th>店长名</th>
            <th>联系方式</th>
            <th>本月业绩</th>
            <th>本年业绩</th>
            <th>約課查询</th>
            <th>库存查询</th>
        </tr>
        
        
        <c:forEach items="${shopList}" var="sk" varStatus="status">
        <tr <c:if test="${status.count%2==0 }">bgcolor="#CCCCFE"</c:if> >
            <td>${sk.address}</td>
            <td>${sk.skUser.userName}</td>
            <td>${sk.skUser.phoneNum}</td> 
            <td>${sk.monthIncome}</td>
            <td></td>
            <td><a href="<%=basePath %>order/findByShop?shopId=${sk.shopId }">${sk.address}約課查询</a></td>
            <td><a href="<%=basePath %>goods/findByShop?shopId=${sk.shopId }">${sk.address}库存查询</a></td>
        </tr>
        </c:forEach>
    </table>
<br/>
<center>
    第${pb.pc}页/共${pb.tp}页
    <a href="${pb.url}&pc=1">首页</a>
    <c:if test="${pb.pc>1}">
        <a href="${pb.url}&pc=${pb.pc-1}">上一页</a>
    </c:if>

    <c:choose>
        <c:when test="${pb.tp<=10}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${pb.tp}"/>
        </c:when>
        <c:otherwise>
            <c:set var="begin" value="${pb.pc-5}"/>
            <c:set var="end" value="${pb.pc+4}"/>
            <%--头溢出--%>
            <c:if test="${begin<1}">
                <c:set var="begin" value="1"/>
                <c:set var="end" value="10"/>
            </c:if>
            <%--尾溢出--%>
            <c:if test="${end>pb.tp}">
                <c:set var="end" value="${pb.tp}"/>
                <c:set var="begin" value="${pb.tp-9}"/>
            </c:if>
        </c:otherwise>
    </c:choose>

    <%--循环遍历页码列表--%>
    <c:forEach var="i" begin="${begin}" end="${end}">
        <c:choose>
            <c:when test="${i eq pb.pc}">
                [${i}]
            </c:when>
            <c:otherwise>
                <a href="${pb.url}&pc=${i}">[${i}]</a>
            </c:otherwise>
        </c:choose>

    </c:forEach>


    <c:if test="${pb.pc<pb.tp}">
    <a href="${pb.url}&pc=${pb.pc+1}">下一页</a>
    </c:if>
    <a href="${pb.url}&pc=${pb.tp}">尾页</a>

</center>

</body>
</html>
