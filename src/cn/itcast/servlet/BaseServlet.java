package cn.itcast.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {
	/*
	 * 它会根据请求中的m，来决定调用本类的哪个方法
	 */
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=utf-8");
		try {
			// 例如：http://localhost:8080/demo1/xxx?m=add
			String method = req.getParameter("method");// 它是一个方法名称
			Class c = this.getClass();
			Method m = c.getMethod(method, HttpServletRequest.class,
					HttpServletResponse.class);
			String result = (String) m.invoke(this, req, res);
			if(result != null && !result.isEmpty()) {
				req.getRequestDispatcher(result).forward(req, res);
			}
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
}
