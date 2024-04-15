package org.filmrental.com.model;
 
import jakarta.persistence.*;
 
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
import java.util.List;
 
import org.springframework.web.bind.annotation.CrossOrigin;
 
import java.time.LocalDateTime;
 
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 
@CrossOrigin(origins ={"http://localhost:4200"},allowCredentials="true")
@Entity
@Table(indexes = {
	    @Index(name = "idx_actor_last_name", columnList = "last_name")
	})
 
public class Actor {
 
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short actorId;
 
    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;
 
    @Column(name = "last_name", nullable = false, length = 45)
    private String lastName;
 
    @Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate = LocalDateTime.now();
 
    @JsonIgnore
    @OneToMany(mappedBy = "actor", targetEntity = FilmActor.class)
    private List<FilmActor> filmActor;
 
 
	public Short getActorId() {
		return actorId;
	}
 
 
	public void setActorId(Short actorId) {
		this.actorId = actorId;
	}
 
 
	public String getFirstName() {
		return firstName;
	}
 
 
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
 
 
	public String getLastName() {
		return lastName;
	}
 
 
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
 
 
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
 
 
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
 
 
	public List<FilmActor> getFilmActor() {
		return filmActor;
	}
 
 
	public void setFilmActor(List<FilmActor> filmActor) {
		this.filmActor = filmActor;
	}
 
 
	public Actor() {
		super();
	}
 
 
	public Actor(Short actorId, String firstName, String lastName, LocalDateTime lastUpdate,
			List<FilmActor> filmActor) {
		super();
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastUpdate = lastUpdate;
		this.filmActor = filmActor;
	}
 
 
	public Actor(Short actorId, String firstName, String lastName, LocalDateTime lastUpdate) {
		super();
		this.actorId = actorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.lastUpdate = lastUpdate;
	}


}