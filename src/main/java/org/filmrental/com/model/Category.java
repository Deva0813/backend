package org.filmrental.com.model;
 
import java.time.LocalDateTime;
import java.util.List;
 
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Byte categoryId;
	@Column(length = 25,nullable = false,name = "name")
	private String name;
	@Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate = LocalDateTime.now();
	@JsonIgnore
	@OneToMany(targetEntity = FilmCategory.class,mappedBy = "category")
	private List<FilmCategory> filmCategory;
 
	public Byte getCategoryId() {
		return categoryId;
	}
 
	public void setCategoryId(Byte categoryId) {
		this.categoryId = categoryId;
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
 
	public List<FilmCategory> getFilmCategory() {
		return filmCategory;
	}
 
	public void setFilmCategory(List<FilmCategory> filmCategory) {
		this.filmCategory = filmCategory;
	}
 
	public Category(Byte categoryId, String name, LocalDateTime lastUpdate, List<FilmCategory> filmCategory) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.lastUpdate = lastUpdate;
		this.filmCategory = filmCategory;
	}
	public Category(Byte categoryId, String name, LocalDateTime lastUpdate) {
		super();
		this.categoryId = categoryId;
		this.name = name;
		this.lastUpdate = lastUpdate;
	}
 
	public Category() {
		super();
	}

 
}