package org.filmrental.com.dto;

import org.filmrental.com.model.Actor;

public class FilmActorDetails {
	private Actor actors;

	public FilmActorDetails(Actor actors) {
		super();
		this.actors = actors;
	}

	public FilmActorDetails() {
		super();
	}

	public Actor getActors() {
		return actors;
	}

	public void setActors(Actor actors) {
		this.actors = actors;
	}
	
	
}
