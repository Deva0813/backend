package org.filmrental.com.model;
 
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
 
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
@Table(indexes = {
    @Index(name = "idx_title", columnList = "title")
})
 
@JsonIgnoreProperties({"filmActor", "filmCategory", "inventory"})
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short filmId;
 
    @Column(length = 128, nullable = false, name = "title")
    private String title;
 
    @Column(columnDefinition = "TEXT")
    private String description;
 
 
    @Column(nullable = false, name = "release_year",columnDefinition = "YEAR")
    private Integer releaseYear;
 
    @Column(nullable = false, name = "rental_duration")
    private Byte rentalDuration;
 
 
    @Column(name = "length")
    private Short length;
 
    @Column(precision = 5, scale = 2, name = "rental_rate")
    private BigDecimal rentalRate;
 
    @Column(precision = 5, scale = 2, name = "replacement_cost")
    private BigDecimal replacementCost;
 
    @Enumerated(EnumType.STRING)
    private MovieRating rating = MovieRating.G;
 
    @Column(name = "special_features", columnDefinition = "SET('Trailers','Commentaries','Deleted Scenes','Behind the Scenes') DEFAULT NULL")
    private String specialFeatures;
 
    @Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate = LocalDateTime.now();
 
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;
 
    @ManyToOne
    @JoinColumn(name = "original_language_id", columnDefinition = "TINYINT")
    private Language originalLanguage;
    @Lob
	@Column(columnDefinition = "BLOB")
	private byte[] picture=null;
    @Transient
    private String base64Image;

    public byte[] getPicture() {
		return picture;
	}
 
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
 
	public String getBase64Image() {
		return base64Image;
	}
 
	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}
 
	@JsonIgnore
    @OneToMany(targetEntity = FilmActor.class, mappedBy = "film")
    private List<FilmActor> filmActor;
    @JsonIgnore
    @OneToMany(targetEntity = FilmCategory.class, mappedBy = "film")
    private List<FilmCategory> filmCategory;
    @JsonIgnore
    @OneToMany(targetEntity = Inventory.class, mappedBy = "film")
    private List<Inventory> inventory;
 
	public Short getFilmId() {
		return filmId;
	}
 
	public void setFilmId(Short filmId) {
		this.filmId = filmId;
	}
 
	public String getTitle() {
		return title;
	}
 
	public void setTitle(String title) {
		this.title = title;
	}
 
	public String getDescription() {
		return description;
	}
 
	public void setDescription(String description) {
		this.description = description;
	}
 
	public Integer getReleaseYear() {
		return releaseYear;
	}
 
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
 
	public Byte getRentalDuration() {
		return rentalDuration;
	}
 
	public void setRentalDuration(Byte rentalDuration) {
		this.rentalDuration = rentalDuration;
	}
 
	public Short getLength() {
		return length;
	}
 
	public void setLength(Short length) {
		this.length = length;
	}
 
	public BigDecimal getRentalRate() {
		return rentalRate;
	}
 
	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}
 
	public BigDecimal getReplacementCost() {
		return replacementCost;
	}
 
	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}
 
	public MovieRating getRating() {
		return rating;
	}
 
	public void setRating(MovieRating rating) {
		this.rating = rating;
	}
 
	public String getSpecialFeatures() {
		return specialFeatures;
	}
 
	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}
 
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
 
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
 
	public Language getLanguage() {
		return language;
	}
 
	public void setLanguage(Language language) {
		this.language = language;
	}
 
	public Language getOriginalLanguage() {
		return originalLanguage;
	}
 
	public void setOriginalLanguage(Language originalLanguage) {
		this.originalLanguage = originalLanguage;
	}
 
	public List<FilmActor> getFilmActor() {
		return filmActor;
	}
 
	public void setFilmActor(List<FilmActor> filmActor) {
		this.filmActor = filmActor;
	}
 
	public List<FilmCategory> getFilmCategory() {
		return filmCategory;
	}
 
	public void setFilmCategory(List<FilmCategory> filmCategory) {
		this.filmCategory = filmCategory;
	}
 
	public List<Inventory> getInventory() {
		return inventory;
	}
 
	public void setInventory(List<Inventory> inventory) {
		this.inventory = inventory;
	}
 
	public Film(Short filmId, String title, String description, Integer releaseYear, Byte rentalDuration, Short length,
			BigDecimal rentalRate, BigDecimal replacementCost, MovieRating rating, String specialFeatures,
			LocalDateTime lastUpdate, Language language, Language originalLanguage, List<FilmActor> filmActor,
			List<FilmCategory> filmCategory, List<Inventory> inventory) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.rentalDuration = rentalDuration;
		this.length = length;
		this.rentalRate = rentalRate;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.lastUpdate = lastUpdate;
		this.language = language;
		this.originalLanguage = originalLanguage;
		this.filmActor = filmActor;
		this.filmCategory = filmCategory;
		this.inventory = inventory;
	}

 
	public Film(Short filmId, String title, String description, Integer releaseYear, Byte rentalDuration, Short length,
			BigDecimal rentalRate, BigDecimal replacementCost, MovieRating rating, String specialFeatures) {
		super();
		this.filmId = filmId;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.rentalDuration = rentalDuration;
		this.length = length;
		this.rentalRate = rentalRate;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
	}
 
	public Film() {
		super();
	}
	public Film(String title) {
		super();
		this.title = title;
	}
}