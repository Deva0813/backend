package org.filmrental.com.controller;


import java.util.List;

import org.filmrental.com.exception.DataNotFoundException;
import org.filmrental.com.exception.DeletionException;
import org.filmrental.com.exception.IdNotFoundException;
import org.filmrental.com.exception.PostException;
import org.filmrental.com.exception.UpdationErrorException;
import org.filmrental.com.model.Actor;
import org.filmrental.com.model.Category;
import org.filmrental.com.model.Language;
import org.filmrental.com.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;
@RequestMapping("/api/category")
@RestController
public class CategoryRestController {
	
	@Autowired
	ICategoryService categoryService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addCategory(@RequestBody Category category){
		Category category1 = categoryService.addCategoryDetails(category);
		if(category1 == null || category1.getCategoryId()==0) {
			throw new PostException("Error in adding Category Details");
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
		}
	}
	@GetMapping("/")
	public ResponseEntity<List<Category>> getAllCategories(){
		List<Category> categories = categoryService.getAllCategory();
		if(categories.isEmpty() || categories == null) {
			throw new DataNotFoundException("No Categories Found");
		}else {
			return new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("id") Byte id){
		Category category = categoryService.getCategoryById(id);
		if(category == null) {
			throw new DataNotFoundException("No Category found for the id "+id);
		}else {
			return new ResponseEntity<Category>(category, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCategoryById(@PathVariable("id") Byte id){
		if(categoryService.getCategoryById(id)!=null) {
			Boolean isDeteled = categoryService.deleteCategory(id);
			if(isDeteled == false) {
				throw new DeletionException("Category cannot be deleted");
			}else {
				return ResponseEntity.status(HttpStatus.OK).body("Record Deleted Successfully");
			}
		}else {
			throw new IdNotFoundException("Category Id not found");
		}
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Category> updateCategory(@RequestBody Category category){
		Category category1 = categoryService.updateCategory(category);
		if(category1 == null) {
			throw new UpdationErrorException("Error while Updation");
		}else {
			return new ResponseEntity<Category>(category1, HttpStatus.OK);
		}
	}
	
	
	
	
}
