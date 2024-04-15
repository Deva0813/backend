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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
 
@Entity
public class Address {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Short addressId;
        @Column(length = 50,nullable = false,name = "address")
		private String address;
        @Column(length = 50,name = "address2")
        private String address2;
        @Column(length = 20,name = "district",nullable = false)
        private String district;
        @Column(length = 10,name = "postal_code")
        private String postalCode;
        @Column(length = 20,name = "phone",nullable = false)
        private String phone;
        @Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime lastUpdate = LocalDateTime.now();
        @JsonIgnore
        @OneToMany(targetEntity = Staff.class,mappedBy = "address")
        private List<Staff> staff;
 
        @JsonIgnore
        @OneToMany(targetEntity = Store.class,mappedBy = "address")
        private List<Store> store;
 
        @JsonIgnore
        @OneToMany(targetEntity = Customer.class,mappedBy = "address")
        private List<Customer> customer;
 
        @ManyToOne
		@JoinColumn(name="city_id")
		private City city;
 
		public Short getAddressId() {
			return addressId;
		}
 
		public void setAddressId(Short addressId) {
			this.addressId = addressId;
		}
 
		public String getAddress() {
			return address;
		}
 
		public void setAddress(String address) {
			this.address = address;
		}
 
		public String getAddress2() {
			return address2;
		}
 
		public void setAddress2(String address2) {
			this.address2 = address2;
		}
 
		public String getDistrict() {
			return district;
		}
 
		public void setDistrict(String district) {
			this.district = district;
		}
 
		public String getPostalCode() {
			return postalCode;
		}
 
		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}
 
		public String getPhone() {
			return phone;
		}
 
		public void setPhone(String phone) {
			this.phone = phone;
		}
 
		public LocalDateTime getLastUpdate() {
			return lastUpdate;
		}
 
		public void setLastUpdate(LocalDateTime lastUpdate) {
			this.lastUpdate = lastUpdate;
		}
 
		public List<Staff> getStaff() {
			return staff;
		}
 
		public void setStaff(List<Staff> staff) {
			this.staff = staff;
		}
 
		public List<Store> getStore() {
			return store;
		}
 
		public void setStore(List<Store> store) {
			this.store = store;
		}
 
		public List<Customer> getCustomer() {
			return customer;
		}
 
		public void setCustomer(List<Customer> customer) {
			this.customer = customer;
		}
 
		public City getCity() {
			return city;
		}
 
		public void setCity(City city) {
			this.city = city;
		}
 
		public Address(Short addressId, String address, String address2, String district, String postalCode,
				String phone, LocalDateTime lastUpdate, List<Staff> staff, List<Store> store, List<Customer> customer,
				City city) {
			super();
			this.addressId = addressId;
			this.address = address;
			this.address2 = address2;
			this.district = district;
			this.postalCode = postalCode;
			this.phone = phone;
			this.lastUpdate = lastUpdate;
			this.staff = staff;
			this.store = store;
			this.customer = customer;
			this.city = city;
		}
 
		public Address() {
			super();
		}
 
		public Address(String address) {
			super();
			this.address = address;
		}


}