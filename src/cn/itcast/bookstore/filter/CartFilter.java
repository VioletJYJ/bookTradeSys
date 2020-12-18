package cn.itcast.bookstore.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.user.domain.User;
import cn.itcast.filter.HttpFilter;

public class CartFilter extends HttpFilter {
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		User user = (User) request.getSession().getAttribute("user");
		if(user == null) {
			List<String> links = new ArrayList<String>();
			links.add("<a href='" + request.getContextPath() + "/jsps/login.jsp'>登录</a>");
			links.add("<a href='" + request.getContextPath() + "/jsps/regist.jsp'>注册</a>");
			links.add("<a href='" + request.getContextPath() + "/index.jsp'>主页</a>");
			request.setAttribute("links", links);
			request.setAttribute("msg", "您还没有登录，不能购买图书，请先登录！");
			request.getRequestDispatcher("/jsps/msg.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}
}
