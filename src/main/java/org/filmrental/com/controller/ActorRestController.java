
package org.filmrental.com.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.filmrental.com.dto.FilmsProjectRequestBody;
import org.filmrental.com.dto.Top10ActorDetails;
import org.filmrental.com.exception.DataNotFoundException;
import org.filmrental.com.exception.DeletionException;
import org.filmrental.com.exception.IdNotFoundException;
import org.filmrental.com.exception.NameNotFoundException;
import org.filmrental.com.model.Actor;
import org.filmrental.com.model.Film;
import org.filmrental.com.service.IActorService;
import org.filmrental.com.service.IFilmActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
@CrossOrigin(origins= {"http://localhost:4200"}, allowCredentials="true")


@RequestMapping("/api/actors")
@RestController
public class ActorRestController {
	
	@Autowired
	private IActorService iactorService;
	
	@Autowired
	private IFilmActorService filmActorService;
	
	//Add new Actor to DB------POST
	@PostMapping("/post")
	public ResponseEntity<String>CreateActor(@RequestBody Actor actor) {
		Actor actor2=iactorService.addActor(actor);
		if(actor2==null|| actor2.getActorId()==0)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		else {
			return ResponseEntity.status(HttpStatus.CREATED).body("Record Created Successfully");}
	}
	
	//Find Actor By LastName-----GET
	@GetMapping("/lastname/{ln}")
	public ResponseEntity<List<Actor>> searchActorsByLastName(@PathVariable("ln") String ln)throws NameNotFoundException {
		List<Actor> actors = iactorService.findActorsByLastName(ln);
		if(actors==null || actors.isEmpty())
		{
			throw new NameNotFoundException("Last name not found:  " +ln );
		}else {
		return new ResponseEntity<List<Actor>>(actors, HttpStatus.OK);}
	}
	
	//Find Actor By FirstName-----GET
	@GetMapping("/firstname/{fn}")
	public ResponseEntity<List<Actor>> searchActorsByFirstName(@PathVariable("fn") String fn)throws NameNotFoundException {
		List<Actor> actors = (List<Actor>)iactorService.findActorsByFirstName(fn);
		if(actors==null || actors.isEmpty())
		{
			throw new NameNotFoundException("firstname not found: "+fn);
		}else {
		return new ResponseEntity<List<Actor>>(actors, HttpStatus.OK);}
	}
	
	//Get Films By Id --------GET
	@GetMapping("/{id}/films")
    public ResponseEntity<List<Film>> getFilmsByActorId(@PathVariable("id") short id) {
        List<Film> films = iactorService.getFilmsByActorId(id);
        if (films.isEmpty()) {
            throw new DataNotFoundException("Films not found for actor with id: " + id);
        }
        return new ResponseEntity<>(films, HttpStatus.OK);
    }
	
	//Find Actors by Id --------GET
	@GetMapping("/{id}")
    public ResponseEntity<Actor> getActorById(@PathVariable("id") short actorId) {
        Actor actorOptional = iactorService.findByActorsId(actorId);
        if (actorOptional!=null) {
            return new ResponseEntity<>(actorOptional, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
	
	//Update LastName ----PUT
	@PutMapping("/update/lastName")
	public ResponseEntity<Actor> updateLastNameOfActor( @RequestBody Actor actor) throws IdNotFoundException{
		Actor actor1=iactorService.updateLastNameById(actor.getLastName(), actor.getActorId());
		if(iactorService.findByActorsId(actor.getActorId())!=null) {
			if(actor1 !=null) {
				return new ResponseEntity<Actor>(actor1,HttpStatus.OK);
			}else {
				throw new IdNotFoundException("Cannot Update Lastname");
			}
		}else {
			throw new IdNotFoundException("ActorId not found");
		}
	}
	//Update firstName ----PUT
	@PutMapping("/update/firstName")
	public ResponseEntity<Actor> updateFirstNameOfActor(@RequestBody Actor actor)throws IdNotFoundException {
		Actor actor1=iactorService.updateFirstNameById(actor.getFirstName(), actor.getActorId());
		if(iactorService.findByActorsId(actor.getActorId())!=null) {
			if(actor1 !=null) {
				return new ResponseEntity<Actor>(actor1,HttpStatus.OK);
			}else {
				throw new IdNotFoundException("Cannot Update Firstname");
			}
		}else {
			throw new IdNotFoundException("ActorId not found");
		}
	}
	// TOP 10 actor by filmCount ---GET
    @GetMapping("/toptenbyfilmcount")
	public ResponseEntity<List<Top10ActorDetails>> getTopTenActorsByFilmCount() {
		List<Top10ActorDetails> actors =iactorService.findTopTenActorsByFilmCount();
		if(actors.isEmpty()) {
			throw new DataNotFoundException("No Actor Found ");
		}
		return new ResponseEntity<>(actors, HttpStatus.OK);
    }
  //Assign Film to a Actor---------PUT
  	@PutMapping("/film")
  	public ResponseEntity<List<Film>> assignFilmToActor(@RequestBody FilmsProjectRequestBody request) {
          if (request.getActorId() != null) {
              List<Film> films = filmActorService.assignFilmToAnActor(request.getActorId(), request.getFilm());
              return new ResponseEntity<>(films, HttpStatus.OK);
          } else {
              throw new IdNotFoundException("Actor Id not provided");
          }

  	}
  	
  	//Get All Actors --------GET
  	@GetMapping("/allActors")
  	public List<Actor> getAllActors() {
		List<Actor> actors= iactorService.getAllActors();
		
		if(actors==null || actors.isEmpty()) {
			throw new DataNotFoundException("No Actor Found ");
		}
		return actors;
	}
  	
  	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteActorById(@PathVariable("id") short id){
		if(iactorService.findByActorsId(id)!= null) {
			Boolean isDeleted = iactorService.deleteActor(id);
			if(isDeleted == false) {
				throw new DeletionException("Actor cannot be deleted");
			}else {
				return ResponseEntity.status(HttpStatus.OK).body("Record Deleted Successfully");
			}
		}else {
			throw new IdNotFoundException("Actor Id not found");
		}
		
	}
	
}	

	

	



	



