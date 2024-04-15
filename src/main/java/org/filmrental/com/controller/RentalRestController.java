package org.filmrental.com.controller;

import java.util.List;

import org.filmrental.com.dto.RentalDue;
import org.filmrental.com.exception.DataNotFoundException;
import org.filmrental.com.exception.IdNotFoundException;
import org.filmrental.com.exception.PostException;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.Rental;
import org.filmrental.com.service.IRentalService;
import org.filmrental.com.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = {"http://localhost:4200"}, allowCredentials = "true")
@RestController  //Indicates controller class
@RequestMapping("/api/rental")
public class RentalRestController {
	
	@Autowired  // Auto wiring  Rental Service
	IRentalService rentalservice;
	

		
	@PostMapping("/add") // Mapping for adding new rental
	public ResponseEntity<String> addNewRental(@RequestBody Rental rentalModel) {
	    try {
	        rentalservice.addRental(rentalModel);
	        return new ResponseEntity<String>("Rental added successfully!", HttpStatus.OK);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ResponseEntity<String>("Error adding rental!: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@GetMapping("/customer/{id}")  // Mapping for getting rentals by customer id
	public ResponseEntity<List<Film>> getbycustomerid(@PathVariable("id") Short customerId) throws IdNotFoundException{

		List<Film> rental = rentalservice.getbycustomerid(customerId);
		if(rental == null || rental.isEmpty()) {
			throw new IdNotFoundException("Customer Id not found");
		}else {
			return new ResponseEntity<List<Film>>(rental, HttpStatus.OK);
		}
	}
		
	
	
	@GetMapping("/toptenfilms")  // Mapping for finding overall top 10 rented films
	public ResponseEntity<List<Film>> findTop10RentedFilm() throws DataNotFoundException {
	   
	        List<Film> top10Films = rentalservice.getTop10RentedFilms();
	        if (top10Films.isEmpty() || top10Films == null) {
	            throw new DataNotFoundException("Error finding top 10 films!"); 
		    
	    } else {
	        return new ResponseEntity<List<Film>>(top10Films, HttpStatus.OK);

	    }
	}
	
	
	
	@GetMapping("/toptenfilms/store/{id}") // Mapping for finding top 10 rented films by store id
	public ResponseEntity<List<Film>> findTop10ByStoreIdOrderByRentalRate(@PathVariable("id") int id) throws IdNotFoundException{
		List<Film> top10Filmstostore = rentalservice.getTopTenFilmsRentedOfAStore(id);
		if(top10Filmstostore.isEmpty() || top10Filmstostore == null) {
			throw new IdNotFoundException("Store Id not found");
		}else {
		return new ResponseEntity<List<Film>>(top10Filmstostore, HttpStatus.OK);
		}

	}
	
	
		@PutMapping("update/returndate/{id}") // Mapping for updating return date
		public ResponseEntity<String> updateReturnDate(@PathVariable("id") int rentalId, @RequestBody Rental rental)throws IdNotFoundException{
			Rental rental1 =rentalservice.updateRentalReturnDate(rentalId,rental.getReturnDate());
			if (rental1 == null || rental1.getRentalId()!=rentalId) {
				throw new IdNotFoundException("Rental Id not found");
			}else {
			return new ResponseEntity<String>("Rental Return date updated successfully!", HttpStatus.OK);
		}
	}
		
		
		
		@GetMapping("/due/store/{id}") // Mapping for getting customers details with pending returns by store id
		public ResponseEntity<List<RentalDue>> getCustomersWithPendingReturns(@PathVariable("id") int storeId)  {
		    List<RentalDue> rentalDues = rentalservice.getCustomersWithPendingReturns(storeId);
		    if (rentalDues == null) {
		        throw new IdNotFoundException("No customers with pending returns found for store ID: " + storeId);
		    } else {
		        return new ResponseEntity<List<RentalDue>>(rentalDues, HttpStatus.OK);
		    }
		}
		
		@GetMapping("/request/count") // Mapping for getting customers details with pending returns by store id
		public ResponseEntity<Integer> customerRentalRequestCount()  {
		    int count = rentalservice.countCustomerRentalRequest();
		    if (count == 0) {
		        throw new DataNotFoundException("No customers with pending reuqets");
		    } else {
		        return new ResponseEntity<Integer>(count, HttpStatus.OK);
		    }
		}
		
		@GetMapping("/films/storebyname/{storeName}")
		public ResponseEntity<List<Film>> getFilmsRentedOfAStore(@PathVariable String storeName){
			List<Film> Filmtostores = rentalservice.getFilmsRentedOfAStore(storeName);
			if(Filmtostores.isEmpty() || Filmtostores == null) {
				throw new DataNotFoundException("Store name not found");
			}else {
				return new ResponseEntity<List<Film>>(Filmtostores, HttpStatus.OK);
			}
			
		}

		
		
}



		
		
	
	


