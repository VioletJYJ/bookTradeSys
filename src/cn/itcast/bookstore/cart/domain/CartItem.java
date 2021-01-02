package cn.itcast.bookstore.cart.domain;
/**
*购物车条目
*/
import cn.itcast.bookstore.book.domain.Book;

public class CartItem {
	private Book book;//图书
	private Integer count = 0;//数量
	private Double price = 0.0;//价格
	
	public CartItem() {
		
	}
	
	public CartItem(Book book, Integer count) {
		this.book = book;
		this.count = count;
		price = book.getPrice() * count;
	}
	
	public String getBid() {
		return book.getBid();
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
		this.price = book.getPrice() * this.count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String toString() {
		return "CartItem [count=" + count + ", price=" + price + "]";
	}
}
