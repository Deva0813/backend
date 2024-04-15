package org.filmrental.com.dao;


import org.filmrental.com.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryDao extends CrudRepository<Category, Byte> {
	public Category findByName(String name);
}
