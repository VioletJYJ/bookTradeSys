package cn.itcast.bookstore.cart.domain;
/**
* 购物车类
*/
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.itcast.bookstore.book.domain.Book;

public class Cart {
	private Map<String,CartItem> map = new LinkedHashMap<String,CartItem>(); //图书
	private Double price = 0.0;// 购物车总计价格
	
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	// 添加图书
	public void add(CartItem ci) {
		String bid = ci.getBid();
		CartItem _ci = map.get(bid);
		if(_ci != null) {
			_ci.setCount(_ci.getCount() + ci.getCount());
			map.put(bid, _ci);
			this.price = _ci.getPrice();
		} else {
			map.put(bid, ci);
			this.price += ci.getPrice();
		}
	}
	
	 // 删除图书
	public void del(String bid) {
		CartItem ci = map.remove(bid);
		this.price -= ci.getPrice();
	}
	
	// 返回购物车中所有图书信息
	public Collection<CartItem> getAll() {
		return map.values();
	}
	
	// 查找图书信息
	public CartItem findById(String bid) {
		return map.get(bid);
	}
	
	// 清空购物车
	public void clear() {
		map.clear();
		this.price = 0.0;
	}
}
