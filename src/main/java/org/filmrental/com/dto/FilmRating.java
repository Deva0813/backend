package org.filmrental.com.dto;

public class FilmRating {
	Short filmId;
	String rating;
	public FilmRating(Short filmId, String rating) {
		super();
		this.filmId = filmId;
		this.rating = rating;
	}
	public Short getFilmId() {
		return filmId;
	}
	public void setFilmId(Short filmId) {
		this.filmId = filmId;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public FilmRating() {
		super();
	}
	
}
