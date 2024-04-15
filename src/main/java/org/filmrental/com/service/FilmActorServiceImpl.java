package org.filmrental.com.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.filmrental.com.dao.IActorDao;
import org.filmrental.com.dao.IFilmActorDao;
import org.filmrental.com.dao.IFilmDao;
import org.filmrental.com.dto.FilmActorDetails;
import org.filmrental.com.exception.IdNotFoundException;
import org.filmrental.com.model.Actor;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.FilmActor;
import org.filmrental.com.model.FilmActorId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmActorServiceImpl implements IFilmActorService {
	
	@Autowired
	private IFilmDao filmDao;

	
	@Autowired
	private IFilmActorDao filmActorDao;
	
	@Autowired
	private IActorDao actorDao;
	

	@Override
	public List<Actor> assignActorToAFilm(Short filmId, Actor actor) {
	    Optional<Film> optionalFilm = filmDao.findById(filmId);
	    if (optionalFilm.isPresent()) {
	        Film film = optionalFilm.get();
	        Optional<Actor> optionalActor = actorDao.findById(actor.getActorId());
	        if (optionalActor.isPresent()) {
	            FilmActor filmActor = new FilmActor();
	            filmActor.setFilm(film);
	            filmActor.setActor(actor);
	            filmActor.setLastUpdate(LocalDateTime.now());
	            filmActorDao.save(filmActor);
	            List<FilmActor> filmActors = filmActorDao.findByFilmFilmId(filmId);
	            List<Actor> actors = new ArrayList<>();
	            for (FilmActor fa : filmActors) {
	                actors.add(fa.getActor());
	            }
	            return actors;
	        } else {
	            throw new IdNotFoundException("Actor Id not found");
	        }
	    } else {
	        throw new IdNotFoundException("Film Id not found");
	    }
	}

	
	@Override
	public List<Film> assignFilmToAnActor(Short actorId, Film film) {
	    Optional<Actor> optionalActor = actorDao.findById(actorId);
	    if (optionalActor.isPresent()) {
	        Actor actor = optionalActor.get();
	        Optional<Film> optionalFilm = filmDao.findById(film.getFilmId());
	        if (optionalFilm.isPresent()) {
	            FilmActor filmActor = new FilmActor();
	            filmActor.setFilm(film);
	            filmActor.setActor(actor);
	            filmActor.setLastUpdate(LocalDateTime.now());
	            filmActorDao.save(filmActor);
	            List<FilmActor> filmActors = filmActorDao.findByActorActorId(actorId);
	            List<Film> films = new ArrayList<>();
	            for (FilmActor fa : filmActors) {
	                films.add(fa.getFilm());
	            }
	            return films;
	        } else {
	            throw new IdNotFoundException("Film Id not found");
	        }
	    } else {
	        throw new IdNotFoundException("Actor Id not found");
	    }
	}

}
