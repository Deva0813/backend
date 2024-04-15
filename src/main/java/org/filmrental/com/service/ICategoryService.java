package org.filmrental.com.service;

import java.util.List;


import org.filmrental.com.model.Category;
import org.springframework.stereotype.Service;


public interface ICategoryService {
	public Category addCategoryDetails(Category category);
	public List<Category> getAllCategory();
	public Category getCategoryById(Byte categoryId);
	public boolean deleteCategory(Byte categoryId);
	public Category updateCategory(Category category); 
}
