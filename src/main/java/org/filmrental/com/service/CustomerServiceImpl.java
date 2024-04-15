package org.filmrental.com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.filmrental.com.dao.IAddressDao;
import org.filmrental.com.dao.ICustomerDao;
import org.filmrental.com.dao.IStoreDao;
import org.filmrental.com.dto.FindAssignAddressToStaff;
import org.filmrental.com.exception.DataNotFoundException;
import org.filmrental.com.exception.IdNotFoundException;
//import org.filmrental.com.exception.CustomizedException;
import org.filmrental.com.model.Address;
import org.filmrental.com.model.Customer;
import org.filmrental.com.model.Staff;
import org.filmrental.com.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	
	@Autowired
	ICustomerDao customerDao;
	
	@Autowired
	IAddressDao addressDao;
	
	@Autowired
	IStoreDao  storeDao;

	@Override
	public Customer addCustomer(Customer customer) {
		Address address = customer.getAddress();
		addressDao.save(address);
		return customerDao.save(customer);
	}
	
	

	@Override
	public List<Customer> findByFirstName(String firstName) {
		return customerDao.findByFirstName(firstName);
	}
	
	@Override
	public List<Customer> findByLastName(String lastName) {
		return customerDao.findByLastName(lastName);
	}

	@Override
	public List<Customer> findByEmail(String email) {
		return customerDao.findByEmail(email);
	}
	
	@Override
	 
	public List<Customer> findByAddressCityCity(String city) {
		List<Customer> list = customerDao.findByAddressCityCity(city);
		return list;
	}
		
	@Override
	public List<Customer> findByAddressCityCountryCountry(String city) {
		//Search customer by Country
        List<Customer> list = customerDao.findByAddressCityCountryCountry(city);
		return list; 
	}
	@Override
	//Find All Active Customers
	public List<Customer> findAllActiveCustomer() {
		return customerDao.findAllActiveCustomer();
	}



	@Override
	public List<Customer> findAllInActiveCustomer() {
		return customerDao.findAllInActiveCustomer();
	}



	@Override
	public Customer findByAddressPhone(String phone) {
		return customerDao.findByAddressPhone(phone);
	}

	@Transactional
	@Override
	public Customer updateFirstNameOfCustomer(String firstName, Short customerId) {
		Customer customer = getCustomerById(customerId);
		if(customer != null) {
			customer.setFirstName(firstName);
			customer.setLastUpdate(LocalDateTime.now());
		}
		return customer;
	}
	
	@Transactional
	@Override
	public Customer updateLastNameOfCustomer(String lastName, Short customerId) {
		Customer customer = getCustomerById(customerId);
		if(customer != null) {
			customer.setLastName(lastName);
			customer.setLastUpdate(LocalDateTime.now());
		}
		return customer;
	}



	@Override
	public Customer getCustomerById(Short customerId) {
		Optional<Customer> optional =customerDao.findById(customerId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}


	@Transactional
	@Override
	public Customer updateEmailOfCustomer(String email, Short customerId) {
		Customer customer = getCustomerById(customerId);
		if(customer!=null) {
			customer.setEmail(email);
			customer.setLastUpdate(LocalDateTime.now());
		}
		return customer;
	}



	@Override
	public Customer updatePhoneNumberOfCustomer(String phone, Short customerId) {
		Customer customer = getCustomerById(customerId);
		if(customer!=null) {
			if(customer.getAddress().getAddressId()!=null) {
				customer.getAddress().setPhone(phone);
				customer.getAddress().setLastUpdate(LocalDateTime.now());
			}else {
				throw new IdNotFoundException("Address Id not found");
			}
		}
		return customer;
	}



	@Override
	public Customer assignAddressToCustomer(byte id, Address address) {
	    Optional<Customer> optionalCustomer = customerDao.findById((short) id);
	    
	    if (optionalCustomer.isPresent()) { // Checking if customer exists
	        Customer customer = optionalCustomer.get();
	        
	        // Set the address for the customer
	        customer.setAddress(address);
	        
	        // Save the customer
	        return customerDao.save(customer);
	    }
		return null; 
	}




	@Override
	public Customer  assignStoreToCustomer(int customerId, int storeId) {
	    Optional<Customer> optionalCustomer = customerDao.findById((short) customerId);
	    
	    if (optionalCustomer.isPresent()) { // Checking if customer exists
	        Customer customer = optionalCustomer.get();
	        Optional<Store> optionalStore = storeDao.findById(customerId);
	        
	        if (optionalStore.isPresent()) { // Checking if store exists
	            Store store1 = optionalStore.get();           
	            // Assigning store to customer
	            customer.setStore(store1);
	            customerDao.save(customer);
	        } 
	    }
		return null; 
	}

}




