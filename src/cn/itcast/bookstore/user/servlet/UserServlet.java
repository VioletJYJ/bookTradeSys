package cn.itcast.bookstore.user.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.itcast.bookstore.user.domain.User;
import cn.itcast.bookstore.user.service.UserService;
import cn.itcast.servlet.BaseServlet;
import cn.itcast.utils.CommonUtils;

public class UserServlet extends BaseServlet {
	private UserService userService = new UserService();

	public String exit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User)request.getSession().getAttribute("user");
		if(user == null) {
			request.setAttribute("msg", "您还没有登录，不能退出！");
		} else {
			request.getSession().removeAttribute("user");
			request.setAttribute("msg", "您已退出！");
		}
		List<String> links = new ArrayList<String>();
		links.add("<a href='" + request.getContextPath() + "/index.jsp'>主页</a>");
		request.setAttribute("links", links);
		return "/jsps/msg.jsp";
	}
	
	public String registPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsps/regist.jsp";
	}
	
	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = CommonUtils.toBean(request.getParameterMap(), User.class);
		user.setUid(CommonUtils.uuid());
		userService.regist(user);
		request.setAttribute("msg", "注册成功");
		List<String> links = new ArrayList<String>();
		links.add("<a href='UserServlet?method=loginPre'>登录页面</a>");
		links.add("<a href='" + request.getContextPath() + "/index.jsp'>主页</a>");
		request.setAttribute("links", links);
		return "/jsps/msg.jsp";
	}
	
	public String loginPre(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsps/login.jsp";
	}
	
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.login(username, password);
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.setAttribute("msg", "登录成功");
			List<String> links = new ArrayList<String>();
			links.add("<a href='" + request.getContextPath() + "/index.jsp" + "'>主页</a>");
			request.setAttribute("links", links);
			return "/jsps/msg.jsp";
		} else {
			request.setAttribute("msg", "用户名或密码错误！");
			List<String> links = new ArrayList<String>();
			links.add("<a href='" + request.getContextPath() + "/index.jsp" + "'>主页</a>");
			request.setAttribute("links", links);
			return "/jsps/msg.jsp";
		}
	}
}
