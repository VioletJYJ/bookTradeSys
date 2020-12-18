package cn.itcast.bookstore.order.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.cart.domain.CartItem;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderItem;
import cn.itcast.bookstore.order.service.OrderService;
import cn.itcast.bookstore.user.domain.User;
import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;
import cn.itcast.utils.PaymentUtil;

public class OrderServlet extends BaseServlet {
	private OrderService orderService = new OrderService();
	
	public String buy(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			request.setAttribute("msg", "购物车中没有商品, 不能生成订单！");
			return "/jsps/msg.jsp";
		}
		Order order = new Order();
		order.setOid(CommonUtils.uuid());
		order.setAddress(request.getParameter("address"));
		order.setOrdertime(new Date());
		order.setUser(user);
		order.setPrice(cart.getPrice());
		order.setState(1);
		for(CartItem ci : cart.getAll()) {
			OrderItem oi = new OrderItem();
			oi.setIid(CommonUtils.uuid());
			oi.setCount(ci.getCount());
			oi.setSubtotal(ci.getPrice());
			oi.setBook(ci.getBook());
			order.addOrderItem(oi);
		}
		orderService.add(order);
		cart.clear();
		
		request.setAttribute("order", order);
		return "/jsps/order/orderdesc.jsp";
	}
	
	public String orderDesc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String oid = request.getParameter("oid");
		Order order = orderService.findOrderByOid(oid);
		request.setAttribute("order", order);
		return "/jsps/order/orderdesc.jsp";
	}
	
	public String orderList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		List<Order> orderList = orderService.findOrderByUid(user.getUid());
		request.setAttribute("orderList", orderList);
		return "/jsps/order/orderlist.jsp";
	}
	
	/*
	 * 支付方式
	 */
	public String submit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String oid = request.getParameter("oid");
		Order order = orderService.load(oid);
		
		// 修改收货地址
		String address = request.getParameter("address");
		orderService.updateAddress(oid, address);
		

		String p0_Cmd = "Buy";// 业务类型，固定值为buy，即“买”
		String p1_MerId = "10001126856";// 在易宝注册的商号
		String p2_Order = order.getOid();// 订单编号
//		String p3_Amt = order.getPrice() + "";// 支付的金额
		String p3_Amt = "0.01";
		String p4_Cur = "CNY";// 交易种币，固定值为CNY，表示人民币
		String p5_Pid = "";// 商品名称
		String p6_Pcat = "";// 商品各类
		String p7_Pdesc = "";// 商品描述
		String p8_Url = "http://localhost:8080/bookstore/order/OrderServlet?method=back";// 电商的返回页面，当支付成功后，易宝会重定向到这个页面
		String p9_SAF = "";// 送货地址
		String pa_MP = "";// 商品扩展信息
		String pd_FrpId = request.getParameter("pd_FrpId");// 支付通道，即选择银行
		String pr_NeedResponse = "1";// 应答机制，固定值为1

		// 密钥，由易宝提供，只有商户和易宝知道这个密钥。
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl";

		// 通过上面的参数、密钥、加密算法，生成hmac值
		// 参数的顺序是必须的，如果没有值也不能给出null，而应该给出空字符串。
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue);
		
		// 把所有参数连接到网关地址后面
		String url = "https://www.yeepay.com/app-merchant-proxy/node";
		url += "?p0_Cmd=" + p0_Cmd + 
				"&p1_MerId=" + p1_MerId +
				"&p2_Order=" + p2_Order + 
				"&p3_Amt=" + p3_Amt + 
				"&p4_Cur=" + p4_Cur + 
				"&p5_Pid=" + p5_Pid + 
				"&p6_Pcat=" + p6_Pcat + 
				"&p7_Pdesc=" + p7_Pdesc + 
				"&p8_Url=" + p8_Url + 
				"&p9_SAF=" + p9_SAF + 
				"&pa_MP=" + pa_MP + 
				"&pd_FrpId=" + pd_FrpId + 
				"&pr_NeedResponse=" + pr_NeedResponse + 
				"&hmac=" + hmac;
//		System.out.println(url);
		// 重定向到网关
		response.sendRedirect(url);
		
		return null;
	}
	
	public String back(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		/*
		 * 易宝会提供一系列的结果参数，我们获取其中需要的即可
		 * 获取支付结果：r1_Code，1表示支付成功。
		 * 获取支付金额：r3_Amt
		 * 获取电商的订单号：r6_Order
		 * 获取结果返回类型：r9_BType，1表示重定向返回，2表示点对点返回，
		 *     但点对点我们收不到，因为我们的ip都是局域网ip。
		 */
		String r1_Code = request.getParameter("r1_Code");
		String r3_Amt = request.getParameter("r3_Amt");
		String r6_Order = request.getParameter("r6_Order");
		String r9_BType = request.getParameter("r9_BType");
		
		if(r1_Code.equals("1")) {
			if(r9_BType.equals("1")) {
				orderService.updateState(r6_Order, 2);
//				response.getWriter().print("<h1>支付成功！</h1>");//其实支付不成功时根本易宝根本就不会返回到本Servlet
//				response.getWriter().print("支付金额为：" + r3_Amt + "<br/>");
//				response.getWriter().print("订单号为：" + r6_Order + "<br/>");
				request.setAttribute("msg", "支付成功！");
				List<String> links = new ArrayList<String>();
				links.add("订单号为：" + r6_Order);
				links.add("支付金额为：" + r3_Amt);
				links.add("<a href='"+request.getContextPath()+"/index.jsp'/>主页</a>");
				request.setAttribute("links", links);
				return "/jsps/msg.jsp";
			}
		}
		response.getWriter().print("支付失败！");
		return "";
	}
}
