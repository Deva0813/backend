package org.filmrental.com.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
 
import com.fasterxml.jackson.annotation.JsonFormat;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@IdClass(FilmCategoryId.class)
public class FilmCategory implements Serializable{	
	@Id
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	@Id
	@ManyToOne
	@JoinColumn(name="film_id")
	private Film film;
	@Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate = LocalDateTime.now();
 
	public FilmCategory(Category category, Film film, LocalDateTime lastUpdate) {
		super();
		this.category = category;
		this.film = film;
		this.lastUpdate = lastUpdate;
	}
 
	public FilmCategory() {
		super();
	}
 
	public Category getCategory() {
		return category;
	}
 
	public void setCategory(Category category) {
		this.category = category;
	}
 
	public Film getFilm() {
		return film;
	}
 
	public void setFilm(Film film) {
		this.film = film;
	}
 
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
 
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}


}