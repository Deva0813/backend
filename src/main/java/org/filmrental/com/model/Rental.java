package org.filmrental.com.model;
import java.sql.Timestamp;
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
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
//@Table(uniqueConstraints = {
//	    @UniqueConstraint(name = "unique_rental", columnNames = {"rental_date", "inventory_id", "customer_id"})
//	})
public class Rental {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int rentalId;
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime rentalDate=LocalDateTime.now();
		@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
		private LocalDateTime returnDate;
		@Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    private LocalDateTime lastUpdate = LocalDateTime.now();
 
		@JsonIgnore
		@OneToMany(targetEntity=Payment.class,mappedBy="rental")
		private List<Payment> payment;
		@ManyToOne
		@JoinColumn(name="staff_id")
		private Staff staff;
		@ManyToOne
		@JoinColumn(name="inventory_id")
		private Inventory inventory;
		@ManyToOne
		@JoinColumn(name="customer_id")
		private Customer customer;
 
		public int getRentalId() {
			return rentalId;
		}
 
		public void setRentalId(int rentalId) {
			this.rentalId = rentalId;
		}
 
		public LocalDateTime getRentalDate() {
			return rentalDate;
		}
 
		public void setRentalDate(LocalDateTime rentalDate) {
			this.rentalDate = rentalDate;
		}
 
		public LocalDateTime getReturnDate() {
			return returnDate;
		}
 
		public void setReturnDate(LocalDateTime returnDate) {
			this.returnDate = returnDate;
		}
 
		public LocalDateTime getLastUpdate() {
			return lastUpdate;
		}
 
		public void setLastUpdate(LocalDateTime lastUpdate) {
			this.lastUpdate = lastUpdate;
		}
 
		public List<Payment> getPayment() {
			return payment;
		}
 
		public void setPayment(List<Payment> payment) {
			this.payment = payment;
		}
 
		public Staff getStaff() {
			return staff;
		}
 
		public void setStaff(Staff staff) {
			this.staff = staff;
		}
 
		public Inventory getInventory() {
			return inventory;
		}
 
		public void setInventory(Inventory inventory) {
			this.inventory = inventory;
		}
 
		public Customer getCustomer() {
			return customer;
		}
 
		public void setCustomer(Customer customer) {
			this.customer = customer;
		}
 
		public Rental(int rentalId, LocalDateTime rentalDate, LocalDateTime returnDate, LocalDateTime lastUpdate,
				List<Payment> payment, Staff staff, Inventory inventory, Customer customer) {
			super();
			this.rentalId = rentalId;
			this.rentalDate = rentalDate;
			this.returnDate = returnDate;
			this.lastUpdate = lastUpdate;
			this.payment = payment;
			this.staff = staff;
			this.inventory = inventory;
			this.customer = customer;
		}
 
		public Rental() {
			super();
		}

}