package org.filmrental.com.dao;

import java.util.List;
import java.util.Optional;

import org.filmrental.com.model.Category;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.FilmCategory;
import org.filmrental.com.model.FilmCategoryId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IFilmCategoryDao extends CrudRepository<FilmCategory, FilmCategoryId> {
	List<FilmCategory> findByFilmFilmId(Short filmId);
	FilmCategory findByFilm(Film film);
	
	
	
	@Query("UPDATE FilmCategory fc set fc.category.categoryId=:categoryId WHERE fc.film.filmId=:filmId")
	void updateExistingCategory(@Param("filmId") Short filmId, @Param("categoryId") Byte categoryId);
}
