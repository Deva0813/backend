package org.filmrental.com.dto;

public class FilmCategoryRequestBody {
	Short filmId;
	String categoryName;

	public FilmCategoryRequestBody(Short filmId, String categoryName) {
		super();
		this.filmId = filmId;
		this.categoryName = categoryName;
	}
	public Short getFilmId() {
		return filmId;
	}
	public void setFilmId(Short filmId) {
		this.filmId = filmId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public FilmCategoryRequestBody() {
		super();
	}
	
}
