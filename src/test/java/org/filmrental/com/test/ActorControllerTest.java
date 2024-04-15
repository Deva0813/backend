package org.filmrental.com.test;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.filmrental.com.dao.IActorDao;
import org.filmrental.com.dao.IFilmActorDao;
import org.filmrental.com.dao.IFilmDao;
import org.filmrental.com.dto.FilmsProjectRequestBody;
import org.filmrental.com.dto.Top10ActorDetails;
import org.filmrental.com.model.Actor;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.FilmActor;
import org.filmrental.com.model.MovieRating;
import org.filmrental.com.service.ActorServiceImpl;
import org.filmrental.com.service.FilmActorServiceImpl;
import org.filmrental.com.service.IActorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
public class ActorControllerTest {
	
	    @Mock
	    private IFilmDao filmDao;
	    
	    @Mock
	    private IActorDao actorDao;
	    
	    @InjectMocks
	    private ActorServiceImpl actorService;
	    
	    @InjectMocks
		private FilmActorServiceImpl filmActorServiceImpl;
	    
	    @Mock
		private IFilmActorDao filmActorDao;
	    
	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
	    
	    //ADD ACTOR
	    @Test
	    public void testAddActor() {
	    	// Prepare test data
	        Actor actor = new Actor();
	        actor.setFirstName("John");
	        actor.setLastName("Doe");
	        actor.setLastUpdate(LocalDateTime.now());
	        actor.setFilmActor(new ArrayList<>());
	        when(actorDao.save(any(Actor.class))).thenReturn(actor);
	        Actor savedActor = actorService.addActor(actor);
	        verify(actorDao, times(1)).save(actor);
	        // Verify the result
	        assertEquals(actor, savedActor);
	    }
	    
	    //FIND ACTOR BY LASTNAME
	    @Test
	    public void testFindActorsByLastName() {
	    	// Prepare test data
	        Actor actor1 = new Actor();
	        actor1.setActorId((short) 1);
	        actor1.setFirstName("John");
	        actor1.setLastName("Doe");
	        actor1.setLastUpdate(LocalDateTime.now());
	        actor1.setFilmActor(new ArrayList<>());
	        Actor actor2 = new Actor();
	        actor2.setActorId((short) 2);
	        actor2.setFirstName("Jane");
	        actor2.setLastName("Doe");
	        actor2.setLastUpdate(LocalDateTime.now());
	        actor2.setFilmActor(new ArrayList<>());
	        List<Actor> actorsWithLastNameDoe = new ArrayList<>();
	        actorsWithLastNameDoe.add(actor1);
	        actorsWithLastNameDoe.add(actor2);
	        when(actorDao.findByLastName("Doe")).thenReturn(actorsWithLastNameDoe);
	        // Call the method to test
	        List<Actor> foundActors = actorService.findActorsByLastName("Doe");
	        verify(actorDao, times(1)).findByLastName("Doe");
	        // Verify the result
	        assertEquals(actorsWithLastNameDoe, foundActors);
	    }
	    
	    //FIND ACTOR BY FIRSTNAME
	    @Test
	    public void testFindActorsByFirstName() {
	    	// Prepare test data
	        Actor actor1 = new Actor();
	        actor1.setActorId((short) 1);
	        actor1.setFirstName("John");
	        actor1.setLastName("Doe");
	        actor1.setLastUpdate(LocalDateTime.now());
	        actor1.setFilmActor(new ArrayList<>());
	        Actor actor2 = new Actor();
	        actor2.setActorId((short) 2);
	        actor2.setFirstName("John");
	        actor2.setLastName("Smith");
	        actor2.setLastUpdate(LocalDateTime.now());
	        actor2.setFilmActor(new ArrayList<>());
	        List<Actor> actorsWithFirstNameJohn = new ArrayList<>();
	        actorsWithFirstNameJohn.add(actor1);
	        actorsWithFirstNameJohn.add(actor2);
	        when(actorDao.findByFirstName("John")).thenReturn(actorsWithFirstNameJohn);
	        // Call the method to test
	        List<Actor> foundActors = actorService.findActorsByFirstName("John");
	        verify(actorDao, times(1)).findByFirstName("John");
	        // Verify the result
	        assertEquals(actorsWithFirstNameJohn, foundActors);
	        
	    }
	    
