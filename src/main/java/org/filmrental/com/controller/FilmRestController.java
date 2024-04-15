
package org.filmrental.com.controller;
 
import java.util.List;
 
 
import org.filmrental.com.dto.ActorDetails;
import org.filmrental.com.dto.CountFilms;
import org.filmrental.com.dto.FilmActorDetails;
import org.filmrental.com.dto.FilmCategoryRequestBody;
import org.filmrental.com.dto.FilmRating;
import org.filmrental.com.dto.FilmsProjectRequestBody;
import org.filmrental.com.dto.Actors;
import org.filmrental.com.exception.DataNotFoundException;
import org.filmrental.com.exception.IdNotFoundException;
import org.filmrental.com.exception.InvalidRatingException;
import org.filmrental.com.exception.PostException;
import org.filmrental.com.exception.UpdationErrorException;
import org.filmrental.com.model.Actor;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.FilmActor;
import org.filmrental.com.model.FilmCategory;
import org.filmrental.com.model.MovieRating;
import org.filmrental.com.service.FilmServiceImpl;
import org.filmrental.com.service.IActorService;
import org.filmrental.com.service.IFilmActorService;
import org.filmrental.com.service.IFilmService;
import org.filmrental.com.utility.MovieRatingValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
@RequestMapping("/api/films")
@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
public class FilmRestController {
	@Autowired
	private IFilmService filmService;
	@Autowired
	private IFilmActorService filmActorService;
	@Autowired
	private IActorService iactorService;
	//Add new Film object in DB=====(1)
	@PostMapping("/post")
	public ResponseEntity<String> addFilm(@RequestBody Film film) {
		Film film1 = filmService.addFilm(film);
		if(film1 == null || film1.getFilmId()==0) {
			throw new PostException("Error in adding Film Details");
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");
		}
	}
	//Get all customers
	@GetMapping("/")
	public ResponseEntity<List<Film>> getAllFilms(){
		List<Film> films=filmService.getAllFilms();
		if(films.isEmpty() || films == null) {
			throw new DataNotFoundException("Film List is empty");
		}else {
			return new ResponseEntity<List<Film>>(films, HttpStatus.OK);
		}	
	}
	//Search Films by Title=====(2)
	@GetMapping("/title/{title}")
	public ResponseEntity<List<Film>> findByTitleContaining(@PathVariable String title) {
		List<Film> films = filmService.findByTitleContaining(title);
		if(films.isEmpty() || films == null) {
			throw new DataNotFoundException("No Films has the title "+title);
		}else {
			return new ResponseEntity<>(films, HttpStatus.OK);
		}
	}
	//Search Films by Release Year=====(3)
	@GetMapping("/year/{year}")
	public ResponseEntity<List<Film>> findByReleaseYear(@PathVariable int year) {
		List<Film> films = filmService.findByReleaseYear(year);
		if(films.isEmpty() || films == null) {
			throw new DataNotFoundException("No Films was released in the "+year);
		}else {
			return new ResponseEntity<>(films, HttpStatus.OK);
		}
	}
	//Search Films where Rental Duration is greater than {rd}=====(4)
	@GetMapping("/duration/gt/{rd}")
	public ResponseEntity<List<Film>> findFilmsByRentalDurationGreaterThan(@PathVariable int rd) {
		List<Film> films = filmService.findFilmsByRentalDurationGreaterThan(rd);
		if(films.isEmpty() || films == null) {
			throw new DataNotFoundException("No Films has duration greater than "+rd);
		}else {
			return new ResponseEntity<>(films, HttpStatus.OK);
		}
	}
	//Search Films where Rental Rate is greater than {rate}=====(5)
	@GetMapping("/rate/gt/{rate}")
	public ResponseEntity<List<Film>> findFilmsByRentalRateGreaterThan(@PathVariable int rate) {
		List<Film> films = filmService.findFilmsByRentalRateGreaterThan(rate);
		if(films.isEmpty() || films == null) {
			throw new DataNotFoundException("No Films has rental rate greater than "+rate);
		}else {
			return new ResponseEntity<>(films, HttpStatus.OK);
		}
	}
	//Search Films where Length is greater than {length}=====(6)
	@GetMapping("/length/gt/{length}")
	public ResponseEntity<List<Film>> findFilmsByLengthGreaterThan(@PathVariable int length) {
		List<Film> films = filmService.findFilmsByLengthGreaterThan(length);
		if(films.isEmpty() || films == null) {
			throw new DataNotFoundException("No Films has length greater than "+length);
		}else {
			return new ResponseEntity<>(films, HttpStatus.OK);
		}
	}
	//Search Films where Rental Duration is lower than {rd}=====(7)
	@GetMapping("/duration/lt/{rd}")
	public ResponseEntity<List<Film>> findFilmsByRentalDurationLessThan(@PathVariable int rd) {
		List<Film> films = filmService.findFilmsByRentalDurationLessThan(rd);
		if(films.isEmpty() || films == null) {
			throw new DataNotFoundException("No Films has duration less than "+rd);
		}else {
			return new ResponseEntity<>(films, HttpStatus.OK);
		}
	}
	//Search Films where Rental rate is lower than {length}=====(8)
	@GetMapping("/rate/lt/{rate}")
	public ResponseEntity<List<Film>> findFilmsByRentalRateLessThan(@PathVariable int rate) {
		List<Film> films = filmService.findFilmsByRentalRateLessThan(rate);
		if(films.isEmpty() || films == null) {
			throw new DataNotFoundException("No Films has rental rate less than "+rate);
		}else {
			return new ResponseEntity<>(films, HttpStatus.OK);
		}
	}
	//Search Films where Length is  lower than {length}=====(9)
	@GetMapping("/length/lt/{length}")
	public ResponseEntity<List<Film>> findFilmsByLengthLessThan(@PathVariable int length) {
		List<Film> films = filmService.findFilmsByLengthLessThan(length);
		if(films.isEmpty() || films == null) {
			throw new DataNotFoundException("No Films has length less than "+length);
		}else {
			return new ResponseEntity<>(films, HttpStatus.OK);
		}
	}
	//Search  Films which are Released between {from} and {to}====(10)
	@GetMapping("/betweenyear/{from}/{to}")
	public ResponseEntity<List<Film>> findFilmsByReleaseYearBetween(@PathVariable int from, @PathVariable int to) {
		List<Film> films = filmService.findFilmsByReleaseYearBetween(from, to);
		if(films.isEmpty() || films == null) {
			throw new DataNotFoundException("No Films has been released between "+ from + " and "+ to);
		}else {
			return new ResponseEntity<>(films, HttpStatus.OK);
		}
	}
	//Search Films where Rating is greater than {rating}=====(11)
	@GetMapping("/rating/gt/{rating}")
	public ResponseEntity<List<Film>> findFilmsByRatingGreaterThan(@PathVariable String rating) {
		if(MovieRatingValidation.isValidRating(rating)) {
			List<Film> films = filmService.findByRatingGreaterThan(MovieRating.valueOf(rating));
			if(films.isEmpty() || films == null) {
				throw new DataNotFoundException("No Films has been greater than "+rating);
			}else {
				return new ResponseEntity<>(films, HttpStatus.OK);
			}
		}else {
			throw new InvalidRatingException("Invalid Rating Exception");
		}

	}
	//Search Films where Rating is lower than {rating}=====(12)
	@GetMapping("/rating/lt/{rating}")
	public ResponseEntity<List<Film>> findFilmsByRatingLessThan(@PathVariable String rating) {
		if(MovieRatingValidation.isValidRating(rating)) {
			List<Film> films = filmService.findByRatingLessThan(MovieRating.valueOf(rating));
			if(films.isEmpty() || films == null) {
				throw new DataNotFoundException("No Films has been less than "+rating);
			}else {
				return new ResponseEntity<>(films, HttpStatus.OK);
			}
		}else {
			throw new InvalidRatingException("Invalid Rating Exception");
		}

	}
	//Search Films where Language is {lang}=====(13)
	@GetMapping("/language/{lang}")
	public ResponseEntity<List<Film>> findFilmsByLanguageName(@PathVariable String lang) {
		List<Film> films = filmService.findFilmsByLanguageName(lang);
		if(films.isEmpty() || films == null) {
			throw new DataNotFoundException("No Films has been found for the language "+lang);
		}else {
			return new ResponseEntity<>(films, HttpStatus.OK);
		}
	}
	//Display number of Films released  by each Year=====(14)
	@GetMapping("/countbyyear")
	public ResponseEntity<List<CountFilms>> findCountOfFilmsByYear() {
		List<CountFilms> films = filmService.findCountOfFilmsByYear();
		if(films.isEmpty() || films == null) {
			throw new DataNotFoundException("No Films has been found for the year ");
		}else {
			return new ResponseEntity<>(films, HttpStatus.OK);
		}
	}
	//Find all Actors of a Film by Film id=====(15)
	@GetMapping("/{id}/actors")
	public ResponseEntity<List<ActorDetails>> findActorsByFilmId(@PathVariable("id") Short id) {
		List<ActorDetails> films = filmService.findActorsByFilmId(id);
		if(films.isEmpty() || films == null) {
			throw new DataNotFoundException("No Actors found for this film");
		}else {
			return new ResponseEntity<>(films, HttpStatus.OK);
		}
	}
	//Find all Films of specified  {category}=====(16)
	@GetMapping("/category/{category}")
	public ResponseEntity<List<Film>> findActorsByFilmCategory(@PathVariable("category") String category) {
		List<Film> films = filmService.findFilmByCategory(category);
		if(films.isEmpty() || films == null) {
			throw new DataNotFoundException("No Films found for this category "+category);
		}else {
			return new ResponseEntity<>(films, HttpStatus.OK);
		}
	}
	//Update Title of a Film=====(17)
	@PutMapping("/update/title")
	public ResponseEntity<Film> updateFilmTitle(@RequestBody Film film){
		Film film1 = filmService.updateFilmTitle(film.getTitle(), film.getFilmId());
		if(filmService.getFilmById(film.getFilmId())!=null) {
			if(film1 != null) {
				return new ResponseEntity<Film>(film1,HttpStatus.OK);
			}else {
				throw new UpdationErrorException("Cannot Update Film Title");
			}
		}else {
			throw new IdNotFoundException("Film Id not found");
		}
	}
	//Update Release Year of a Film=====(18)
	@PutMapping("/update/releaseyear")
	public ResponseEntity<Film> updateFilmReleaseYear(@RequestBody Film film){
		Film film1 = filmService.updateFilmReleaseYear(film.getReleaseYear(), film.getFilmId());
		if(filmService.getFilmById(film.getFilmId())!=null) {
			if(film1 != null) {
				return new ResponseEntity<Film>(film1,HttpStatus.OK);
			}else {
				throw new UpdationErrorException("Cannot Update Film Release Year");
			}
		}else {
			throw new IdNotFoundException("Film Id not found");
		}
	}
	//Update Rental Duration of a Film=====(19)
	@PutMapping("/update/rentalduration")
	public ResponseEntity<Film> updateFilmRentalDuration(@RequestBody Film film){
		Film film1 = filmService.updateFilmRentalDuration(film.getRentalDuration(), film.getFilmId());
		if(filmService.getFilmById(film.getFilmId())!=null) {
			if(film1 != null) {
				return new ResponseEntity<Film>(film1,HttpStatus.OK);
			}else {
				throw new UpdationErrorException("Cannot Update Film Rental Duration");
			}
		}else {
			throw new IdNotFoundException("Film Id not found");
		}
	}
	//Update Rental Rate of a Film=====(20)
	@PutMapping("/update/rentalrate/{id}")
	public ResponseEntity<Film> updateFilmRentalRate(@RequestBody Film film){
		Film film1 = filmService.updateFilmRentalRate(film.getRentalRate(), film.getFilmId());
		if(filmService.getFilmById(film.getFilmId())!=null) {
			if(film1 != null) {
				return new ResponseEntity<Film>(film1,HttpStatus.OK);
			}else {
				throw new UpdationErrorException("Cannot Update Film Rental Rate");
			}
		}else {
			throw new IdNotFoundException("Film Id not found");
		}
	}
	//Update  Rating of a Film=====(21)//exception is not working
	@PutMapping("/update/rating")
	public ResponseEntity<Film> updateFilmRating(@RequestBody FilmRating film){
		Film film1 = filmService.updateRatingOfFilm(film.getRating(), film.getFilmId());
		if(film1 != null) {
			return new ResponseEntity<Film>(film1,HttpStatus.OK);
		}else {
			throw new UpdationErrorException("Cannot Update Film Rating");
		}		
	}
	//Update Language of a Film=====(22)
	@PutMapping("/update/language")
	public ResponseEntity<Film> updateFilmLanguage(@RequestBody Film film){
		Film film1 = filmService.updateLanguageOfFilm(film.getLanguage().getName(), film.getFilmId());
		if(filmService.getFilmById(film.getFilmId())!=null) {
			if(film1 != null) {
				return new ResponseEntity<Film>(film1,HttpStatus.OK);
			}else {
				throw new UpdationErrorException("Cannot Update Film Language");
			}
		}else {
			throw new IdNotFoundException("Film Id not found");
		}
	}
	//Update Category of a Film=====(23)
	@PutMapping("/update/category")
	public ResponseEntity<FilmCategory> updateFilmCategory(@RequestBody FilmCategoryRequestBody request){
		FilmCategory film1 = filmService.updateOrAddCategory(request.getFilmId(), request.getCategoryName());
		if(filmService.getFilmById(request.getFilmId())!=null) {
			if(film1 != null) {
				return new ResponseEntity<FilmCategory>(film1,HttpStatus.OK);
			}else {
				throw new UpdationErrorException("Cannot Update Film Category");
			}
		}else {
			throw new IdNotFoundException("Film Id not found");
		}
	}
	//Assign Actor to a film=====(24)	
	@PutMapping("/actor")
	public ResponseEntity<List<Actor>> assignActorToAFilm(@RequestBody Actors request) {
        if (request.getFilmId() != null) {
            List<Actor> actors = filmActorService.assignActorToAFilm(request.getFilmId(), request.getActor());
            return new ResponseEntity<>(actors, HttpStatus.OK);
        } else {
            throw new IdNotFoundException("Film Id not provided");
        }
 
	}	
	@GetMapping("/actors/{title}")
    public List<Actor> getActorsByFilmTitle(@PathVariable String title) {
        return iactorService.findByFilmTitle(title);
    }
 
}