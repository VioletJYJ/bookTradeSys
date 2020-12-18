package cn.itcast.bookstore.category.service;

import java.util.List;

import cn.itcast.bookstore.category.dao.CategoryDao;
import cn.itcast.bookstore.category.domain.Category;

public class CategoryService {
	private CategoryDao categoryDao = new CategoryDao();
	
	public List<Category> all() {
		try {
			return categoryDao.all();
		} catch(Exception e) {
			throw new RuntimeException();
		}
	}
}
