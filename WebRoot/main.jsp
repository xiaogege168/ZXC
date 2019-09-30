<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>老刘的博客</title>
 <link rel="stylesheet" type="text/css" href="/ssh_blog/style.css" />
</head>
<body>
<div id="container">	
	<div id="banner">
		<h1><a href="<%=basePath %>/admin/login.jsp">老刘的博客</a></h1>
	</div>

<div id="center">
<div class="content">
    <!-- list blog begin  -->
	<c:forEach items="${BlogpageModel.list}" var="blog">

   	<h2>${fn:substring(blog.createdtime,0,10) }</h2> 

    <div class="entry">
	    <a id="6"></a>		
		<h3><a href="<%=basePath %>Main/Main_diapaly!DispalyBlog?id=${blog.id }" target="_blank">${blog.title }</a></h3>
			<p><c:if test="${fn:length(blog.content) lt 200}"> 
				${blog.content}
			</c:if></p>
			<p><c:if test="${fn:length(blog.content) gt 200}"> 
				${fn:substring(blog.content,0,200)}${"..." }
			</c:if></p>
		<p class="posted"> ${blog.createdtime } <a href="<%=basePath %>Main/Main_CategoryBlog!CategoryBlog?categoryid=${blog.category.id }">${blog.category.name }</a> | <a href="<%=basePath %>Main/Main_diapaly!DispalyBlog?id=${blog.id }">查看评论</a></p>
    </div>
   	</c:forEach>
	   	
	<!-- 产生分页的连接-->
   	&nbsp;<a href="<%=basePath %>Main/Main_list!listMain?pageNo=${BlogpageModel.topPageNo}">|&lt;&lt;</a> &nbsp;
   		<a href="<%=basePath %>Main/Main_list!listMain?pageNo=${BlogpageModel.previousPageNo}"> &lt;  </a>
   		${BlogpageModel.pageNo }/${BlogpageModel.totalPages }&nbsp;
   		<a href="<%=basePath %>Main/Main_list!listMain?pageNo=${BlogpageModel.nextPageNo}"> &gt;  </a>&nbsp;
   		<a href="<%=basePath %>Main/Main_list!listMain?pageNo=${BlogpageModel.buttomPageNo}"> &gt;&gt;|</a>
 <!-- end list -->	



</div><!-- end content -->
</div><!-- end center -->

<div id="right">
<div class="sidebar">
        <ul>
    	<li>老刘自己的博客，欢迎大家访问</li>
      </ul>

  
  <h2>分类</h2>
   <ul>		
	<li><a href="<%=basePath %>Main/Main_category!listMain?pageNo=${BlogpageModel.pageNo }&command=1">全部</a></li>	
			<c:if test="${command=='pageModel'}"> 
				<c:forEach items="${CategorypageModel.list}" var="category">
				<li><a href="<%=basePath %>Main/Main_CategoryBlog!CategoryBlog?categoryid=${category.id}">${category.name }</a></li>        
				</c:forEach> 
			</c:if>
			<c:if test="${command=='list' }"> 
				<c:forEach items="${CategorList}" var="category">
				<li><a href="<%=basePath %>Main/Main_CategoryBlog!CategoryBlog?categoryid=${category.id}">${category.name }</a></li>        
				</c:forEach> 
			</c:if>	
   </ul>
  	    <h2>最近的主题</h2>
		<ul>	
	<c:forEach items="${RecentBlog.list}" var="blog">
	    		<li><a href="<%=basePath %>Main/Main_diapaly!DispalyBlog?id=${blog.id }" target="_blank">${blog.title }</a></li>
     </c:forEach>
      </ul>
  	
	   
	   <h2>最近的评论</h2>
	  <ul>		
	<c:forEach items="${CommentpageModel.list}" var="comment">
	  	    <li><a href="<%=basePath %>Main/Main_diapaly!DispalyBlog?id=${comment.blog.id }" target="_blank">${comment.content }</a></li>
      </c:forEach>        
      </ul>
  	   	
</div><!-- end sidebar -->	
</div><!-- end right -->
</div><!-- end container -->
</body>
</html>