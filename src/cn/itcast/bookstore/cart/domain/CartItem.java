package cn.itcast.bookstore.cart.domain;

import cn.itcast.bookstore.book.domain.Book;

public class CartItem {
	private Book book;
	private Integer count = 0;
	private Double price = 0.0;
	
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
