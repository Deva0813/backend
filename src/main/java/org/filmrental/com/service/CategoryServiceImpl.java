package org.filmrental.com.service;

import java.util.List;

import org.filmrental.com.dao.ICategoryDao;
import org.filmrental.com.model.Category;
import org.filmrental.com.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {
	
	@Autowired
	private ICategoryDao categoryDao;

	@Override
	public Category addCategoryDetails(Category category) {
		Category cat= categoryDao.save(category);
		return cat;
	}

	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category getCategoryById(Byte categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCategory(Byte categoryId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Category updateCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

}
