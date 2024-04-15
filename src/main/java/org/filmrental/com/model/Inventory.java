package org.filmrental.com.model;
 
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
 
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "MEDIUMINT")
	private Integer inventoryId;
	@Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate = LocalDateTime.now();
 
	
	@ManyToOne
	@JoinColumn(name="film_id")
	private Film film;
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store store;
	@JsonIgnore
	@OneToMany(targetEntity=Rental.class,mappedBy="inventory")
	private List<Rental> rental;
 
 
	public Integer getInventoryId() {
		return inventoryId;
	}
 
 
	public void setInventoryId(Integer inventoryId) {
		this.inventoryId = inventoryId;
	}
 
 
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
 
 
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
 
 
	public Film getFilm() {
		return film;
	}
 
 
	public void setFilm(Film film) {
		this.film = film;
	}
 
 
	public Store getStore() {
		return store;
	}
 
 
	public void setStore(Store store) {
		this.store = store;
	}
 
 
	public List<Rental> getRental() {
		return rental;
	}
 
 
	public void setRental(List<Rental> rental) {
		this.rental = rental;
	}
 
 
	public Inventory(Integer inventoryId, LocalDateTime lastUpdate, Film film, Store store, List<Rental> rental) {
		super();
		this.inventoryId = inventoryId;
		this.lastUpdate = lastUpdate;
		this.film = film;
		this.store = store;
		this.rental = rental;
	}
 
 
	public Inventory() {
		super();
	}

 
}