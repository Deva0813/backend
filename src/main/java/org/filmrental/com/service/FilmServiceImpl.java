package org.filmrental.com.service;

import java.math.BigDecimal;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.filmrental.com.dao.ICategoryDao;
import org.filmrental.com.dao.IFilmCategoryDao;
import org.filmrental.com.dao.IFilmDao;
import org.filmrental.com.dao.ILanguageDao;
import org.filmrental.com.dto.ActorDetails;
import org.filmrental.com.dto.CountFilms;
import org.filmrental.com.exception.DataNotFoundException;
import org.filmrental.com.exception.IdNotFoundException;
import org.filmrental.com.exception.InvalidCategoryException;
import org.filmrental.com.exception.InvalidLanguageException;
import org.filmrental.com.exception.InvalidRatingException;
import org.filmrental.com.model.Category;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.FilmCategory;
import org.filmrental.com.model.Language;
import org.filmrental.com.model.MovieRating;
import org.filmrental.com.utility.MovieRatingValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class FilmServiceImpl implements IFilmService{
	
	@Autowired
	private IFilmDao filmDao;
	
	@Autowired
	private ICategoryDao categoryDao;
	
	@Autowired
	private ILanguageDao languageDao;
	
	@Autowired
	private IFilmCategoryDao filmCategoryDao;
	
	//=======================POST========================
	
	//Adding new film to DB
	@Override
	public Film addFilm(Film film) {
//		Language language = film.getLanguage();
//		if(language.getLanguageId()!=null) {
//			List<FilmCategory> filmCategory = film.getFilmCategory();
//			if(filmCategory != null || !filmCategory.isEmpty()) {
//				for(FilmCategory fc:filmCategory) {
//					Category category = fc.getCategory();
//					if(category.getCategoryId()!=null) {
//						fc.setFilm(film);
//						fc.setCategory(category);
//						fc.setLastUpdate(LocalDateTime.now());
//						filmCategoryDao.save(fc);
//						
//					}else {
//						throw new InvalidCategoryException("Invalid category given");
//					}
//				}
//			//System.out.println(filmCategory);
//			}else {
//				throw new DataNotFoundException("null category");
//			}
//		}
		Language language = film.getLanguage();
		Language originalLanguage = film.getOriginalLanguage();

		if (language != null && originalLanguage != null &&
		    language.getName() != null && originalLanguage.getName() != null) {
		    
		    Language foundLanguage = languageDao.findByName(language.getName());
		    Language foundOriginalLanguage = languageDao.findByName(originalLanguage.getName());
		    
		    if (foundLanguage != null && foundOriginalLanguage != null) {
		        film.setLanguage(foundLanguage);
		        film.setOriginalLanguage(foundOriginalLanguage);
		        return filmDao.save(film);
		    } else {
		        throw new InvalidLanguageException("Language or original language not found");
		    }
		} else {
		    throw new InvalidLanguageException("Invalid or missing language information in the film data");
		}

	}
	
	//=======================GET========================
	
	//Get All Films in DB
	@Override
	public List<Film> getAllFilms() {
		return (List<Film>) filmDao.findAll();
	}
	
	//listing the film that contains the given title
	@Override
	public List<Film> findByTitleContaining(String title) {
		 List<Film> list = filmDao.findByTitleContaining(title);
		 return list;
	}

	//listing films based on release year
	@Override
	public List<Film> findByReleaseYear(int year) {
		List<Film> list = filmDao.findByReleaseYear(year);
		return list;
	}
	
	//listing films based on rental duration which are greater than given duration
	@Override
	public List<Film> findFilmsByRentalDurationGreaterThan(int rentalDuration) {
		List<Film> list = filmDao.findFilmsByRentalDurationGreaterThan(rentalDuration);
		return list;
	}
	
	//listing films based on release rate which are greater than given rate
	@Override
	public List<Film> findFilmsByRentalRateGreaterThan(double rentalRate) {
		List<Film> list = filmDao.findFilmsByRentalRateGreaterThan(rentalRate);
		return list;
	}
	
	//listing films based on length which are greater than given length
	@Override
	public List<Film> findFilmsByLengthGreaterThan(int length) {
		List<Film> list = filmDao.findFilmsByLengthGreaterThan(length);
		return list;
	}
	
	//listing films based on rental duration which are greater than given duration
	@Override
	public List<Film> findFilmsByRentalDurationLessThan(int rentalDuration) {
		List<Film> list = filmDao.findFilmsByRentalDurationLessThan(rentalDuration);
		return list;
	}
	
	//listing films based on release rate which are less than given rate
	@Override
	public List<Film> findFilmsByRentalRateLessThan(double rentalRate) {
		List<Film> list = filmDao.findFilmsByRentalRateLessThan(rentalRate);
		return list;
	}
	
	//listing films based on length which are less than given rate
	@Override
	public List<Film> findFilmsByLengthLessThan(int length) {
		List<Film> list = filmDao.findFilmsByLengthLessThan(length);
		return list;
	}
	
	//listing films between given from and to year
	@Override
	public List<Film> findFilmsByReleaseYearBetween(int fromYear, int toYear) {
		List<Film> list = filmDao.findFilmsByReleaseYearBetween(fromYear, toYear);
		return list;
	}
	
	//listing film based on give language
	@Override
	public List<Film> findFilmsByLanguageName(String language) {
		List<Film> list = filmDao.findFilmsByOriginalLanguageName(language);
		return list;
	}

	//listing the count of films in each year
	@Override
	public List<CountFilms> findCountOfFilmsByYear() {
		return filmDao.findCountOfFilmsByYear();
	}
	
	//listing actors by given film id
	@Override
	public List<ActorDetails> findActorsByFilmId(Short filmId) {
		return filmDao.findActorsByFilmId(filmId);
	}
	
	//listing films based on given film category
	@Override
	public List<Film> findFilmByCategory(String categoryName) {
		return filmDao.findFilmByCategory(categoryName);
	}
	
	//get film by film id==>for CRUD purspose(not an endpoint)
	@Override
	public Film getFilmById(Short filmId) {
		Optional<Film> optional=filmDao.findById(filmId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}
	
	//listing films which are greater than given rating
	@Override
	public List<Film> findByRatingGreaterThan(MovieRating rating) {
		int providedOrdinal = rating.ordinal();//ordinal provides index number of enum values from 0-n..in this case 0-4
        List<MovieRating> filteredRatings = new ArrayList<>();
        for (MovieRating enumValue : MovieRating.values()) {
            if (enumValue.ordinal() > providedOrdinal) {
                filteredRatings.add(enumValue);
            }
        }
        List<Film> filteredFilms = filmDao.findByRatingIn(filteredRatings);
        return filteredFilms;		
	}
	
	//listing films which are less than given rating
	@Override
	public List<Film> findByRatingLessThan(MovieRating rating) {
		int providedOrdinal = rating.ordinal();//ordinal provides index number of enum values from 0-n..in this case 0-4
        List<MovieRating> filteredRatings = new ArrayList<>();
        for (MovieRating enumValue : MovieRating.values()) {
            if (enumValue.ordinal() < providedOrdinal) {
                filteredRatings.add(enumValue);
            }
        }
        List<Film> filteredFilms = filmDao.findByRatingIn(filteredRatings);
        return filteredFilms;
	}
	
	
	//=======================UPDATE========================
	
	//updating the film title
	@Transactional
	@Override
	public Film updateFilmTitle(String title,Short filmId) {
		Film film1 = getFilmById(filmId);
		if(film1 != null) {
			film1.setTitle(title);
			film1.setLastUpdate(LocalDateTime.now());
		}
		return film1;
	}
	
	//updating the film release year
	@Transactional
	@Override
	public Film updateFilmReleaseYear(int releaseYear, Short filmId) {
		Film film1 = getFilmById(filmId);
		if(film1!=null) {
			film1.setReleaseYear(releaseYear);
			film1.setLastUpdate(LocalDateTime.now());
		}
		return film1;
	}
	
	//updating the film rental duration
	@Transactional
	@Override
	public Film updateFilmRentalDuration(Byte rentalDuration, Short filmId) {
		Film film1 = getFilmById(filmId);
		if(film1!=null) {
			film1.setRentalDuration(rentalDuration);
			film1.setLastUpdate(LocalDateTime.now());
		}
		return film1;
	}
	
	//updating the film rental rate
	@Transactional
	@Override
	public Film updateFilmRentalRate(BigDecimal rentalRate, Short filmId) {
		Film film1 = getFilmById(filmId);
		if(film1!=null) {
			film1.setRentalRate(rentalRate);
			film1.setLastUpdate(LocalDateTime.now());
		}
		return film1;
	}

	//updating the film language
	@Transactional
	@Override
	public Film updateLanguageOfFilm(String languageName, Short filmId) {
		Film film1 = filmDao.getByFilmId(filmId);
		Language language = languageDao.findByName(languageName);//checking whether the language is available in language table

		if(language != null) {
	        film1.setOriginalLanguage(language);
	        return film1; 
	    }else {
	        throw new InvalidLanguageException("Invalid Language");
	    }
	}
	
	//updating the film category
	@Transactional
	@Override
    public FilmCategory updateOrAddCategory(Short filmId, String categoryName) {
		Optional<Film> optionalFilm = filmDao.findById(filmId);
	    if (optionalFilm.isEmpty()) {
	        throw new IdNotFoundException("Film ID Not Found");
	    }

	    Film film = optionalFilm.get();
	    Category categoryObj = categoryDao.findByName(categoryName);//checking whether the category is available in category table
	    if (categoryObj == null) {
	        throw new InvalidCategoryException("Invalid category");
	    }

	    FilmCategory filmCategory = filmCategoryDao.findByFilm(film);
	    if (filmCategory == null || filmCategory.getFilm()==null) {
	    	//it will add new row in film category since the film id is present in film table but not in film category table
	        filmCategory = new FilmCategory();
	        filmCategory.setFilm(film);
	        filmCategory.setCategory(categoryObj);
	        filmCategory = filmCategoryDao.save(filmCategory);
	    } else {
	    	//deleting the existing object and adding with new category 
	    	filmCategoryDao.delete(filmCategory);
	    	filmCategory = new FilmCategory();
	    	filmCategory.setFilm(film);
	    	filmCategory.setCategory(categoryObj);
	    	filmCategory.setLastUpdate(LocalDateTime.now());
	    	filmCategory = filmCategoryDao.save(filmCategory);
	    }
	    return filmCategory;
	}
	
	//updating the film rating
	@Transactional
	@Override
	public Film updateRatingOfFilm(String rating, Short filmId) {
		Film film = getFilmById(filmId);
	    if (film != null) {
	    	//checking whether the given rating is valid or not
	    	if(MovieRatingValidation.isValidRating(rating)) {
	    		film.setRating(MovieRating.valueOf(rating));
	            film.setLastUpdate(LocalDateTime.now());
	            return film;
	    	}else {
	    		throw new InvalidRatingException("Invalid Rating");
	    	}
            
	    } else {
	        throw new IdNotFoundException("Film ID not found");
	    }
        
	}
	

	

}
