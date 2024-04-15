package org.filmrental.com.test;

import org.filmrental.com.dao.IActorDao;
import org.filmrental.com.dao.ICategoryDao;
import org.filmrental.com.dao.IFilmActorDao;
import org.filmrental.com.dao.IFilmCategoryDao;
import org.filmrental.com.dao.IFilmDao;
import org.filmrental.com.dao.ILanguageDao;
import org.filmrental.com.dto.ActorDetails;
import org.filmrental.com.dto.CountFilms;
import org.filmrental.com.exception.InvalidCategoryException;
import org.filmrental.com.model.Actor;
import org.filmrental.com.model.Category;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.FilmActor;
import org.filmrental.com.model.FilmCategory;
import org.filmrental.com.model.Language;
import org.filmrental.com.model.MovieRating;
import org.filmrental.com.service.FilmActorServiceImpl;
import org.filmrental.com.service.FilmServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class FilmControllerTest {
	@Mock
	private IFilmDao filmDao;
	
	@Mock
	private IActorDao actorDao;
	
	@Mock
	private IFilmActorDao filmActorDao;
	
	@Mock
	private ILanguageDao languageDao;
	
	@Mock
	private IFilmCategoryDao filmCategoryDao;
	
	@Mock
	private ICategoryDao categoryDao;
	
	

	@InjectMocks
	private FilmServiceImpl filmServiceImpl;
	
	@InjectMocks
	private FilmActorServiceImpl filmActorServiceImpl;

	@BeforeEach
	void setUp() {
	    MockitoAnnotations.openMocks(this);
	}
	
	//POST TESTING=>add film
	@Test
	void addFilmTest() {
        Film film = new Film();
        film.setTitle("Example Film");
        when(filmDao.save(any(Film.class))).thenReturn(film);
        Film addedFilm = filmServiceImpl.addFilm(film);
        verify(filmDao, times(1)).save(any(Film.class));
        assertEquals("Example Film", addedFilm.getTitle());
	}
	
//	@Test
//	void getAllFilmsTest() {
//		List<Film> films = new ArrayList<>();
//		Film film = new Film();
//        film.setTitle("Example Film");
//        film.setDescription("This is a sample description for the film.");
//        film.setReleaseYear(2022);
//        film.setRentalDuration((byte) 5);
//        film.setLength((short) 120);
//        film.setRentalRate(BigDecimal.valueOf(3.99));
//        film.setReplacementCost(BigDecimal.valueOf(19.99));
//        film.setRating(MovieRating.PG_13);
//        film.setSpecialFeatures("Trailers, Deleted Scenes");
//        film.setLanguage(null);
//        film.setOriginalLanguage(null);
//        films.add(film);
//        when(filmDao.findAll()).thenReturn(films);
//        List<Film> getFilms = filmServiceImpl.getAllFilms();
//        assertEquals(1, getFilms.size());
//	}
	
	//Get Film by title
	@Test
	void searchFilmByTitle() {
		List<Film> films = new ArrayList<>();
		Film film1 = new Film();
        film1.setTitle("Conjuring 1");
        film1.setDescription("This is a sample description for this film.");
        film1.setReleaseYear(2022);
        film1.setRentalDuration((byte) 5);
        film1.setLength((short) 120);
        film1.setRentalRate(BigDecimal.valueOf(3.99));
        film1.setReplacementCost(BigDecimal.valueOf(19.99));
        film1.setRating(MovieRating.PG_13);
        film1.setSpecialFeatures("Trailers, Deleted Scenes");
        film1.setLanguage(null);
        film1.setOriginalLanguage(null);
        
        Film film2 = new Film();
        film2.setTitle("Conjuring 2");
        film2.setDescription("This is a sample description for the film.");
        film2.setReleaseYear(2019);
        film2.setRentalDuration((byte) 6);
        film2.setLength((short) 130);
        film2.setRentalRate(BigDecimal.valueOf(4.99));
        film2.setReplacementCost(BigDecimal.valueOf(27.99));
        film2.setRating(MovieRating.G);
        film2.setSpecialFeatures("Trailers");
        film2.setLanguage(null);
        film2.setOriginalLanguage(null);
        
        films.add(film1);
        films.add(film2);
        //when(filmDao.findByTitleContaining("Conjuring")).thenReturn(Collections.singletonList(film1));
        when(filmDao.findByTitleContaining("Conjuring")).thenReturn(films);
        List<Film> foundFilms = filmServiceImpl.findByTitleContaining("Conjuring");
        verify(filmDao, times(1)).findByTitleContaining("Conjuring");
        assertEquals(2, foundFilms.size());
        assertEquals(films, foundFilms);
	}
	
	//Get Film by realse year
	@Test
	void searchFilmByRelaeseYearTest() {
		List<Film> films = new ArrayList<>();
		Film film1 = new Film();
        film1.setTitle("Conjuring");
        film1.setDescription("This is a sample description for this film.");
        film1.setReleaseYear(2022);
        film1.setRentalDuration((byte) 5);
        film1.setLength((short) 120);
        film1.setRentalRate(BigDecimal.valueOf(3.99));
        film1.setReplacementCost(BigDecimal.valueOf(19.99));
        film1.setRating(MovieRating.PG_13);
        film1.setSpecialFeatures("Trailers, Deleted Scenes");
        film1.setLanguage(null);
        film1.setOriginalLanguage(null);
        
        Film film2 = new Film();
        film2.setTitle("Ring");
        film2.setDescription("This is a sample description for the film.");
        film2.setReleaseYear(2019);
        film2.setRentalDuration((byte) 6);
        film2.setLength((short) 130);
        film2.setRentalRate(BigDecimal.valueOf(4.99));
        film2.setReplacementCost(BigDecimal.valueOf(27.99));
        film2.setRating(MovieRating.G);
        film2.setSpecialFeatures("Trailers");
        film2.setLanguage(null);
        film2.setOriginalLanguage(null);
        
        films.add(film1);
        films.add(film2);
        when(filmDao.findByReleaseYear(2022)).thenReturn(Collections.singletonList(film1));
        List<Film> foundFilms = filmServiceImpl.findByReleaseYear(2022);
        verify(filmDao, times(1)).findByReleaseYear(2022);
        assertEquals(1, foundFilms.size());
        assertEquals(film1, foundFilms.get(0));
	}
	
	//Get films greater than rental duration
	@Test
	void searchFilmByGreaterThanRentalDurationTest() {
		List<Film> films = new ArrayList<>();
		Film film1 = new Film();
        film1.setTitle("Conjuring");
        film1.setDescription("This is a sample description for this film.");
        film1.setReleaseYear(2022);
        film1.setRentalDuration((byte) 3);
        film1.setLength((short) 120);
        film1.setRentalRate(BigDecimal.valueOf(3.99));
        film1.setReplacementCost(BigDecimal.valueOf(19.99));
        film1.setRating(MovieRating.PG_13);
        film1.setSpecialFeatures("Trailers, Deleted Scenes");
        film1.setLanguage(null);
        film1.setOriginalLanguage(null);
        
        Film film2 = new Film();
        film2.setTitle("Ring");
        film2.setDescription("This is a sample description for the film.");
        film2.setReleaseYear(2019);
        film2.setRentalDuration((byte) 6);
        film2.setLength((short) 130);
        film2.setRentalRate(BigDecimal.valueOf(4.99));
        film2.setReplacementCost(BigDecimal.valueOf(27.99));
        film2.setRating(MovieRating.G);
        film2.setSpecialFeatures("Trailers");
        film2.setLanguage(null);
        film2.setOriginalLanguage(null);
        
        
        films.add(film1);
        films.add(film2);
        when(filmDao.findFilmsByRentalDurationGreaterThan(5)).thenReturn(Collections.singletonList(film2));
        List<Film> foundFilms = filmServiceImpl.findFilmsByRentalDurationGreaterThan(5);
        //System.out.println(foundFilms);
        verify(filmDao, times(1)).findFilmsByRentalDurationGreaterThan(5);
        assertEquals(1, foundFilms.size());
        assertEquals(film2, foundFilms.get(0));
	}
	
	
	//Get Films greater than rental rate
	@Test
	void searchFilmByGreaterThanRentalRateTest() {
	    List<Film> films = new ArrayList<>();
	    
	    Film film1 = new Film();
	    film1.setFilmId((short) 7);
	    film1.setTitle("Conjuring");
	    film1.setDescription("This is a sample description for this film.");
	    film1.setReleaseYear(2022);
	    film1.setRentalDuration((byte) 3);
	    film1.setLength((short) 120);
	    film1.setRentalRate(BigDecimal.valueOf(3.99));
	    film1.setReplacementCost(BigDecimal.valueOf(19.99));
	    film1.setRating(MovieRating.PG_13);
	    film1.setSpecialFeatures("Trailers, Deleted Scenes");
	    film1.setLanguage(null);
	    film1.setOriginalLanguage(null);
	    
	    Film film2 = new Film();
	    film2.setFilmId((short) 8);
	    film2.setTitle("Ring");
	    film2.setDescription("This is a sample description for the film.");
	    film2.setReleaseYear(2019);
	    film2.setRentalDuration((byte) 6);
	    film2.setLength((short) 130);
	    film2.setRentalRate(BigDecimal.valueOf(6.99));
	    film2.setReplacementCost(BigDecimal.valueOf(27.99));
	    film2.setRating(MovieRating.G);
	    film2.setSpecialFeatures("Trailers");
	    film2.setLanguage(null);
	    film2.setOriginalLanguage(null);
	    
	    films.add(film1);
	    films.add(film2);
	    
	    when(filmDao.findFilmsByRentalRateGreaterThan(5)).thenReturn(Collections.singletonList(film2));
	    
	    List<Film> foundFilms = filmServiceImpl.findFilmsByRentalRateGreaterThan(5);
	    //System.out.println(foundFilms.size());
	    verify(filmDao, times(1)).findFilmsByRentalRateGreaterThan(5);
	    assertEquals(1, foundFilms.size());
	    assertEquals(film2, foundFilms.get(0));
	}
	
	
	//Get films greater than length
	@Test
	void searchFilmByGreaterThanLengthTest() {
	    List<Film> films = new ArrayList<>();
	    
	    Film film1 = new Film();
	    film1.setFilmId((short) 7);
	    film1.setTitle("Conjuring");
	    film1.setDescription("This is a sample description for this film.");
	    film1.setReleaseYear(2022);
	    film1.setRentalDuration((byte) 3);
	    film1.setLength((short) 120);
	    film1.setRentalRate(BigDecimal.valueOf(3.99));
	    film1.setReplacementCost(BigDecimal.valueOf(19.99));
	    film1.setRating(MovieRating.PG_13);
	    film1.setSpecialFeatures("Trailers, Deleted Scenes");
	    film1.setLanguage(null);
	    film1.setOriginalLanguage(null);
	    
	    Film film2 = new Film();
	    film2.setFilmId((short) 8);
	    film2.setTitle("Ring");
	    film2.setDescription("This is a sample description for the film.");
	    film2.setReleaseYear(2019);
	    film2.setRentalDuration((byte) 6);
	    film2.setLength((short) 90);
	    film2.setRentalRate(BigDecimal.valueOf(6.99));
	    film2.setReplacementCost(BigDecimal.valueOf(27.99));
	    film2.setRating(MovieRating.G);
	    film2.setSpecialFeatures("Trailers");
	    film2.setLanguage(null);
	    film2.setOriginalLanguage(null);
	    
	    films.add(film1);
	    films.add(film2);
	    
	    when(filmDao.findFilmsByLengthGreaterThan(110)).thenReturn(Collections.singletonList(film1));
	    
	    List<Film> foundFilms = filmServiceImpl.findFilmsByLengthGreaterThan(110);
	    //System.out.println(foundFilms.size());
	    verify(filmDao, times(1)).findFilmsByLengthGreaterThan(110);
	    assertEquals(1, foundFilms.size());
	    assertEquals(film1, foundFilms.get(0));
	}
	
	
	//get films less than rental duration
	@Test
	void searchFilmByLessThanRentalDurationTest() {
	    List<Film> films = new ArrayList<>();
	    
	    Film film1 = new Film();
	    film1.setFilmId((short) 7);
	    film1.setTitle("Conjuring");
	    film1.setDescription("This is a sample description for this film.");
	    film1.setReleaseYear(2022);
	    film1.setRentalDuration((byte) 3);
	    film1.setLength((short) 120);
	    film1.setRentalRate(BigDecimal.valueOf(3.99));
	    film1.setReplacementCost(BigDecimal.valueOf(19.99));
	    film1.setRating(MovieRating.PG_13);
	    film1.setSpecialFeatures("Trailers, Deleted Scenes");
	    film1.setLanguage(null);
	    film1.setOriginalLanguage(null);
	    
	    Film film2 = new Film();
	    film2.setFilmId((short) 8);
	    film2.setTitle("Ring");
	    film2.setDescription("This is a sample description for the film.");
	    film2.setReleaseYear(2019);
	    film2.setRentalDuration((byte) 6);
	    film2.setLength((short) 90);
	    film2.setRentalRate(BigDecimal.valueOf(6.99));
	    film2.setReplacementCost(BigDecimal.valueOf(27.99));
	    film2.setRating(MovieRating.G);
	    film2.setSpecialFeatures("Trailers");
	    film2.setLanguage(null);
	    film2.setOriginalLanguage(null);
	    
	    films.add(film1);
	    films.add(film2);
	    
	    when(filmDao.findFilmsByRentalDurationLessThan(4)).thenReturn(Collections.singletonList(film1));
	    
	    List<Film> foundFilms = filmServiceImpl.findFilmsByRentalDurationLessThan(4);
	    //System.out.println(foundFilms.size());
	    verify(filmDao, times(1)).findFilmsByRentalDurationLessThan(4);
	    assertEquals(1, foundFilms.size());
	    assertEquals(film1, foundFilms.get(0));
	}
	
	//get films less than rental rate
	@Test
	void searchFilmByLessThanRentalRateTest() {
	    List<Film> films = new ArrayList<>();
	    
	    Film film1 = new Film();
	    film1.setFilmId((short) 7);
	    film1.setTitle("Conjuring");
	    film1.setDescription("This is a sample description for this film.");
	    film1.setReleaseYear(2022);
	    film1.setRentalDuration((byte) 3);
	    film1.setLength((short) 120);
	    film1.setRentalRate(BigDecimal.valueOf(3.99));
	    film1.setReplacementCost(BigDecimal.valueOf(19.99));
	    film1.setRating(MovieRating.PG_13);
	    film1.setSpecialFeatures("Trailers, Deleted Scenes");
	    film1.setLanguage(null);
	    film1.setOriginalLanguage(null);
	    
	    Film film2 = new Film();
	    film2.setFilmId((short) 8);
	    film2.setTitle("Ring");
	    film2.setDescription("This is a sample description for the film.");
	    film2.setReleaseYear(2019);
	    film2.setRentalDuration((byte) 6);
	    film2.setLength((short) 130);
	    film2.setRentalRate(BigDecimal.valueOf(6.99));
	    film2.setReplacementCost(BigDecimal.valueOf(27.99));
	    film2.setRating(MovieRating.G);
	    film2.setSpecialFeatures("Trailers");
	    film2.setLanguage(null);
	    film2.setOriginalLanguage(null);
	    
	    films.add(film1);
	    films.add(film2);
	    
	    when(filmDao.findFilmsByRentalRateLessThan(4.55)).thenReturn(Collections.singletonList(film1));
	    
	    List<Film> foundFilms = filmServiceImpl.findFilmsByRentalRateLessThan(4.55);
	    //System.out.println(foundFilms.size());
	    verify(filmDao, times(1)).findFilmsByRentalRateLessThan(4.55);
	    assertEquals(1, foundFilms.size());
	    assertEquals(film1, foundFilms.get(0));
	}
	
	//get films less than length
	@Test
	void searchFilmByLessThanLengthTest() {
	    List<Film> films = new ArrayList<>();
	    
	    Film film1 = new Film();
	    film1.setFilmId((short) 7);
	    film1.setTitle("Conjuring");
	    film1.setDescription("This is a sample description for this film.");
	    film1.setReleaseYear(2022);
	    film1.setRentalDuration((byte) 3);
	    film1.setLength((short) 120);
	    film1.setRentalRate(BigDecimal.valueOf(3.99));
	    film1.setReplacementCost(BigDecimal.valueOf(19.99));
	    film1.setRating(MovieRating.PG_13);
	    film1.setSpecialFeatures("Trailers, Deleted Scenes");
	    film1.setLanguage(null);
	    film1.setOriginalLanguage(null);
	    
	    Film film2 = new Film();
	    film2.setFilmId((short) 8);
	    film2.setTitle("Ring");
	    film2.setDescription("This is a sample description for the film.");
	    film2.setReleaseYear(2019);
	    film2.setRentalDuration((byte) 6);
	    film2.setLength((short) 90);
	    film2.setRentalRate(BigDecimal.valueOf(6.99));
	    film2.setReplacementCost(BigDecimal.valueOf(27.99));
	    film2.setRating(MovieRating.G);
	    film2.setSpecialFeatures("Trailers");
	    film2.setLanguage(null);
	    film2.setOriginalLanguage(null);
	    
	    films.add(film1);
	    films.add(film2);
	    
	    when(filmDao.findFilmsByLengthLessThan(100)).thenReturn(Collections.singletonList(film1));
	    
	    List<Film> foundFilms = filmServiceImpl.findFilmsByLengthLessThan(100);
	    //System.out.println(foundFilms.size());
	    verify(filmDao, times(1)).findFilmsByLengthLessThan(100);
	    assertEquals(1, foundFilms.size());
	    assertEquals(film1, foundFilms.get(0));
	}
	
	//get films by release year between
	@Test
	void searchFilmByReleaseYearBetweenTest() {
	    List<Film> films = new ArrayList<>();
	    
	    Film film1 = new Film();
	    film1.setFilmId((short) 7);
	    film1.setTitle("Conjuring");
	    film1.setDescription("This is a sample description for this film.");
	    film1.setReleaseYear(2022);
	    film1.setRentalDuration((byte) 3);
	    film1.setLength((short) 120);
	    film1.setRentalRate(BigDecimal.valueOf(3.99));
	    film1.setReplacementCost(BigDecimal.valueOf(19.99));
	    film1.setRating(MovieRating.PG_13);
	    film1.setSpecialFeatures("Trailers, Deleted Scenes");
	    film1.setLanguage(null);
	    film1.setOriginalLanguage(null);
	    
	    Film film2 = new Film();
	    film2.setFilmId((short) 8);
	    film2.setTitle("Grudge");
	    film2.setDescription("This is a sample description for the film.");
	    film2.setReleaseYear(2019);
	    film2.setRentalDuration((byte) 6);
	    film2.setLength((short) 90);
	    film2.setRentalRate(BigDecimal.valueOf(6.99));
	    film2.setReplacementCost(BigDecimal.valueOf(27.99));
	    film2.setRating(MovieRating.G);
	    film2.setSpecialFeatures("Trailers");
	    film2.setLanguage(null);
	    film2.setOriginalLanguage(null);
	    
	    Film film3 = new Film();
	    film3.setFilmId((short) 8);
	    film3.setTitle("Ring");
	    film3.setDescription("This is a sample description for the film.");
	    film3.setReleaseYear(2020);
	    film3.setRentalDuration((byte) 4);
	    film3.setLength((short) 110);
	    film3.setRentalRate(BigDecimal.valueOf(5.99));
	    film3.setReplacementCost(BigDecimal.valueOf(24.99));
	    film3.setRating(MovieRating.G);
	    film3.setSpecialFeatures("Trailers");
	    film3.setLanguage(null);
	    film3.setOriginalLanguage(null);
	    
	    films.add(film1);
	    films.add(film2);
	    films.add(film3);
	    
	    List<Film> expectedList = new ArrayList<>();
	    expectedList.add(film2);
	    expectedList.add(film3);
	    when(filmDao.findFilmsByReleaseYearBetween(2018, 2021)).thenReturn(expectedList);
	    //when(filmDao.findFilmsByReleaseYearBetween(2018, 2021)).thenReturn(Collections.singletonList(film3));
	    
	    List<Film> foundFilms = filmServiceImpl.findFilmsByReleaseYearBetween(2018, 2021);
	    //System.out.println(foundFilms.size());
	    verify(filmDao, times(1)).findFilmsByReleaseYearBetween(2018, 2021);
	    assertEquals(2, foundFilms.size());
	    assertEquals(expectedList, foundFilms);
	}
	
	//display year and count of films
	@Test
	void displayingFilmsCountByReleaseYear() {
		Film film1 = new Film();
	    film1.setFilmId((short) 7);
	    film1.setTitle("Snowdrop");
	    film1.setDescription("This is a sample description for this film.");
	    film1.setReleaseYear(2022);
	    film1.setRentalDuration((byte) 3);
	    film1.setLength((short) 120);
	    film1.setRentalRate(BigDecimal.valueOf(3.99));
	    film1.setReplacementCost(BigDecimal.valueOf(19.99));
	    film1.setRating(MovieRating.PG_13);
	    film1.setSpecialFeatures("Trailers, Deleted Scenes");
	    Language language1 = new Language((byte) 1,"Korean",LocalDateTime.now());
	    film1.setLanguage(language1);
	    film1.setOriginalLanguage(null);
	    
	    Film film2 = new Film();
	    film2.setFilmId((short) 8);
	    film2.setTitle("Ring");
	    film2.setDescription("This is a sample description for the film.");
	    film2.setReleaseYear(2019);
	    film2.setRentalDuration((byte) 6);
	    film2.setLength((short) 90);
	    film2.setRentalRate(BigDecimal.valueOf(6.99));
	    film2.setReplacementCost(BigDecimal.valueOf(27.99));
	    film2.setRating(MovieRating.G);
	    film2.setSpecialFeatures("Trailers");
	    Language language2 = new Language((byte) 2,"English",LocalDateTime.now());
	    film2.setLanguage(language2);
	    film2.setOriginalLanguage(null);
	    
	    Film film3 = new Film();
	    film3.setFilmId((short) 8);
	    film3.setTitle("Hidden Love");
	    film3.setDescription("This is a sample description for the film.");
	    film3.setReleaseYear(2019);
	    film3.setRentalDuration((byte) 6);
	    film3.setLength((short) 90);
	    film3.setRentalRate(BigDecimal.valueOf(6.99));
	    film3.setReplacementCost(BigDecimal.valueOf(27.99));
	    film3.setRating(MovieRating.G);
	    film3.setSpecialFeatures("Trailers");
	    Language language3 = new Language((byte) 1,"Korean",LocalDateTime.now());
	    film3.setLanguage(language3);
	    film3.setOriginalLanguage(null);
	    List<CountFilms> expected = List.of(new CountFilms(2022,1L),new CountFilms(2019, 2L));
	    when(filmDao.findCountOfFilmsByYear()).thenReturn(expected);
	    
	    List<CountFilms> result = filmServiceImpl.findCountOfFilmsByYear();
	    
	    assertEquals(expected,result);
	    
	}
	
	//get film by language
	@Test
	void searchFilmByLanguageTest() {
	    List<Film> films = new ArrayList<>();
	    
	    Film film1 = new Film();
	    film1.setFilmId((short) 7);
	    film1.setTitle("Snowdrop");
	    film1.setDescription("This is a sample description for this film.");
	    film1.setReleaseYear(2022);
	    film1.setRentalDuration((byte) 3);
	    film1.setLength((short) 120);
	    film1.setRentalRate(BigDecimal.valueOf(3.99));
	    film1.setReplacementCost(BigDecimal.valueOf(19.99));
	    film1.setRating(MovieRating.PG_13);
	    film1.setSpecialFeatures("Trailers, Deleted Scenes");
	    Language language1 = new Language((byte) 1,"Korean",LocalDateTime.now());
	    film1.setLanguage(language1);
	    film1.setOriginalLanguage(null);
	    
	    Film film2 = new Film();
	    film2.setFilmId((short) 8);
	    film2.setTitle("Ring");
	    film2.setDescription("This is a sample description for the film.");
	    film2.setReleaseYear(2019);
	    film2.setRentalDuration((byte) 6);
	    film2.setLength((short) 90);
	    film2.setRentalRate(BigDecimal.valueOf(6.99));
	    film2.setReplacementCost(BigDecimal.valueOf(27.99));
	    film2.setRating(MovieRating.G);
	    film2.setSpecialFeatures("Trailers");
	    Language language2 = new Language((byte) 2,"English",LocalDateTime.now());
	    film2.setLanguage(language2);
	    film2.setOriginalLanguage(null);
	    
	    Film film3 = new Film();
	    film3.setFilmId((short) 8);
	    film3.setTitle("Hidden Love");
	    film3.setDescription("This is a sample description for the film.");
	    film3.setReleaseYear(2019);
	    film3.setRentalDuration((byte) 6);
	    film3.setLength((short) 90);
	    film3.setRentalRate(BigDecimal.valueOf(6.99));
	    film3.setReplacementCost(BigDecimal.valueOf(27.99));
	    film3.setRating(MovieRating.G);
	    film3.setSpecialFeatures("Trailers");
	    Language language3 = new Language((byte) 1,"Korean",LocalDateTime.now());
	    film3.setLanguage(language3);
	    film3.setOriginalLanguage(null);
	    
	    
	    films.add(film1);
	    films.add(film2);
	    films.add(film3);
	    
	    List<Film> expectedFilms = new ArrayList<>();
	    expectedFilms.add(film1);
	    expectedFilms.add(film3);
	    
	    when(filmDao.findFilmsByOriginalLanguageName("Korean")).thenReturn(expectedFilms);
	    
	    List<Film> foundFilms = filmServiceImpl.findFilmsByLanguageName("Korean");
	    //System.out.println(foundFilms.size());
	    verify(filmDao, times(1)).findFilmsByOriginalLanguageName("Korean");
	    assertEquals(2, foundFilms.size());
	    assertEquals(expectedFilms, foundFilms);
	}
	
	
	//get film by category 
	@Test
	void searchFilmByCategoryTest() {
	    List<Film> films = new ArrayList<>();
	    
	    Film film1 = new Film();
	    film1.setFilmId((short) 7);
	    film1.setTitle("Snowdrop");
	    film1.setDescription("This is a sample description for this film.");
	    film1.setReleaseYear(2022);
	    film1.setRentalDuration((byte) 3);
	    film1.setLength((short) 120);
	    film1.setRentalRate(BigDecimal.valueOf(3.99));
	    film1.setReplacementCost(BigDecimal.valueOf(19.99));
	    film1.setRating(MovieRating.PG_13);
	    film1.setSpecialFeatures("Trailers, Deleted Scenes");
	    film1.setLanguage(null);
	    film1.setOriginalLanguage(null);
	    Category category1 = new Category((byte)1,"Love",LocalDateTime.now());
	    FilmCategory filmCategory1 = new FilmCategory();
	    filmCategory1.setCategory(category1);
	    List<FilmCategory> filmCateogiesList1 = new ArrayList<>();
	    filmCateogiesList1.add(filmCategory1);
	    film1.setFilmCategory(filmCateogiesList1);
	    
	    Film film2 = new Film();
	    film2.setFilmId((short) 8);
	    film2.setTitle("Ring");
	    film2.setDescription("This is a sample description for the film.");
	    film2.setReleaseYear(2019);
	    film2.setRentalDuration((byte) 6);
	    film2.setLength((short) 90);
	    film2.setRentalRate(BigDecimal.valueOf(6.99));
	    film2.setReplacementCost(BigDecimal.valueOf(27.99));
	    film2.setRating(MovieRating.G);
	    film2.setSpecialFeatures("Trailers");
	    film1.setLanguage(null);
	    film1.setOriginalLanguage(null);
	    Category category2 = new Category((byte)2,"Horror",LocalDateTime.now());
	    FilmCategory filmCategory2 = new FilmCategory();
	    filmCategory2.setCategory(category2);
	    List<FilmCategory> filmCateogiesList2 = new ArrayList<>();
	    filmCateogiesList2.add(filmCategory2);
	    film1.setFilmCategory(filmCateogiesList2);
	    
	    Film film3 = new Film();
	    film3.setFilmId((short) 8);
	    film3.setTitle("Hidden Love");
	    film3.setDescription("This is a sample description for the film.");
	    film3.setReleaseYear(2019);
	    film3.setRentalDuration((byte) 6);
	    film3.setLength((short) 90);
	    film3.setRentalRate(BigDecimal.valueOf(6.99));
	    film3.setReplacementCost(BigDecimal.valueOf(27.99));
	    film3.setRating(MovieRating.G);
	    film3.setSpecialFeatures("Trailers");
	    Category category3 = new Category((byte)2,"Love",LocalDateTime.now());
	    FilmCategory filmCategory3 = new FilmCategory();
	    filmCategory3.setCategory(category3);
	    List<FilmCategory> filmCateogiesList3 = new ArrayList<>();
	    filmCateogiesList3.add(filmCategory3);
	    film1.setFilmCategory(filmCateogiesList3);
	    
	    
	    films.add(film1);
	    films.add(film2);
	    films.add(film3);
	    
	    List<Film> expectedFilms = new ArrayList<>();
	    expectedFilms.add(film1);
	    expectedFilms.add(film3);
	    
	    when(filmDao.findFilmByCategory("Love")).thenReturn(expectedFilms);
	    
	    List<Film> foundFilms = filmServiceImpl.findFilmByCategory("Love");
	    //System.out.println(foundFilms.size());
	    verify(filmDao, times(1)).findFilmByCategory("Love");
	    assertEquals(2, foundFilms.size());
	    assertEquals(expectedFilms, foundFilms);
	}
	
	
	//update film title
	@Test
	void updateFilmTitleTest() {
		Film film1 = new Film();
        film1.setTitle("Conjuring 1");
        film1.setDescription("This is a sample description for this film.");
        film1.setReleaseYear(2022);
        film1.setRentalDuration((byte) 5);
        film1.setLength((short) 120);
        film1.setRentalRate(BigDecimal.valueOf(3.99));
        film1.setReplacementCost(BigDecimal.valueOf(19.99));
        film1.setRating(MovieRating.PG_13);
        film1.setSpecialFeatures("Trailers, Deleted Scenes");
        film1.setLanguage(null);
        film1.setOriginalLanguage(null);
		
	    when(filmDao.findById((short) 1)).thenReturn(Optional.of(film1));
	    
	    short filmId = 1;
	    String updatedTitle = "Squid Game 2";
	    Film film2 = filmServiceImpl.updateFilmTitle(updatedTitle, filmId);
	    verify(filmDao, times(1)).findById(filmId);
	    assertEquals(updatedTitle, film2.getTitle());
	    assertEquals(film1,film2);
	}
	
	@Test
	void updateFilmReleaseYearTest() {
		Film film1 = new Film();
        film1.setTitle("Conjuring 1");
        film1.setDescription("This is a sample description for this film.");
        film1.setReleaseYear(2022);
        film1.setRentalDuration((byte) 5);
        film1.setLength((short) 120);
        film1.setRentalRate(BigDecimal.valueOf(3.99));
        film1.setReplacementCost(BigDecimal.valueOf(19.99));
        film1.setRating(MovieRating.PG_13);
        film1.setSpecialFeatures("Trailers, Deleted Scenes");
        film1.setLanguage(null);
        film1.setOriginalLanguage(null);
	    when(filmDao.findById((short) 1)).thenReturn(Optional.of(film1));
	    
	    short filmId = 1;
	    int updatedReleaseYear = 2023;
	    Film film2 = filmServiceImpl.updateFilmReleaseYear(updatedReleaseYear, filmId);
//	    System.out.println(film2.getReleaseYear());
	    verify(filmDao, times(1)).findById(filmId);
	    assertEquals(updatedReleaseYear, film2.getReleaseYear());
	    assertEquals(film1,film2);
	}
	
	@Test
	void updateFilmRentalDurationTest() {
		Film film1 = new Film();
        film1.setTitle("Conjuring 1");
        film1.setDescription("This is a sample description for this film.");
        film1.setReleaseYear(2022);
        film1.setRentalDuration((byte) 5);
        film1.setLength((short) 120);
        film1.setRentalRate(BigDecimal.valueOf(3.99));
        film1.setReplacementCost(BigDecimal.valueOf(19.99));
        film1.setRating(MovieRating.PG_13);
        film1.setSpecialFeatures("Trailers, Deleted Scenes");
        film1.setLanguage(null);
        film1.setOriginalLanguage(null);
	    when(filmDao.findById((short) 1)).thenReturn(Optional.of(film1));
	    
	    short filmId = 1;
	    byte updatedRentalDuration = 10;
	    Film film2 = filmServiceImpl.updateFilmRentalDuration(updatedRentalDuration, filmId);
	    verify(filmDao, times(1)).findById(filmId);
	    assertEquals(updatedRentalDuration, film1.getRentalDuration());
	    assertEquals(film1,film2);
	}
	
	@Test
	void updateFilmRentalRateTest() {
		Film film1 = new Film();
        film1.setTitle("Conjuring 1");
        film1.setDescription("This is a sample description for this film.");
        film1.setReleaseYear(2022);
        film1.setRentalDuration((byte) 5);
        film1.setLength((short) 120);
        film1.setRentalRate(BigDecimal.valueOf(3.99));
        film1.setReplacementCost(BigDecimal.valueOf(19.99));
        film1.setRating(MovieRating.PG_13);
        film1.setSpecialFeatures("Trailers, Deleted Scenes");
        film1.setLanguage(null);
        film1.setOriginalLanguage(null);
	    when(filmDao.findById((short) 1)).thenReturn(Optional.of(film1));
	    
	    short filmId = 1;
	    BigDecimal updatedRentalRate = new BigDecimal(6.08);
	    Film film2 = filmServiceImpl.updateFilmRentalRate(updatedRentalRate, filmId);
	    verify(filmDao, times(1)).findById(filmId);
	    assertEquals(updatedRentalRate, film1.getRentalRate());
	    assertEquals(film1,film2);
	}
	
	@Test
	void updateFilmLanguageTest() {
		Film film1 = new Film();
		film1.setFilmId((short)1);
        film1.setTitle("Conjuring 1");
        film1.setDescription("This is a sample description for this film.");
        film1.setReleaseYear(2022);
        film1.setRentalDuration((byte) 5);
        film1.setLength((short) 120);
        film1.setRentalRate(BigDecimal.valueOf(3.99));
        film1.setReplacementCost(BigDecimal.valueOf(19.99));
        film1.setRating(MovieRating.PG_13);
        film1.setSpecialFeatures("Trailers, Deleted Scenes");
        Language language1 = new Language();
        Language language2 = new Language();
        Language language3 = new Language();
        language3.setName("Korean");
        language2.setName("Spanish");
        language1.setName("English");
        film1.setOriginalLanguage(language2);
        //System.out.println(film1.getLanguage().getName());
        film1.setOriginalLanguage(null);
	    when(filmDao.getByFilmId((short) 1)).thenReturn(film1);
	    when(languageDao.findByName("Korean")).thenReturn(language3);
	    when(languageDao.findByName("Spanish")).thenReturn(language2);
	    when(languageDao.findByName("English")).thenReturn(language1);
	    
	    short filmId = 1;
	    String updatedLanguageName = "Korean";
	    Film film2 = filmServiceImpl.updateLanguageOfFilm(updatedLanguageName, filmId);
	    assertEquals(updatedLanguageName, film2.getOriginalLanguage().getName());
	    assertEquals(film1,film2);
	}	
	
	@Test
    public void assignActorsToAFilmTest() {
        Short filmId = 1;
        Short actorId = 192;
        Film film = new Film(); 
        film.setFilmId((short)990);
        film.setTitle("Conjuring 1");
        film.setDescription("This is a sample description for this film.");
        film.setReleaseYear(2022);
        film.setRentalDuration((byte) 5);
        film.setLength((short) 120);
        film.setRentalRate(BigDecimal.valueOf(3.99));
        film.setReplacementCost(BigDecimal.valueOf(19.99));
        film.setRating(MovieRating.PG_13);
        film.setSpecialFeatures("Trailers, Deleted Scenes");
        
        Actor actor = new Actor((short)192,"JOHN","SUVARI",LocalDateTime.now());
        FilmActor filmActor = new FilmActor();
        filmActor.setFilm(film);
        filmActor.setActor(actor);
        filmActor.setLastUpdate(LocalDateTime.now());
        List<FilmActor> filmActors = new ArrayList<>();
        filmActors.add(filmActor);
        when(filmDao.findById(filmId)).thenReturn(Optional.of(film));
        when(actorDao.findById(actorId)).thenReturn(Optional.of(actor));
        when(filmActorDao.findByFilmFilmId(filmId)).thenReturn(filmActors);
        List<Actor> result = filmActorServiceImpl.assignActorToAFilm(filmId, actor);
        verify(filmActorDao, times(1)).save(any(FilmActor.class));
        assertEquals(1, result.size());
        assertEquals(actor, result.get(0));
    }
	
	@Test
	void searchFilmsGreaterThanRatingTest() {
		
		//Movie Rating enum=>G,PG,PG_13,R,NC_17
		Film film1 = new Film();
		film1.setFilmId((short)990);
        film1.setTitle("CONJURING");
        film1.setDescription("This is a sample description for this film.");
        film1.setReleaseYear(2022);
        film1.setRentalDuration((byte) 5);
        film1.setLength((short) 120);
        film1.setRentalRate(BigDecimal.valueOf(3.99));
        film1.setReplacementCost(BigDecimal.valueOf(19.99));
        film1.setRating(MovieRating.PG);
        film1.setSpecialFeatures("Trailers, Deleted Scenes");
        
        Film film2 = new Film();
		film2.setFilmId((short)220);
        film2.setTitle("MONEY HEIST");
        film2.setDescription("This is a sample description for this film.");
        film2.setReleaseYear(2022);
        film2.setRentalDuration((byte) 5);
        film2.setLength((short) 220);
        film2.setRentalRate(BigDecimal.valueOf(3.99));
        film2.setReplacementCost(BigDecimal.valueOf(29.99));
        film2.setRating(MovieRating.G);
        film2.setSpecialFeatures("Trailers, Deleted Scenes");
        
        Film film3 = new Film();
		film3.setFilmId((short)38);
        film3.setTitle("PREDATOR");
        film3.setDescription("This is a sample description for this film.");
        film3.setReleaseYear(2022);
        film3.setRentalDuration((byte) 5);
        film3.setLength((short) 320);
        film3.setRentalRate(BigDecimal.valueOf(3.99));
        film3.setReplacementCost(BigDecimal.valueOf(39.99));
        film3.setRating(MovieRating.NC_17);
        film3.setSpecialFeatures("Trailers, Deleted Scenes");
        
        when(filmDao.findByRatingIn(anyList())).thenReturn(List.of(film1, film2));
        
        List<Film> result = filmServiceImpl.findByRatingGreaterThan(MovieRating.PG_13);
        
        assertEquals(List.of(film1,film2), result);
	}
	
	@Test
	void searchFilmsLessThanRatingTest() {
		
		//Movie Rating enum=>G,PG,PG_13,R,NC_17
		Film film1 = new Film();
		film1.setFilmId((short)990);
        film1.setTitle("CONJURING");
        film1.setDescription("This is a sample description for this film.");
        film1.setReleaseYear(2022);
        film1.setRentalDuration((byte) 5);
        film1.setLength((short) 120);
        film1.setRentalRate(BigDecimal.valueOf(3.99));
        film1.setReplacementCost(BigDecimal.valueOf(19.99));
        film1.setRating(MovieRating.R);
        film1.setSpecialFeatures("Trailers, Deleted Scenes");
        
        Film film2 = new Film();
		film2.setFilmId((short)220);
        film2.setTitle("MONEY HEIST");
        film2.setDescription("This is a sample description for this film.");
        film2.setReleaseYear(2022);
        film2.setRentalDuration((byte) 5);
        film2.setLength((short) 220);
        film2.setRentalRate(BigDecimal.valueOf(3.99));
        film2.setReplacementCost(BigDecimal.valueOf(29.99));
        film2.setRating(MovieRating.PG);
        film2.setSpecialFeatures("Trailers, Deleted Scenes");
        
        Film film3 = new Film();
		film3.setFilmId((short)38);
        film3.setTitle("PREDATOR");
        film3.setDescription("This is a sample description for this film.");
        film3.setReleaseYear(2022);
        film3.setRentalDuration((byte) 5);
        film3.setLength((short) 320);
        film3.setRentalRate(BigDecimal.valueOf(3.99));
        film3.setReplacementCost(BigDecimal.valueOf(39.99));
        film3.setRating(MovieRating.NC_17);
        film3.setSpecialFeatures("Trailers, Deleted Scenes");
        
        when(filmDao.findByRatingIn(anyList())).thenReturn(List.of(film1, film3));
        
        List<Film> result = filmServiceImpl.findByRatingLessThan(MovieRating.PG_13);
        
        assertEquals(List.of(film1,film3), result);
	}
	
	@Test
	void updateRatingOffilmTest() {
		Film film1 = new Film();
		film1.setFilmId((short)990);
        film1.setTitle("CONJURING");
        film1.setDescription("This is a sample description for this film.");
        film1.setReleaseYear(2022);
        film1.setRentalDuration((byte) 5);
        film1.setLength((short) 120);
        film1.setRentalRate(BigDecimal.valueOf(3.99));
        film1.setReplacementCost(BigDecimal.valueOf(19.99));
        film1.setRating(MovieRating.R);
        film1.setSpecialFeatures("Trailers, Deleted Scenes");
        
        when(filmDao.findById((short)990)).thenReturn(Optional.of(film1));
        
        Short filmId=990;
        String newRating = "PG";
        
        Film film2 = filmServiceImpl.updateRatingOfFilm(newRating, filmId);
        assertEquals(newRating,film2.getRating().name());        
	}
	
	@Test
	void findActorsByFilmIdTest() {
		Film film1 = new Film();
		film1.setFilmId((short)1);
		Actor actor1 = new Actor();
		actor1.setFirstName("Cha");
		actor1.setLastName("Eunwoo");
		Actor actor2 = new Actor();
		actor2.setFirstName("Lee");
		actor2.setLastName("Minho");
		String str1 = actor1.getFirstName()+" "+actor1.getLastName();
		String str2 = actor2.getFirstName()+" "+actor2.getLastName();
		List<ActorDetails> expected = new ArrayList<>();
		expected.add(new ActorDetails(str1));
		expected.add(new ActorDetails(str2));
		
		Short filmId = 1;
		when(filmDao.findActorsByFilmId(filmId)).thenReturn(expected);
		List<ActorDetails> result = filmServiceImpl.findActorsByFilmId(filmId);
		assertEquals(expected,result);
	}
	
	@Test
	void updateCategoryOfFilmTest() {
		Short filmId = 1;
        String categoryName = "Action";

        Film film = new Film();
        film.setFilmId(filmId);

        when(filmDao.findById(filmId)).thenReturn(Optional.of(film));
        when(categoryDao.findByName(categoryName)).thenReturn(null);

        InvalidCategoryException exception = assertThrows(InvalidCategoryException.class, () -> {
            filmServiceImpl.updateOrAddCategory(filmId, categoryName);
        });

        assertEquals("Invalid category", exception.getMessage());
   	}

}
