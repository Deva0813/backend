package org.filmrental.com.service;

import java.util.List;

import org.filmrental.com.dto.FilmActorDetails;
import org.filmrental.com.model.Actor;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.FilmActor;
import org.springframework.stereotype.Service;

public interface IFilmActorService {
	public List<Actor> assignActorToAFilm(Short filmId,Actor actor);
	public List<Film> assignFilmToAnActor(Short actorId, Film film);
}
