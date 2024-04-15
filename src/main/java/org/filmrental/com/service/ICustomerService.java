package org.filmrental.com.service;

import java.util.List;

//import org.filmrental.com.exception.CustomizedException;
import org.filmrental.com.model.Address;
import org.filmrental.com.model.Customer;
import org.filmrental.com.model.Store;
import org.springframework.data.repository.CrudRepository;


public interface ICustomerService {
	//Add new customer to database
	public Customer addCustomer(Customer customer);
	
	//get customer by id
	public Customer getCustomerById(Short customerId);
	
	//Search customer by first name
	public List<Customer> findByFirstName(String firstName);
	
	//Search customer by last name
	public List<Customer> findByLastName(String lastName);
	
	//Search customer by email
	public List<Customer> findByEmail(String email);
	
	//Search customer by city
	public List<Customer> findByAddressCityCity(String city);
	
	//Search customer by country
	public List<Customer> findByAddressCityCountryCountry(String country);
	
    //Search All Active Customers
	public List<Customer> findAllActiveCustomer();
	
	//Search All InActive Customers
	public List<Customer> findAllInActiveCustomer();
	
    
    //Search customer by phone
	public Customer findByAddressPhone(String phone);
	
	//Update first name of customer
	public Customer updateFirstNameOfCustomer(String firstName, Short customerId);
	
	//Update last name of customer
	public Customer updateLastNameOfCustomer(String lastName, Short customerId);
	
	//Update email of customer
	public Customer updateEmailOfCustomer(String email,Short customerId);
	
	//update phone number of customer
	public Customer updatePhoneNumberOfCustomer(String phone,Short customerId);
	
	
	//Assign store to customer
	public Customer assignStoreToCustomer(int customerId, int i);
	
    //Assign address to customer
	public Customer assignAddressToCustomer(byte id, Address address);

	
}

