package org.filmrental.com.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale.Category;

import org.filmrental.com.dto.ActorDetails;
import org.filmrental.com.dto.CountFilms;
import org.filmrental.com.model.Actor;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.FilmCategory;
import org.filmrental.com.model.Language;
import org.filmrental.com.model.MovieRating;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public interface IFilmService {
	public Film addFilm(Film film);//revise
	public List<Film> getAllFilms();
	public Film getFilmById(Short filmId);
	public List<Film> findByTitleContaining(String title);
	public List<Film> findByReleaseYear(int year);
	public List<Film> findFilmsByRentalDurationGreaterThan(int rentalDuration);
	public List<Film> findFilmsByRentalRateGreaterThan(double rentalRate);
	public List<Film> findFilmsByLengthGreaterThan(int length);
	public List<Film> findFilmsByRentalDurationLessThan(int rentalDuration);
	public List<Film> findFilmsByRentalRateLessThan(double rentalRate);
	public List<Film> findFilmsByLengthLessThan(int length);
	public List<Film> findByRatingGreaterThan(MovieRating rating);//revise
	public List<Film> findByRatingLessThan(MovieRating rating);//revise
	public List<Film> findFilmsByReleaseYearBetween(int fromYear, int toYear);
	public List<Film> findFilmsByLanguageName(String language);
	public List<CountFilms> findCountOfFilmsByYear();
	public List<ActorDetails> findActorsByFilmId(Short filmId);
	public List<Film> findFilmByCategory(String categoryName);
	public Film updateFilmTitle(String title,Short filmId);
	public Film updateFilmReleaseYear(int releaseYear,Short filmId);
	public Film updateFilmRentalDuration(Byte rentalDuration,Short filmId);
	public Film updateFilmRentalRate(BigDecimal rentalRate,Short filmId);
	public Film updateLanguageOfFilm(String language, Short filmId);
	public Film updateRatingOfFilm(String rating,Short filmId);//exception is not working
	public FilmCategory updateOrAddCategory(Short filmId, String categoryName);
}
