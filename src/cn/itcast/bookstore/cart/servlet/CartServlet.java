package cn.itcast.bookstore.cart.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.bookstore.cart.domain.Cart;
import cn.itcast.bookstore.cart.domain.CartItem;
import cn.itcast.servlet.BaseServlet;

public class CartServlet extends BaseServlet {
	private BookService bookService = new BookService();
	
	public String add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		Integer count = Integer.parseInt(request.getParameter("count"));
		
		Book book = bookService.findById(bid);
		
		CartItem ci = new CartItem(book, count);
		
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
		}
		cart.add(ci);
		request.getSession().setAttribute("cart", cart);
		
		return "/jsps/cart/cartlist.jsp";
	}
	
	public String show(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {	
		return "/jsps/cart/cartlist.jsp";
	}
	
	public String del(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		cart.del(bid);
		return "/jsps/cart/cartlist.jsp";
	}
	
	public String clear(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart != null) {
			cart.clear();
		}
		return "/jsps/cart/cartlist.jsp";
	}
}
