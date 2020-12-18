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
		border: solid 2px gray;
		width: 75px;
		height: 75px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	
	#buy {
		background: url(${base}images/all.png) no-repeat;
		display: inline-block;
		
		background-position: 0 -412px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	#buy:HOVER {
		background: url(${base}images/all.png) no-repeat;
		display: inline-block;
		
		background-position: 0 -448px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>
  </head>
  
  <body>
<h1>当前订单</h1>

<table border="1" width="100%" cellspacing="0" background="black">
	<tr bgcolor="#ffcccc" bordercolor="#ffcccc">
		<td colspan="6">
			订单：${order.oid }　成交时间：<fmt:formatDate value="${order.ordertime }" pattern="yyyy-MM-dd HH:mm"/>　金额：<font color="red"><b>${order.price }</b></font>
		</td>
	</tr>
	<c:forEach items="${order.orderItems }" var="item">
		<tr bordercolor="gray" align="center">
			<td width="15%">
				<div><img src="${item.book.image }" height="75"/></div>
			</td>
			<td>书名：${item.book.bname }</td>
			<td>单价：${item.book.price }</td>
			<td>作者：${item.book.author }</td>
			<td>数量：${item.count }</td>
			<td>小计：${item.subtotal }</td>
		</tr>
	</c:forEach>
</table>
<br/>
<form method="post" action="order/OrderServlet" id="form" target="_parent">
	<input type="hidden" name="method" value="submit" />
	<input type="hidden" name="oid" value="${order.oid }"/>
	收货地址：<input type="text" name="address" size="50" value="湖北省武汉市华中师范大学张三收"/><br/>

	选择银行：<br/>
	<input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>工商银行
	<img src="<c:url value='/bank_img/icbc.bmp'/>" align="middle"/>
	<input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>中国银行
	<img src="<c:url value='/bank_img/bc.bmp'/>" align="middle"/><br/><br/>
	<input type="radio" name="pd_FrpId" value="ABC-NET-B2C"/>农业银行
	<img src="<c:url value='/bank_img/abc.bmp'/>" align="middle"/>
	<input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>建设银行
	<img src="<c:url value='/bank_img/ccb.bmp'/>" align="middle"/><br/><br/>
	<input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>交通银行
	<img src="<c:url value='/bank_img/bcc.bmp'/>" align="middle"/><br/>
</form>
<a id="buy" href="javascript:_go()"></a>
<script type="text/javascript">
	function _go() {
		document.getElementById("form").submit();
	}
</script>
  </body>
</html>
