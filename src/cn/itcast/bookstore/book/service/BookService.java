package cn.itcast.bookstore.book.service;

import java.util.List;

import cn.itcast.bookstore.book.dao.BookDao;
import cn.itcast.bookstore.book.domain.Book;

public class BookService {
	private BookDao bookDao = new BookDao();

	public List<Book> query(String cid) {
		try {
			if (cid == null || cid.isEmpty()) {
				return bookDao.query();
			} else {
				return bookDao.query(cid);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	public Book findById(String bid) {
		try {
			return bookDao.findById(bid);
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}
}
