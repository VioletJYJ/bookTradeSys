<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="req" value="${pageContext.request }"/>
<c:set var="base" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath }/"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${base }" target="body">
    
    <title>My JSP 'top.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		background: #4682B4; 
	}
	a {
		text-transform:none;
		text-decoration:none;
	} 
	a:hover {
		text-decoration:underline;
	}
</style>
  </head>
  
  <body>
<h1 style="text-align: center;">图 书 交 易 系 统</h1>
<div style="font-size: 10pt;">
<c:choose>
	<c:when test="${not empty user}">
		您好：${user.username }&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="cart/CartServlet?method=show">我的购物车</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="order/OrderServlet?method=orderList">我的订单</a>&nbsp;&nbsp;|&nbsp;&nbsp;
		<a href="UserServlet?method=exit" target="_parent">退出</a>
	</c:when>
	<c:otherwise>
		<a href="UserServlet?method=loginPre" target="_parent">登录</a> |&nbsp; 
		<a href="UserServlet?method=registPre" target="_parent">注册</a>
	</c:otherwise>
</c:choose>
</div>
  </body>
</html>
