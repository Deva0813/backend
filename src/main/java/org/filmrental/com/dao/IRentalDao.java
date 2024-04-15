package org.filmrental.com.dao;

import java.util.List;

import org.filmrental.com.dto.RentalDue;
import org.filmrental.com.model.Customer;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.Rental;
import org.filmrental.com.model.Store;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IRentalDao extends CrudRepository<Rental,Integer>{

	@Query("select r.inventory.film from Rental r where r.customer.customerId=:id")
	List<Film> getbycustomerid(@Param("id") Short customerId);

	@Query("select r.inventory.film from Rental r order by r.inventory.film.rentalRate desc")
	List<Film> findTop10RentedFilm();

	@Query("select r.inventory.film from Rental r where r.inventory.store.storeId = :id order by r.inventory.film.rentalRate DESC")
    List<Film> getTopTenFilmsRentedOfAStore(@Param("id")int storeId);
	
	@Query("select r.inventory.film from Rental r where r.customer.customerId=:id")
	List<Film> findAllFilmsRentedToCustomer(@Param("id") int id);
	
//	@Query("select r.customer from Rental r WHERE r.returnDate IS NULL AND r.inventory.store.id = :storeId")
//    List<Customer> findCustomersWithPendingReturns(@Param("storeId") int storeId);
	
	@Query("select new org.filmrental.com.dto.RentalDue(r.customer.customerId,r.customer.firstName,r.customer.lastName,r.customer.email,r.customer.active,r.customer.createDate) from Rental r WHERE r.returnDate IS NULL AND r.inventory.store.id = :storeId")
	List<RentalDue> findCustomersWithPendingReturns(@Param("storeId") int storeId);
	
	//additional endpoint for displaying number of customers requested for renting the film
	@Query("select count(r.rentalId) from Rental r where r.staff.staffId is null")
	int countCustomerRentalRequest();
	
	@Query("select r.inventory.film from Rental r where r.inventory.store.storeName = :storeName")
    List<Film> getFilmsRentedOfAStore(@Param("storeName")String storeName);
	
	
}
