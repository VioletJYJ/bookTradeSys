<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="req" value="${pageContext.request }"/>
<c:set var="base" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath }/"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${base }" target="body">
    
    <title>My JSP 'bookdesc.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	* {
		font-size: 11pt;
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(${base}images/all.png) no-repeat;
		display: inline-block;
		
		background-position: 0 -902px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(${base}images/all.png) no-repeat;
		display: inline-block;
		
		background-position: 0 -938px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
<h1>购物车</h1>

<c:choose>
	<c:when test="${empty sessionScope.cart.all }">购物车中没有图书</c:when>
	<c:otherwise>
<table border="1" width="100%" cellspacing="0" background="black">
	<tr>
		<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
			<a href="cart/CartServlet?method=clear">清空购物车</a>
		</td>
	</tr>
	<tr>
		<th>图片</th>
		<th>书名</th>
		<th>作者</th>
		<th>单价</th>
		<th>数量</th>
		<th>小计</th>
		<th>操作</th>
	</tr>
<c:forEach items="${sessionScope.cart.all }" var="cartItem">	
	<tr>
		<td><div><img src="${cartItem.book.image }"/></div></td>
		<td>${cartItem.book.bname }</td>
		<td>${cartItem.book.author }</td>
		<td>${cartItem.book.price }元</td>
		<td>${cartItem.count }</td>
		<td><fmt:formatNumber value="${cartItem.price }" pattern="0.##"/>元</td>
		<td><a href="cart/CartServlet?method=del&bid=${cartItem.book.bid }">删除</a></td>
	</tr>
</c:forEach>
	<tr>
		<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
			合计：<fmt:formatNumber value="${cart.price }" pattern="0.##"/>元
		</td>
	</tr>
	<tr>
		<td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
			<a id="buy" href="order/OrderServlet?method=buy"></a>
		</td>
	</tr>
</table>

	</c:otherwise>
</c:choose>
  </body>
</html>
