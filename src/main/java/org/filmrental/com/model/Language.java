package org.filmrental.com.model;
 
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
 
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
public class Language {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Byte languageId;
	private String name;
	@Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate = LocalDateTime.now();
 
	
	@JsonIgnore
	@OneToMany(targetEntity = Film.class,mappedBy = "language")
	private List<Film> film;
	@JsonIgnore
	@OneToMany(mappedBy = "originalLanguage")
	private List<Film> originalLanguageFilm;
	public Byte getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Byte languageId) {
		this.languageId = languageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	public List<Film> getFilm() {
		return film;
	}
	public void setFilm(List<Film> film) {
		this.film = film;
	}
	public List<Film> getOriginalLanguageFilm() {
		return originalLanguageFilm;
	}
	public void setOriginalLanguageFilm(List<Film> originalLanguageFilm) {
		this.originalLanguageFilm = originalLanguageFilm;
	}
	public Language(Byte languageId, String name, LocalDateTime lastUpdate, List<Film> film,
			List<Film> originalLanguageFilm) {
		super();
		this.languageId = languageId;
		this.name = name;
		this.lastUpdate = lastUpdate;
		this.film = film;
		this.originalLanguageFilm = originalLanguageFilm;
	}
	public Language(Byte languageId, String name, LocalDateTime lastUpdate) {
		super();
		this.languageId = languageId;
		this.name = name;
		this.lastUpdate = lastUpdate;
	}
	public Language() {
		super();
	}

}