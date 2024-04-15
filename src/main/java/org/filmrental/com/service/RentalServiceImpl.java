package org.filmrental.com.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.filmrental.com.dao.IRentalDao;
import org.filmrental.com.dto.RentalDue;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //service class
public class RentalServiceImpl implements IRentalService{
	
	
	@Autowired // Auto wiring Rental DAO
	private IRentalDao irentaldao;
	
	
	
	@Override // Adding new rental
	public Rental addRental(Rental rental) {		
		return irentaldao.save(rental);
		 
	}
	@Override // getting films rented by a particular customer
	public List<Film> getbycustomerid(Short customerId) {
		
		return irentaldao.getbycustomerid(customerId);
	}
	@Override
	public List<Film> getTop10RentedFilms() // getting top 10 rented films
	{
		
			List<Film> allFilms = irentaldao.findTop10RentedFilm();
			// Trimming list to give only the top 10 films
			if (allFilms.size() > 10) {
				List<Film> top10films = new ArrayList<Film>();
				for (int i = 0; i < 10; i++) {
					top10films.add(allFilms.get(i));
				}
				return top10films;
			}
			return allFilms;
	}
	
	@Override  // getting top 10 films rented from a store
	public List<Film> getTopTenFilmsRentedOfAStore(int storeId) {
		List<Film> filmsFromStore = irentaldao.getTopTenFilmsRentedOfAStore(storeId);
		// Trimming list to give only the top 10 films
		if (filmsFromStore.size() > 10) {
			List<Film> top10films = new ArrayList<Film>();
			for (int i = 0; i < 10; i++) {
				top10films.add(filmsFromStore.get(i));
			}
			return top10films;
		}
		return filmsFromStore;
	}	
	
	
	
	@Override  // getting all films rented to a customer
	public List<Film> getAllFilmsRentedToCustomer(int rentalId) {
		if (irentaldao.findById(rentalId).isEmpty() == true) {
		}
		return irentaldao.findAllFilmsRentedToCustomer(rentalId);

	}

	@Override  // Method to update rental return date
	public  Rental updateRentalReturnDate(int id, LocalDateTime returnDate) {
		
			Optional<Rental> opt = irentaldao.findById(id);
			if (opt.isPresent()) {

				Rental rentalToUpdate = opt.get();
				rentalToUpdate.setReturnDate(returnDate);
				return irentaldao.save(rentalToUpdate);
				 
			}
			return null;

	}

	@Override //getting customers with pending returns
	public List<RentalDue> getCustomersWithPendingReturns(int storeId) {
	    List<RentalDue> list = irentaldao.findCustomersWithPendingReturns(storeId);
	    if (!list.isEmpty()) {
	        return list;
	    } else {
	        return null; 	  
	        }
	}
	
	//count of customer rental request
	@Override
	public int countCustomerRentalRequest() {
		return irentaldao.countCustomerRentalRequest();
	}
	
	@Override
	public List<Film> getFilmsRentedOfAStore(String storeName) {
		
		return irentaldao.getFilmsRentedOfAStore(storeName);

	  
}
}
	
	


