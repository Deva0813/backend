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
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Country {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Short countryId;
        @Column(length = 50,nullable = false,name = "country")
        private String country;
        @Column(name = "last_update", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime lastUpdate = LocalDateTime.now();

        @JsonIgnore
        @OneToMany(targetEntity = City.class,mappedBy = "country")
        private List<City> city;


		public Country(Short countryId, String country, LocalDateTime lastUpdate, List<City> city) {
			super();
			this.countryId = countryId;
			this.country = country;
			this.lastUpdate = lastUpdate;
			this.city = city;
		}

		

		public Short getCountryId() {
			return countryId;
		}


		public void setCountryId(Short countryId) {
			this.countryId = countryId;
		}


		public String getCountry() {
			return country;
		}


		public void setCountry(String country) {
			this.country = country;
		}


		public LocalDateTime getLastUpdate() {
			return lastUpdate;
		}


		public void setLastUpdate(LocalDateTime lastUpdate) {
			this.lastUpdate = lastUpdate;
		}


		public List<City> getCity() {
			return city;
		}


		public void setCity(List<City> city) {
			this.city = city;
		}


		public Country() {
			super();
		}
        
        
        
        
}