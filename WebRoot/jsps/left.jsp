<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="req" value="${pageContext.request }"/>
<c:set var="base" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath }/"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${base }" target="body">
    
    <title>My JSP 'left.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		*{
			font-size:10pt;
			text-align: center;
		}
	</style>
  </head>
  
  <body>
<div style="background: #87CEFA; margin: 3px; padding: 3px;">
	<a style="text-decoration: none;" target="" href="${base }BookServlet?method=query">全部分类</a>
</div>
<c:forEach items="${categoryList }" var="category">
	<div style="background: #87CEFA; margin: 3px; padding: 3px;">
		<a style="text-decoration: none;" target="" href="BookServlet?method=query&cid=${category.cid }">${category.cname }</a>
	</div>
</c:forEach>
  </body>
</html>
