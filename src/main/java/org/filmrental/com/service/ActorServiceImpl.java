package org.filmrental.com.service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.filmrental.com.dao.IActorDao;
import org.filmrental.com.dao.IFilmDao;
import org.filmrental.com.dto.Top10ActorDetails;
import org.filmrental.com.model.Actor;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.FilmActor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.transaction.Transactional;


@Service
public class ActorServiceImpl implements IActorService{
	
	@Autowired
	private IActorDao actorDao;
	
	

	//Add new Actor object in DB
	@Override
	public Actor addActor(Actor actor) {
		// TODO Auto-generated method stub
		Actor act= actorDao.save(actor);
		return act;
	}

	//Find actor by lastName
	@Override
	public List<Actor> findActorsByLastName(String lastName)  {
		List<Actor> list = actorDao.findByLastName(lastName);
		return list;
	}
	
	//Find actor by firstName
	@Override
	public List<Actor> findActorsByFirstName(String firstName){
		List<Actor> list = actorDao.findByFirstName(firstName);
	    return list;
	}

	// Films by actorId
	@Override
	public List<Film> getFilmsByActorId(int actor_id) {
		List<Film> films = actorDao.getFilmsByActorId(actor_id);
		return films;
	}

	//Find by actorId
	@Override
	public Actor findByActorsId(short actorId) {
		Optional<Actor> optional=actorDao.findById(actorId);
		if(optional.isPresent())
			return optional.get();
		return null;
	}

	
    //Update by firstName
	@Transactional
	@Override
	public Actor updateLastNameById(String lastName, short actorId) {
		Actor actor1=findByActorsId(actorId);
		if(actor1!=null) {
			actor1.setLastName(lastName);
			actor1.setLastUpdate(LocalDateTime.now());
			
		}
		return actor1;
	}

	//Update by lastName
	@Transactional
	@Override
	public Actor updateFirstNameById(String firstName, short actorId) {
		Actor actor1=findByActorsId(actorId);
		if(actor1!=null) {
			actor1.setFirstName(firstName);
			actor1.setLastUpdate(LocalDateTime.now());
			
		}
		return actor1;
	}

	//TOP 10 Actor by Film count
	@Override
	public List<Top10ActorDetails> findTopTenActorsByFilmCount() {
		List<Top10ActorDetails> list=actorDao.findTopTenActorsByFilmCount();
		return list;
	}

	// Set actor 
	public void setActorDao(IActorDao actorDao) {      
	this.actorDao = actorDao;   
	}

	@Override
	public List<Actor> getAllActors() {
		// TODO Auto-generated method stub
		return (List<Actor>) actorDao.findAll();
	}

	@Override
	public boolean deleteActor(short actorId) {
		if(findByActorsId(actorId)!=null) {
			actorDao.deleteById(actorId);
			return true;
		}
		return false;
	}
	
	@Override
	public List<Actor> findByFilmActorFilmTitle(String title) {
		// TODO Auto-generated method stub
		return actorDao.findByFilmActorFilmTitle(title);
	}
 
	@Override
	public List<Actor> findByFilmTitle(String title) {
		// TODO Auto-generated method stub
		return  actorDao.findByFilmActorFilmTitle(title);
	}

	

	


}
