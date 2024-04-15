package org.filmrental.com.model;
 
import java.io.Serializable;
 
public class FilmCategoryId implements Serializable {
    private Category category;
    private Film film;
	public FilmCategoryId(Category category, Film film) {
		super();
		this.category = category;
		this.film = film;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public FilmCategoryId() {
		super();
	}
	    
}