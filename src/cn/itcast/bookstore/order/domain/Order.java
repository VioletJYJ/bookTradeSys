package cn.itcast.bookstore.order.domain;
/**
*订单类
*/
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import cn.itcast.bookstore.user.domain.User;

public class Order {
	private String oid;//订单编号
	private Date ordertime;//下单时间
	private Double price = 0.0;//订单总计价格
	private int state;//订单状态
	private User user;//用户
	private String address;//地址
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	private Set<OrderItem> orderItems = new HashSet<OrderItem>();
	
	public void addOrderItem(OrderItem oi) {
		orderItems.add(oi);
	}
	
	public void removeOrderItem(OrderItem oi) {
		orderItems.remove(oi);
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", price="
				+ price + ", state=" + state + ", user=" + user + "]";
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((oid == null) ? 0 : oid.hashCode());
		result = prime * result
				+ ((orderItems == null) ? 0 : orderItems.hashCode());
		result = prime * result
				+ ((ordertime == null) ? 0 : ordertime.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + state;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (oid == null) {
			if (other.oid != null)
				return false;
		} else if (!oid.equals(other.oid))
			return false;
		if (orderItems == null) {
			if (other.orderItems != null)
				return false;
		} else if (!orderItems.equals(other.orderItems))
			return false;
		if (ordertime == null) {
			if (other.ordertime != null)
				return false;
		} else if (!ordertime.equals(other.ordertime))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (state != other.state)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
