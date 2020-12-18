package cn.itcast.bookstore.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.bookstore.category.service.CategoryService;
import cn.itcast.servlet.BaseServlet;

public class IndexServlet extends BaseServlet {
	private CategoryService categoryService = new CategoryService();
	
	public String body(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsps/body.jsp";
	}
	
	public String top(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		return "/jsps/top.jsp";
	}
	
	public String left(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Category> categoryList = categoryService.all();
		request.setAttribute("categoryList", categoryList);
		return "/jsps/left.jsp";
	}
}
