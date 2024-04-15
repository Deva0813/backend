package org.filmrental.com.model;
 
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
 
import com.fasterxml.jackson.annotation.JsonFormat;
 
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@IdClass(FilmActorId.class)
public class FilmActor{
	@Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate = LocalDateTime.now();
	@Id
	@ManyToOne
	@JoinColumn(name="actor_id")
	private Actor actor;
	@Id
	@ManyToOne
	@JoinColumn(name = "film_id")
	private Film film;
 
	public FilmActor(LocalDateTime lastUpdate, Actor actor, Film film) {
		super();
		this.lastUpdate = lastUpdate;
		this.actor = actor;
		this.film = film;
	}
 
	public FilmActor() {
		super();
	}
 
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
 
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
 
	public Actor getActor() {
		return actor;
	}
 
	public void setActor(Actor actor) {
		this.actor = actor;
	}
 
	public Film getFilm() {
		return film;
	}
 
	public void setFilm(Film film) {
		this.film = film;
	}


}