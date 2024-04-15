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
import org.filmrental.com.service.ILanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/language")
@RestController
public class LanguageRestController {
	@Autowired
	private ILanguageService languageService;
	
	@PostMapping("/add")
	public ResponseEntity<String> addLanguage(@RequestBody Language language){
		Language language1 = languageService.addLanguageDetails(language);
		if(language1 == null || language1.getLanguageId()==0) {
			throw new PostException("Error in adding language Details");
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
		}
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Language>> getAllLanguage(){
		List<Language> language = languageService.getAllLanguage();
		if(language.isEmpty() || language == null) {
			throw new DataNotFoundException("No Language Found");
		}else {
			return new ResponseEntity<List<Language>>(language, HttpStatus.OK);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Language> getLanguageById(@PathVariable("id") Byte id){
		Language language = languageService.getLanguageById(id);
		if(language == null) {
			throw new DataNotFoundException("No Language found for the id "+id);
		}else {
			return new ResponseEntity<Language>(language, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteLanguageById(@PathVariable("id") Byte id){
		if(languageService.getLanguageById(id)!=null) {
			Boolean isDeteled = languageService.deleteLanguage(id);
			if(isDeteled == false) {
				throw new DeletionException("Language cannot be deleted");
			}else {
				return ResponseEntity.status(HttpStatus.OK).body("Record Deleted Successfully");
			}
		}else {
			throw new IdNotFoundException("Language Id not found");
		}
		
	}
	
	@PutMapping("/update")
	public ResponseEntity<Language> updateLanguage(@RequestBody Language language){
		Language language1 = languageService.updateLanguage(language);
		if(language1 == null) {
			throw new UpdationErrorException("Error while Updation");
		}else {
			return new ResponseEntity<Language>(language1, HttpStatus.OK);
		}
	}
}
