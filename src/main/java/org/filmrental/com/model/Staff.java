package org.filmrental.com.model;
import java.sql.Blob;
import java.time.LocalDate;
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
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Staff {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Byte staffId;
	private String firstName;
	private String lastName;
	@Lob
	private Blob picture;
	@Column(nullable=false,unique=true)
	private String email;	
	private int active;
	@Column(unique = true)
	private String username;
	private String password;
	@Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate = LocalDateTime.now();
	@ManyToOne
	@JoinColumn(name="store_id")
	private Store stores;
	@ManyToOne
	@JoinColumn(name="address_id")
	private  Address address;
	@JsonIgnore
	@OneToMany(targetEntity=Store.class,mappedBy="managerStaff")
	private List<Store> store;
	@JsonIgnore
	@OneToMany(targetEntity=Rental.class,mappedBy="staff")
	private List<Rental> rental;
	@JsonIgnore
	@OneToMany(targetEntity = Payment.class,mappedBy="staff")
	private List<Payment> payment;

 
 
	public Byte getStaffId() {
		return staffId;
	}
 
	public void setStaffId(Byte staffId) {
		this.staffId = staffId;
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
 
	public Blob getPicture() {
		return picture;
	}
 
	public void setPicture(Blob picture) {
		this.picture = picture;
	}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	public int getActive() {
		return active;
	}
 
	public void setActive(int active) {
		this.active = active;
	}
 
	public String getUserName() {
		return username;
	}
 
	public void setUserName(String username) {
		this.username = username;
	}
 
	public String getPassword() {
		return password;
	}
 
	public void setPassword(String password) {
		this.password = password;
	}
 
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
 
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
 
	public Store getStores() {
		return stores;
	}
 
	public void setStores(Store stores) {
		this.stores = stores;
	}
 
	public Address getAddress() {
		return address;
	}
 
	public void setAddress(Address address) {
		this.address = address;
	}
 
	public List<Store> getStore() {
		return store;
	}
 
	public void setStore(List<Store> store) {
		this.store = store;
	}
 
	public List<Rental> getRental() {
		return rental;
	}
 
	public void setRental(List<Rental> rental) {
		this.rental = rental;
	}
 
	public List<Payment> getPayment() {
		return payment;
	}
 
	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}
 
	public Staff(Byte staffId, String firstName, String lastName, Blob picture, String email, int active,
			String username, String password, LocalDateTime lastUpdate, Store stores, Address address,
			List<Store> store, List<Rental> rental, List<Payment> payment) {
		super();
		this.staffId = staffId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.picture = picture;
		this.email = email;
		this.active = active;
		this.username = username;
		this.password = password;
		this.lastUpdate = lastUpdate;
		this.stores = stores;
		this.address = address;
		this.store = store;
		this.rental = rental;
		this.payment = payment;
	}
 
	public Staff() {
		super();
	}

}