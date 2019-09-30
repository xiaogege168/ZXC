<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
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
<script type="text/javascript">

	function validator(){
		if(myform.username.value==""){
			alert("评论人不能为空！");
			myform.username.focus();
			return false;
		}
		if(myform.content.value==""){
			alert("内容不能为空！");
			myform.content.focus();
			return false;
		}
		return true;
	}
</script>

</head>
<body>
<div id="container">	
	<div id="banner">
		<h1><a href="<%=basePath %>Main/Main_list!listMain">老刘的博客</a></h1>
	</div>

<div id="center">
<div class="content">

<table id="tab">
  <tr>
    <td><h2>${blog.title }</h2></td>
  </tr>
  <tr>
    <td>${blog.content }</td>
  </tr>
 
  <tr>
    <td>${blog.createdtime }</td>
  </tr>
 
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="79">
	<c:forEach items="${CommentPageModel.list}" var="comment">
	    <table id="tab">
	      <tr>
	        <td>${comment.username }的评论</td>
	        </tr>
	      <tr>
	        <td>${comment.content }</td>
	        </tr>
	      <tr>
	        <td>${comment.createdtime }</td>
	        </tr>
	    </table><br/><br/>
	</c:forEach>

   <p>&nbsp;</p></td>
  </tr>
  <tr><td>
  
  	<!-- 产生分页的连接-->
   	&nbsp;<a href="<%=basePath %>Main/Main_diapaly!DispalyBlog?id=${blog.id }&pageNo=${CommentPageModel.topPageNo}">|&lt;&lt;</a> &nbsp;
   		<a href="<%=basePath %>Main/Main_diapaly!DispalyBlog?id=${blog.id }&pageNo=${CommentPageModel.previousPageNo}"> &lt;  </a>
   		${RecentComment.pageNo }/${RecentComment.totalPages }&nbsp;
   		<a href="<%=basePath %>Main/Main_diapaly!DispalyBlog?id=${blog.id }&pageNo=${CommentPageModel.nextPageNo}"> &gt;  </a>&nbsp;
   		<a href="<%=basePath %>Main/Main_diapaly!DispalyBlog?id=${blog.id }&pageNo=${CommentPageModel.buttomPageNo}"> &gt;&gt;|</a>
  
  <p>&nbsp;</p></td></tr>
  <tr>
    <td><s:form action="Comment_add!addComment" namespace="/Comment" name="myform">
    <input type="hidden" name="blog_id" value="${blog.id }"/>
      <table id="tab">
        <tr>
          <td >评论人：</td>
          <td><label>
            <input name="username" type="text" id="username" size="20" />
          </label></td>
        </tr>
        <tr>
          <td>内容：</td>
          <td><label>
            <textarea name="content" cols="40" rows="10" id="content"></textarea>
          </label></td>
        </tr>
        <tr>
          <td><label>
            <input type="submit" name="button" id="button" value="提交" onclick="return validator()"/>
          </label></td>
          <td>&nbsp;</td>
        </tr>      
      </table>
        </s:form>
    </td>
  </tr>
</table>
<br clear="all" />
</div><!-- end content -->
</div><!-- end center -->

<div id="right">
<div class="sidebar">
        <ul>
    	<li>老刘自己的博客，欢迎大家访问</li>
      </ul>

  	    <h2>最近的主题</h2>
		<ul>	
	<c:forEach items="${BlogpageModel.list}" var="blog">
	    		<li><a href="<%=basePath %>Main/Main_diapaly!DispalyBlog?id=${blog.id }" target="">${blog.title }</a></li>
     </c:forEach>
      </ul>
  	
	   
	   <h2>最近的评论</h2>
	  <ul>		
	<c:forEach items="${RecentComment.list}" var="rcomment">
	  	    <li><a href="<%=basePath %>Main/Main_diapaly!DispalyBlog?id=${rcomment.blog.id }" target="">${rcomment.content }</a></li>
      </c:forEach>        
      </ul>
  	   	
</div><!-- end sidebar -->	
</div><!-- end right -->
</div><!-- end container -->
</body>
</html>
  
