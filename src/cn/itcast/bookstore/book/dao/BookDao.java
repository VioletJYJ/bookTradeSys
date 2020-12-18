package cn.itcast.bookstore.book.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.jdbc.utils.JdbcUtils;

public class BookDao {
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	
	public List<Book> query() throws SQLException {
		String sql = "select * from book";
		return qr.query(sql, new BeanListHandler<Book>(Book.class));
	}
	
	public List<Book> query(String cid) throws SQLException {
		String sql = "select * from book where cid=?";
		return qr.query(sql, new BeanListHandler<Book>(Book.class), cid);
	}
	
	public Book findById(String bid) throws SQLException {
		String sql = "select * from book where bid=?";
		return qr.query(sql, new BeanHandler<Book>(Book.class), bid);
	}	
}
