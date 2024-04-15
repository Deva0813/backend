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
public class City {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Short cityId;
        @Column(length = 25,nullable = false,name = "city")
        private String city;
        @Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime lastUpdate = LocalDateTime.now();
 
    	@JsonIgnore
        @OneToMany(targetEntity = Address.class,mappedBy = "city")
        private List<Address> address;
        @ManyToOne
        @JoinColumn(name="country_id", nullable=false)
        private Country country;
 
		public City(Short cityId, String city, LocalDateTime lastUpdate, List<Address> address, Country country) {
			super();
			this.cityId = cityId;
			this.city = city;
			this.lastUpdate = lastUpdate;
			this.address = address;
			this.country = country;
		}
 
		public City() {
			super();
		}

 
 
		public Short getCityId() {
			return cityId;
		}
 
		public void setCityId(Short cityId) {
			this.cityId = cityId;
		}
 
		public String getCity() {
			return city;
		}
 
		public void setCity(String city) {
			this.city = city;
		}
 
		public LocalDateTime getLastUpdate() {
			return lastUpdate;
		}
 
		public void setLastUpdate(LocalDateTime lastUpdate) {
			this.lastUpdate = lastUpdate;
		}
 
		public List<Address> getAddress() {
			return address;
		}
 
		public void setAddress(List<Address> address) {
			this.address = address;
		}
 
		public Country getCountry() {
			return country;
		}
 
		public void setCountry(Country country) {
			this.country = country;
		}
 
		
}