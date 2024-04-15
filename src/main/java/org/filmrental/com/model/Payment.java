package org.filmrental.com.model;
 
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
 
import com.fasterxml.jackson.annotation.JsonFormat;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Entity
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	@Column(precision = 5, scale = 2)
	private BigDecimal amount;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime paymentDate=LocalDateTime.now();
	@Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate = LocalDateTime.now();
 
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	@ManyToOne
	@JoinColumn(name="staff_id")
	private Staff staff;
	@ManyToOne
	@JoinColumn(name="rental_id")
	private Rental rental;
 
	public Payment(int paymentId, BigDecimal amount, LocalDateTime paymentDate, LocalDateTime lastUpdate,
			Customer customer, Staff staff, Rental rental) {
		super();
		this.paymentId = paymentId;
		this.amount = amount;
		this.paymentDate = paymentDate;
		this.lastUpdate = lastUpdate;
		this.customer = customer;
		this.staff = staff;
		this.rental = rental;
	}
 
	public Payment() {
		super();
	}
 
	public int getPaymentId() {
		return paymentId;
	}
 
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
 
	public BigDecimal getAmount() {
		return amount;
	}
 
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
 
	public LocalDateTime getPaymentDate() {
		return paymentDate;
	}
 
	public void setPaymentDate(LocalDateTime paymentDate) {
		this.paymentDate = paymentDate;
	}
 
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
 
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
 
	public Customer getCustomer() {
		return customer;
	}
 
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
 
	public Staff getStaff() {
		return staff;
	}
 
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
 
	public Rental getRental() {
		return rental;
	}
 
	public void setRental(Rental rental) {
		this.rental = rental;
	}

 
 
}