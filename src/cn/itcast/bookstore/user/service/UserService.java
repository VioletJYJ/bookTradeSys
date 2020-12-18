package cn.itcast.bookstore.user.service;

import java.sql.SQLException;

import cn.itcast.bookstore.user.dao.UserDao;
import cn.itcast.bookstore.user.domain.User;

public class UserService {
	private UserDao userDao = new UserDao();
	
	public void regist(User user) {
		try {
			userDao.add(user);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public User login(String username, String password) {
		try {
			return userDao.login(username, password);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
