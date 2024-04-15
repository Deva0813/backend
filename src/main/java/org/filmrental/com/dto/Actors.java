package org.filmrental.com.dto;

import org.filmrental.com.model.Actor;

public class Actors {
	private Short filmId;
	private Actor actor;
	public Actors(Short filmId, Actor actor) {
		super();
		this.filmId = filmId;
		this.actor = actor;
	}
	public Actors() {
		super();
	}
	public Short getFilmId() {
		return filmId;
	}
	public void setFilmId(Short filmId) {
		this.filmId = filmId;
	}
	public Actor getActor() {
		return actor;
	}
	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	
}
