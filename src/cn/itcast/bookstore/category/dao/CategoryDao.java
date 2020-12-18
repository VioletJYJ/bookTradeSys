package cn.itcast.bookstore.category.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.bookstore.category.domain.Category;
import cn.itcast.jdbc.utils.JdbcUtils;

public class CategoryDao {
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	public List<Category> all() throws SQLException {
		String sql = "select * from category";
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
	}
}
