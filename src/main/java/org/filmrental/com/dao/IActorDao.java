package org.filmrental.com.dao;

import java.util.List;
import java.util.Optional;


import org.filmrental.com.dto.Top10ActorDetails;
import org.filmrental.com.model.Actor;
import org.filmrental.com.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IActorDao extends JpaRepository<Actor,Short> {
	
	//FIND BY LAST NAME
	List<Actor> findByLastName(String lastName);

	//FIND BY FIRST NAME
	List<Actor> findByFirstName(String firstName);
	
    //FILMS BY ACTOR ID
	@Query("SELECT fa.film FROM FilmActor fa WHERE fa.actor.actorId = ?1")
	List<Film> getFilmsByActorId(int actorId);


	
	
	//FIND TOP 10 ACTORS BY FILM COUNT	
	@Query("SELECT new org.filmrental.com.dto.Top10ActorDetails(f.actor.actorId, CONCAT(f.actor.firstName, ' ', f.actor.lastName), COUNT(f.film.filmId))"
		    + " FROM FilmActor f GROUP BY f.actor.actorId ORDER BY COUNT(f.film.filmId) DESC LIMIT 10")
		List<Top10ActorDetails> findTopTenActorsByFilmCount();
	
	List<Actor> findByFilmActorFilmTitle(String title);


	

	




}