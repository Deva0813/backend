package org.filmrental.com.dto;

import org.filmrental.com.model.Film;

public class FilmsProjectRequestBody {
	private Short actorId;
	private Film film;
	public FilmsProjectRequestBody(Short actorId, Film film) {
		super();
		this.actorId = actorId;
		this.film = film;
	}
	public FilmsProjectRequestBody() {
		super();
	}
	public Short getActorId() {
		return actorId;
	}
	public void setActorId(Short actorId) {
		this.actorId = actorId;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	
}
