package org.filmrental.com.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.filmrental.com.dao.IRentalDao;
import org.filmrental.com.dto.RentalDue;
import org.filmrental.com.model.Customer;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.Inventory;
import org.filmrental.com.model.Rental;
import org.filmrental.com.model.Staff;
import org.filmrental.com.service.RentalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class RentalControllerTest {
	@Mock
	private IRentalDao irentaldao;

	@InjectMocks
	private RentalServiceImpl rentalServiceImpl;
	
	@BeforeEach
	void setUp() {
	    MockitoAnnotations.openMocks(this);
	}

	@Test // Test method for renting a film
    void rentFilmTest() {
        Rental rental = new Rental();
        rental.setRentalId(1);
        rental.setReturnDate(LocalDateTime.now());
        rental.setInventory(new Inventory());
        rental.setCustomer(new Customer());
        rental.setReturnDate(LocalDateTime.now());
        rental.setStaff(new Staff());
        rental.setLastUpdate(LocalDateTime.now()); 
        when(irentaldao.save(rental)).thenReturn(rental); 
        Rental result = rentalServiceImpl.addRental(rental); 
        assertEquals(rental, result);
        verify(irentaldao, times(1)).save(rental);
    }
	
	
	@Test // Test method for getting top 10 rented films
    void getTopTenRentedFilmsTest() {
        List<Film> films = new ArrayList<>();
        Film film1 = new Film();
        film1.setTitle("Film 1");
        film1.setDescription("Description 1");
        films.add(film1);
        when(irentaldao.findTop10RentedFilm()).thenReturn(films);
        List<Film> result = rentalServiceImpl.getTop10RentedFilms();
        assertEquals(films, result);
        verify(irentaldao, times(1)).findTop10RentedFilm();
    }
	
	@Test // Test method for getting top 10 rented films by store
    void getTopTenRentedFilmsByStoreTest() {
        int storeId = 1;
        List<Film> films = new ArrayList<>();
        Film film1 = new Film();
        film1.setTitle("Film 1");
        film1.setDescription("Description 1");
        films.add(film1);
        when(irentaldao.getTopTenFilmsRentedOfAStore(storeId)).thenReturn(films);
        List<Film> result = rentalServiceImpl.getTopTenFilmsRentedOfAStore(storeId);
        assertEquals(films, result);
        verify(irentaldao, times(1)).getTopTenFilmsRentedOfAStore(storeId);
    }
	
	@Test  // Test method for getting films rented by a customer
    void getFilmsRentedByCustomerTest() {
        int customerId = 1;
        List<Film> films = new ArrayList<>();
        Film film1 = new Film();
        film1.setTitle("Film 1");
        film1.setDescription("Description 1");
        films.add(film1);
        when(irentaldao.findAllFilmsRentedToCustomer(customerId)).thenReturn(films);
        List<Film> result = rentalServiceImpl.getAllFilmsRentedToCustomer(customerId);
        assertEquals(films, result);
        verify(irentaldao, times(1)).findAllFilmsRentedToCustomer(customerId);
    }
	

	
	@Test // Test method for updating return date of a rental
	void updateReturnDateTest() {
	    Integer rentalId = 1;
	    Rental rental = new Rental();
	    rental.setRentalId(rentalId);
	    rental.setReturnDate(LocalDateTime.now()); 
	    rental.setInventory(new Inventory());
	    rental.setCustomer(new Customer());
	    rental.setReturnDate(LocalDateTime.now()); 
	    rental.setStaff(new Staff());
	    rental.setLastUpdate(LocalDateTime.now()); 
	    Optional<Rental> optionalRental = Optional.of(rental); 
	    when(irentaldao.findById(rentalId)).thenReturn(optionalRental); 
	    when(irentaldao.save(rental)).thenReturn(rental);
	    Rental result = rentalServiceImpl.updateRentalReturnDate(rentalId,LocalDateTime.now());
	    assertEquals(rental, result);
	    verify(irentaldao, times(1)).findById(rentalId);
	    verify(irentaldao, times(1)).save(rental);
	}
	
	
	
	
	@Test //	Test method to get customer with pending returns
    public void testFindCustomersWithPendingReturns() {
        int storeId = 1;       
        List<RentalDue> mockResult = new ArrayList<>();
        mockResult.add(new RentalDue((short) 1, "John", "Doe", "john.doe@example.com", (byte) 1, LocalDateTime.now()));
        mockResult.add(new RentalDue((short) 2, "Jane", "Doe", "jane.doe@example.com", (byte) 1, LocalDateTime.now()));
        when(irentaldao.findCustomersWithPendingReturns(storeId)).thenReturn(mockResult); // assuming storeId = 1
        List<RentalDue> result = rentalServiceImpl.getCustomersWithPendingReturns(storeId);
        assertEquals(2, result.size()); 
    }
}


