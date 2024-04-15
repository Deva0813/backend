package org.filmrental.com.service;

import java.util.List;


import org.filmrental.com.dto.Top10ActorDetails;
import org.filmrental.com.model.Actor;
import org.filmrental.com.model.Film;


public interface IActorService {
	public Actor addActor(Actor actor);

	public List<Actor> findActorsByLastName(String lastName);
	
	public List<Actor> findActorsByFirstName(String firstName);
	
	public List<Film> getFilmsByActorId(int actorId); 
	
	public Actor findByActorsId (short actorId);

	public Actor updateLastNameById(String lastName,short actorId);

	public Actor updateFirstNameById(String lastName, short actorId);
	
	public List<Top10ActorDetails> findTopTenActorsByFilmCount();
	
	public List<Actor> getAllActors();
	
	public boolean deleteActor(short actorId);
	
	
	public List<Actor> findByFilmActorFilmTitle(String title);

	public List<Actor> findByFilmTitle(String title);
	


 

}
