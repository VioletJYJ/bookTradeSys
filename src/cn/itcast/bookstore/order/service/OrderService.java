package cn.itcast.bookstore.order.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.bookstore.order.dao.OrderDao;
import cn.itcast.bookstore.order.domain.Order;

public class OrderService {
	private OrderDao orderDao = new OrderDao();
	
	public void add(Order order) {
		try {
			orderDao.add(order);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Order> findOrderByUid(String uid) {
		try {
			return orderDao.findOrderByUid(uid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Order findOrderByOid(String oid) {
		try {
			return orderDao.findOrderByOid(oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Order load(String oid) {
		try {
			return orderDao.load(oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateState(String oid, int state) {
		try {
			orderDao.updateState(oid, state);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void updateAddress(String oid, String address) {
		try {
			orderDao.updateAddress(oid, address);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