	    //FILMS BY ACTOR ID
	    @Test
	    public void testGetFilmsByActorId() {
	        // Prepare test data
	        int actorId = 1;
	        List<Film> expectedFilms = new ArrayList<>();
	        // Mock behavior
	        when(actorDao.getFilmsByActorId(actorId)).thenReturn(expectedFilms);
	        List<Film> actualFilms = actorService.getFilmsByActorId(actorId);
	        // Verify the result
	        assertEquals(expectedFilms, actualFilms);
	        verify(actorDao, times(1)).getFilmsByActorId(actorId);
	    }
	    
	    //UPDATE LASTNAME
	    @Test
	    public void testUpdateLastNameById() {
	        // Prepare test data
	        Actor actor=new Actor();
	        actor.setActorId((short)5);
	        actor.setLastName("John");
	        
	        when(actorDao.findById((short)5)).thenReturn(Optional.of(actor));
	        
	        
	        short actorId=5;
	        String newLastName="Michael";
	        
	        Actor actor2=actorService.updateLastNameById(newLastName,actorId);
	        // Verify the result
	        assertEquals("Michael",actor2.getLastName());
	        
	    }
	    
	    //UPDATE FIRSTNAME
	    @Test
	    public void testUpdateFirstNameById() {
	        // Prepare test data
	        Actor actor=new Actor();
	        actor.setActorId((short)5);
	        actor.setFirstName("John");
	        when(actorDao.findById((short)5)).thenReturn(Optional.of(actor));
	        short actorId=5;
	        String newFirstName="Michael";
	        Actor actor2=actorService.updateFirstNameById(newFirstName,actorId);
	        // Verify the result
	        assertEquals("Michael",actor2.getFirstName());
	        
	    }
	    
	    //TOP 10 ACTORS BY FILM COUNT
	    @Test
	    public void testFindTopTenActorsByFilmCount() {
	        // Mock ActorDao
	        IActorDao actorDao = mock(IActorDao.class);

	        List<Top10ActorDetails> sampleData = new ArrayList<>();
	        sampleData.add(new Top10ActorDetails((short) 1, "John Doe", 5L));         
	        sampleData.add(new Top10ActorDetails((short) 2, "Jane Smith", 3L));
	        when(actorDao.findTopTenActorsByFilmCount()).thenReturn(sampleData);

	        ActorServiceImpl actorService = new ActorServiceImpl();
            actorService.setActorDao(actorDao);

	        List<Top10ActorDetails> result = actorService.findTopTenActorsByFilmCount();

	        // Verify the result
	        assertEquals(sampleData.size(), result.size());
	        for (int i = 0; i < sampleData.size(); i++) {        
	        assertEquals(sampleData.get(i).actorId(), result.get(i).actorId());            
	        assertEquals(sampleData.get(i).name(), result.get(i).name());           
	        assertEquals(sampleData.get(i).numberOfFilms(), result.get(i).numberOfFilms()); 
	        }
	    }
	    @Test
	    public void assignFilmToAnActorTest() {
	        Short filmId = 990;
	        Short actorId = 192;
	        Film film = new Film(); 
	        film.setFilmId(filmId);
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
	        when(filmActorDao.findByActorActorId(actorId)).thenReturn(filmActors);
	        List<Film> result = filmActorServiceImpl.assignFilmToAnActor(actorId, film);
	        verify(filmActorDao, times(1)).save(any(FilmActor.class));
	        assertEquals(1, result.size());
	        assertEquals(film, result.get(0));
	    }
	   
}


