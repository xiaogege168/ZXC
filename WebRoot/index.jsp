<%--
  Created by IntelliJ IDEA.
  User: codingBoy
  Date: 16/10/23
  Time: 上午11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
	import="java.util.Vector"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:forEach var="item" begin="1" end="10">${item }</c:forEach>

<%
	Vector v = new Vector();
	v.add("A");
	v.add("B");
	v.add("C");
	v.add("D");
	pageContext.setAttribute("vector", v);
%>
<c:forEach items="${vector }" var="item"> ${item }</c:forEach>


<c:if test="${2>0}"> it's true that 2>0  </c:if>
<c:if test="${1==1 }" var="theTruth" scope="session" />
${theTruth}


<c:forEach var="index" begin="0" end="4">
      # ${index}: 
      <c:choose>
		<c:when test="${index == 1}">
          One!</br>
		</c:when>
		<c:when test="${index == 4}">
          Four!</br>
		</c:when>
		<c:when test="${index == 3}">
          Three!</br>
		</c:when>
		<c:otherwise>
          Huh?</br>
		</c:otherwise>
	</c:choose>
</c:forEach>


<jsp:forward page="/frame.jsp" />
