package org.filmrental.com.model;
 
import java.io.Serializable;
 
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
 
public class FilmActorId implements Serializable {
	//@Id
	private Short actor;
	//@Id
	private Short film;
 
	public FilmActorId(Short actor, Short film) {
		super();
		this.actor = actor;
		this.film = film;
	}
 
	public FilmActorId() {
		super();
	}
 
	public Short getActor() {
		return actor;
	}
 
	public void setActor(Short actor) {
		this.actor = actor;
	}
 
	public Short getFilm() {
		return film;
	}
 
	public void setFilm(Short film) {
		this.film = film;
	}

 
//	public Actor getActor() {
//		return actor;
//	}
//
//	public void setActor(Actor actor) {
//		this.actor = actor;
//	}
//
//	public Film getFilm() {
//		return film;
//	}
//
//	public void setFilm(Film film) {
//		this.film = film;
//	}
//
//	public FilmActorId(Actor actor, Film film) {
//		super();
//		this.actor = actor;
//		this.film = film;
//	}
 
	
}