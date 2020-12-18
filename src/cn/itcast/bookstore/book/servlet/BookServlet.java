package cn.itcast.bookstore.book.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.book.service.BookService;
import cn.itcast.servlet.BaseServlet;

public class BookServlet extends BaseServlet {
	private BookService bookService = new BookService();
	
	public String query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cid = request.getParameter("cid");
		List<Book> bookList = bookService.query(cid);
		request.setAttribute("bookList", bookList);
		return "/jsps/booklist.jsp";
	}
	
	public String desc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bid = request.getParameter("bid");
		Book book = bookService.findById(bid);
		request.setAttribute("book", book);
		return "/jsps/bookdesc.jsp";
	}
}
