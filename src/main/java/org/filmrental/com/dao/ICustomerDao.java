package org.filmrental.com.dao;

import java.util.List;
import java.util.Optional;

import org.filmrental.com.model.Address;
import org.filmrental.com.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


	@Repository
	public interface ICustomerDao extends CrudRepository<Customer,Short>{
		//find By First Name
		List<Customer> findByFirstName(String firstName);
		
		//find By Last Name
		List<Customer> findByLastName(String lastName);
		
		//find By Email
    	List<Customer> findByEmail(String email);
    	
    	//find Address by City
    	List<Customer> findByAddressCityCity(String city);
    	
    	////find Address by Country
    	List<Customer> findByAddressCityCountryCountry(String country);
    	
    	
    	//find All Active Customers
    	@Query(value = "SELECT * FROM Customer where active = 1", nativeQuery = true)
    	List<Customer> findAllActiveCustomer();
    	
    	//find all InActive Customers
    	@Query(value = "SELECT * FROM Customer where active = 0", nativeQuery = true)
    	List<Customer> findAllInActiveCustomer();
    	
    	//Find phone Number of Customer
    	@Query("SELECT c FROM Customer c WHERE c.address.phone=:phone")
		public Customer findByAddressPhone(@Param("phone") String phone);

}



