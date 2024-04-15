package org.filmrental.com.model;


import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = {
	    @Index(name = "idx_actor_last_name", columnList = "last_name")
	})

public class Customer {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Short customerId;
	    @Column(length = 45,nullable = false,name = "first_name")
        private String firstName;
	    @Column(length = 45,nullable = false,name = "last_name")
        private String lastName;
	    @Column(length = 50,name = "email")
        private String email;
	    @Column(length = 1,nullable = false,name = "active")
        private Byte active;
	    @Column(nullable = false,name = "create_date")
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createDate = LocalDateTime.now();   
	    @Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    private LocalDateTime lastUpdate = LocalDateTime.now();

	    @JsonIgnore
        @OneToMany(targetEntity = Payment.class,mappedBy = "customer")
        private List<Payment> payment;
	    
	    @JsonIgnore
        @OneToMany(targetEntity = Rental.class,mappedBy = "customer")
        private List<Rental> rental;
	    
        @ManyToOne
		@JoinColumn(name="store_id")
		private Store store;
        
        @ManyToOne
		@JoinColumn(name="address_id")
		private Address address;

		public Customer(Short customerId, String firstName, String lastName, String email, Byte active,
				LocalDateTime createDate, LocalDateTime lastUpdate, List<Payment> payment, List<Rental> rental,
				Store store, Address address) {
			super();
			this.customerId = customerId;
			this.firstName = firstName;
			this.lastName = lastName;
			this.email = email;
			this.active = active;
			this.createDate = createDate;
			this.lastUpdate = lastUpdate;
			this.payment = payment;
			this.rental = rental;
			this.store = store;
			this.address = address;
		}

		public Customer() {
			super();
		}

		public Short getCustomerId() {
			return customerId;
		}

		public void setCustomerId(Short customerId) {
			this.customerId = customerId;
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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public Byte getActive() {
			return active;
		}

		public void setActive(Byte active) {
			this.active = active;
		}

		public LocalDateTime getCreateDate() {
			return createDate;
		}

		public void setCreateDate(LocalDateTime createDate) {
			this.createDate = createDate;
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

		public List<Rental> getRental() {
			return rental;
		}

		public void setRental(List<Rental> rental) {
			this.rental = rental;
		}

		public Store getStore() {
			return store;
		}

		public void setStore(Store store) {
			this.store = store;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}
        
        
}