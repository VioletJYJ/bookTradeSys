package cn.itcast.bookstore.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.bookstore.user.domain.User;
import cn.itcast.jdbc.utils.JdbcUtils;

public class UserDao {
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
	
	public void add(User user) throws SQLException {
		String sql = "insert into user values(?,?,?)";
		qr.update(sql, user.getUid(), user.getUsername(), user.getPassword());
	}
	
	public User login(String username, String password) throws SQLException {
		String sql = "select * from user where username=? and password=?";
		return qr.query(sql, new BeanHandler<User>(User.class), username, password);
	}
	
	public User findUserByUid(String uid) throws SQLException {
		String sql = "select * from user where uid=?";
		return qr.query(sql, new BeanHandler<User>(User.class), uid);
	}
}
