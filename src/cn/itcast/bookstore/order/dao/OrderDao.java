package cn.itcast.bookstore.order.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.itcast.bookstore.book.domain.Book;
import cn.itcast.bookstore.order.domain.Order;
import cn.itcast.bookstore.order.domain.OrderItem;
import cn.itcast.bookstore.user.dao.UserDao;
import cn.itcast.bookstore.user.domain.User;
import cn.itcast.jdbc.utils.JdbcUtils;
import cn.itcast.utils.CommonUtils;

public class OrderDao {
	private QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());

	public void add(Order order) throws SQLException {
		String sql = "insert into orders values(?,?,?,?,?,?)";
		qr.update(sql, order.getOid(), order.getOrdertime(), order.getPrice(),
				order.getState(), order.getUser().getUid(), order.getAddress());
		
		////////////////////////
		
		Set<OrderItem> orderItems = order.getOrderItems();
		sql = "insert into orderitem values(?,?,?,?,?)";
		for(OrderItem item : orderItems) {
			qr.update(sql, item.getIid(), item.getCount(), item.getSubtotal(), order.getOid(), item.getBook().getBid());
		}
	}
	
	public List<Order> findOrderByUid(String uid) throws SQLException {
		// 获取当前User对象
		UserDao userDao = new UserDao();
		User user = userDao.findUserByUid(uid);
		
		// 获取当前uid所有订单
		String sql = "select * from orders where uid=?";
		List<Order> orderList = qr.query(sql, new BeanListHandler<Order>(Order.class), uid);
		
		// 获取每个order的订单项目
		sql = "SELECT * FROM  orderitem i, book b WHERE i.bid=b.bid and oid=?";
		for(Order order : orderList) {
			order.setUser(user);
			// 获取当前订单的所有订单项(包含图书)
			List<Map<String,Object>> maps = qr.query(sql, new MapListHandler(), order.getOid());
			// 遍历所有订单项(包含图书)
			for(Map<String,Object> map : maps) {
				OrderItem item = CommonUtils.toBean(map, OrderItem.class);
				Book book = CommonUtils.toBean(map, Book.class);
				item.setBook(book);
				order.addOrderItem(item);
			}
		}
		return orderList;
	}
	
	public Order findOrderByOid(String oid) throws SQLException {
		// 获取当前uid所有订单
		String sql = "select * from orders where oid=?";
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
		// 获取每个order的订单项目
		sql = "SELECT * FROM orderitem i, book b WHERE i.bid=b.bid AND i.oid=?";
		List<Map<String,Object>> maps = qr.query(sql, new MapListHandler(), oid);
		// 遍历所有订单项(包含图书)
		for(Map<String,Object> map : maps) {
			OrderItem item = CommonUtils.toBean(map, OrderItem.class);
			Book book = CommonUtils.toBean(map, Book.class);
			item.setBook(book);
			order.addOrderItem(item);
		}
		return order;
	}
	
	public Order load(String oid) throws SQLException {
		// 获取当前uid所有订单
		String sql = "select * from orders where oid=?";
		Order order = qr.query(sql, new BeanHandler<Order>(Order.class), oid);
		return order;
	}
	
	// 修改订单状态
	public void updateState(String oid, int state) throws SQLException {
		String sql = "update orders set state=? where oid=?";
		qr.update(sql, state, oid);
	}
	
	// 修改订单的收货地址
	public void updateAddress(String oid, String address) throws SQLException {
		String sql = "update orders set address=? where oid=?";
		qr.update(sql, address, oid);
	}
	
	public static void main(String[] args) throws SQLException {
		OrderDao dao = new OrderDao();
		Order order = dao.findOrderByOid("35c7fb07aadf4a5da0912af663db951e");
		System.out.println(order.getOrderItems());
	}
}
