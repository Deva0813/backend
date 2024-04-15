package org.filmrental.com.dao;

import java.math.BigDecimal;
import java.util.List;

import org.filmrental.com.dto.ActorDetails;
import org.filmrental.com.dto.CountFilms;
import org.filmrental.com.model.Actor;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.MovieRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IFilmDao extends CrudRepository<Film, Short>  {
	public List<Film> findByTitleContaining(String title);
	public List<Film> findByReleaseYear(int year);
	public List<Film> findFilmsByRentalDurationGreaterThan(int rentalDuration);
	public List<Film> findFilmsByRentalRateGreaterThan(double rentalRate);
	public List<Film> findFilmsByLengthGreaterThan(int length);
	public List<Film> findFilmsByRentalDurationLessThan(int rentalDuration);
	public List<Film> findFilmsByRentalRateLessThan(double rentalRate);
	public List<Film> findFilmsByLengthLessThan(int length);
	public List<Film> findFilmsByReleaseYearBetween(int fromYear, int toYear);
	public List<Film> findFilmsByOriginalLanguageName(String language);
	public List<Film> findByRatingGreaterThan(MovieRating rating);
	public List<Film> findByRatingIn(List<MovieRating> filteredRatings);
	public Film getByFilmId(Short filmId);
	
	@Query("SELECT new org.filmrental.com.dto.CountFilms(f.releaseYear, COUNT(f.filmId)) FROM Film f GROUP BY f.releaseYear")
	public List<CountFilms> findCountOfFilmsByYear();

	
	@Query("SELECT new org.filmrental.com.dto.ActorDetails(concat(fa.actor.firstName,' ', fa.actor.lastName)) FROM Film f JOIN f.filmActor fa WHERE f.filmId = :filmId")
	public List<ActorDetails> findActorsByFilmId(@Param("filmId") Short filmId);
	
	@Query("SELECT f FROM Film f JOIN f.filmCategory.category c WHERE c.name=:categoryName")
	public List<Film> findFilmByCategory(@Param("categoryName") String categoryName);
	
	
//	@Query("UPDATE Film f SET f.language.name=:language WHERE f.filmId=:filmId")
//	public Film updateFilmLanguage(@Param("language") String language,@Param("filmId") int filmId);


}
