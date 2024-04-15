package org.filmrental.com.dao;

import java.util.List;

import org.filmrental.com.model.FilmActor;
import org.filmrental.com.model.FilmActorId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFilmActorDao extends CrudRepository<FilmActor, FilmActorId>{
	public List<FilmActor> findByFilmFilmId(Short filmId);
	public List<FilmActor> findByActorActorId(Short actorId);
}
