package org.filmrental.com.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.filmrental.com.dto.RentalDue;
import org.filmrental.com.model.Customer;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.Rental;

public interface IRentalService {
    public Rental addRental(Rental rental);
    public List<Film> getbycustomerid(Short customerId);
    public List<Film> getTop10RentedFilms();
    public List<Film> getTopTenFilmsRentedOfAStore(int storeId);
	public List<Film> getAllFilmsRentedToCustomer(int id);
    public Rental updateRentalReturnDate(int id, LocalDateTime localDateTime);
//	public List<Customer> getCustomersWithPendingReturns(int storeId);
    public List<RentalDue> getCustomersWithPendingReturns(int storeId);
    public int countCustomerRentalRequest();
    public List<Film> getFilmsRentedOfAStore(String storeName);

    
}

