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
@Table(uniqueConstraints = {
	    @UniqueConstraint(name = "idx_unique_manager", columnNames = {"managerStaff"})
	})
public class Store {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int storeId;
	@Column(name = "store_name")
	private String storeName;
	@Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdate = LocalDateTime.now();
 
	
	@JsonIgnore
	@OneToMany(targetEntity = Inventory.class,mappedBy = "store")
	private List<Inventory> inventory;
	@JsonIgnore
	@OneToMany(targetEntity = Customer.class,mappedBy = "store")
	private List<Customer> customer;
	@ManyToOne
	@JoinColumn(name="address_id")
	private Address address;
	@JsonIgnore
	@OneToMany(targetEntity = Staff.class,mappedBy = "stores")
	private List<Staff> staffs;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "manager_staff_id")
	private Staff managerStaff;
 
	public int getStoreId() {
		return storeId;
	}
 
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
 
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
 
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
 
	public List<Inventory> getInventory() {
		return inventory;
	}
 
	public void setInventory(List<Inventory> inventory) {
		this.inventory = inventory;
	}
 
	public List<Customer> getCustomer() {
		return customer;
	}
 
	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}
 
	public Address getAddress() {
		return address;
	}
 
	public void setAddress(Address address) {
		this.address = address;
	}
 
	public List<Staff> getStaffs() {
		return staffs;
	}
 
	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
	}
 
	public Staff getManagerStaff() {
		return managerStaff;
	}
 
	public void setManagerStaff(Staff managerStaff) {
		this.managerStaff = managerStaff;
	}
 
	public Store(int storeId, LocalDateTime lastUpdate, List<Inventory> inventory, List<Customer> customer,
			Address address, List<Staff> staffs, Staff managerStaff) {
		super();
		this.storeId = storeId;
		this.lastUpdate = lastUpdate;
		this.inventory = inventory;
		this.customer = customer;
		this.address = address;
		this.staffs = staffs;
		this.managerStaff = managerStaff;
	}
 
	public Store() {
		super();
	}
 
	public String getStoreName() {
		return storeName;
	}
 
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

}