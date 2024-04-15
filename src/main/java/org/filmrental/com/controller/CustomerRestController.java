package org.filmrental.com.controller;

import java.util.List;


import org.filmrental.com.exception.DataNotFoundException;
import org.filmrental.com.exception.IdNotFoundException;
import org.filmrental.com.exception.UpdationErrorException;
import org.filmrental.com.model.Actor;
import org.filmrental.com.model.Address;
import org.filmrental.com.model.Customer;
import org.filmrental.com.model.Store;
import org.filmrental.com.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/customers")
@RestController
public class CustomerRestController {
	@Autowired
	private ICustomerService icustomerService;
	

	
	@PostMapping
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
		//add new customers
		icustomerService.addCustomer(customer);
		return new ResponseEntity<String>("Record Created Successfully",HttpStatus.OK);
	}

	@GetMapping("/firstname/{fn}")
	public ResponseEntity<List<Customer>> getCustomerByFirstName(@PathVariable("fn") String fn){
		//find the firstName of customer
		List<Customer> customerFirstName = icustomerService.findByFirstName(fn);
		if (customerFirstName.isEmpty()) {
            throw new  DataNotFoundException("Entered Firstname = " + fn + " is not present.");
        }
		return new ResponseEntity<List<Customer>>(customerFirstName,HttpStatus.OK);	
	}
	@GetMapping("/lastname/{ln}")
	public ResponseEntity<List<Customer>> getCustomerByLastName(@PathVariable("ln") String ln){
		//find the lastName of customer
		List<Customer> customerLastName = icustomerService.findByLastName(ln);
		if (customerLastName.isEmpty()) {
            throw new  DataNotFoundException("Entered Lastname = " + ln + " is not present.");
        }
		return new ResponseEntity<List<Customer>>(customerLastName,HttpStatus.OK);	
	}
		
	
	@GetMapping("/email/{email}")
	public ResponseEntity<List<Customer>> getCustomerByEmail(@PathVariable("email") String email){
		List<Customer> customerEmail = icustomerService.findByEmail(email);
		//find the emailId of customer
		if (customerEmail.isEmpty()) {
            throw new  DataNotFoundException("Entered Email = " + email + " is not present.");
        }
		return new ResponseEntity<List<Customer>>(customerEmail, HttpStatus.OK);
	}
	
	@GetMapping("/city/{city}")
	public ResponseEntity<List<Customer>> findByCity(@PathVariable("city") String city)throws DataNotFoundException{
		//find Address by City
		List<Customer> customer = icustomerService.findByAddressCityCity(city);
		if (!customer.isEmpty()) {
			return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Customer is not found for this city");
		}}
	
	
	@GetMapping("/country/{country}")
	public ResponseEntity<List<Customer>> findByCountry(@PathVariable("country") String country)throws DataNotFoundException{
		List<Customer> customer = icustomerService.findByAddressCityCountryCountry(country);
		//find Address by Country
		if (!customer.isEmpty()) {
			return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Customer is not found for this country");
		}
	}
	@GetMapping("/active")
	public ResponseEntity<List<Customer>> findAllActiveCustomer(){
		//get Active customers
		List<Customer> customer = icustomerService.findAllActiveCustomer();
		if (customer.isEmpty()) {
            throw new  DataNotFoundException("No customer is active");
        }
		return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
	
}
	
	@GetMapping("/inactive")
	public ResponseEntity<List<Customer>> findAllInActiveCustomer(){
		//get Inactive customers
		List<Customer> customer = icustomerService.findAllInActiveCustomer();
		if (customer.isEmpty()) {
            throw new  DataNotFoundException("All customers are active no in is inactive");
        }
		return new ResponseEntity<List<Customer>>(customer, HttpStatus.OK);
	
}
	@GetMapping("/phone/{phone}")
	public ResponseEntity<Customer> getCustomerByPhoneNumber(@PathVariable("phone") String phone){
		//get the phone number of customers
		Customer customer = icustomerService.findByAddressPhone(phone);
		if (customer==null) {
            throw new  DataNotFoundException("Entered phone = " + phone + " is not present.");
        }
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@PutMapping("/update/firstName")
	//Update first name of Customers
	public ResponseEntity<Customer> updateFirstNameOfCustomer(@RequestBody Customer customer)throws IdNotFoundException {
		Customer customer1=icustomerService.updateFirstNameOfCustomer(customer.getFirstName(), customer.getCustomerId());
		if(icustomerService.getCustomerById(customer.getCustomerId())!=null) {
			if(customer1 !=null) {
				return new ResponseEntity<Customer>(customer1,HttpStatus.OK);
			}else {
				throw new UpdationErrorException("Cannot Update Firstname");
			}
		}else {
			throw new IdNotFoundException("ActorId not found");
		}
	}
	
	@PutMapping("/update/lastName")
	//Update last name of Customers
	public ResponseEntity<Customer> updateLastNameOfCustomer(@RequestBody Customer customer)throws IdNotFoundException {
		Customer customer1=icustomerService.updateLastNameOfCustomer(customer.getLastName(), customer.getCustomerId());
		if(icustomerService.getCustomerById(customer.getCustomerId())!=null) {
			if(customer1 !=null) {
				return new ResponseEntity<Customer>(customer1,HttpStatus.OK);
			}else {
				throw new UpdationErrorException("Cannot Update Lastname");
			}
		}else {
			throw new IdNotFoundException("ActorId not found");
		}
	}
	
	@PutMapping("/update/email")
	//Update emailId of Customers
	public ResponseEntity<Customer> updateEmailNameOfCustomer(@RequestBody Customer customer)throws IdNotFoundException {
		Customer customer1=icustomerService.updateEmailOfCustomer(customer.getEmail(), customer.getCustomerId());
		if(icustomerService.getCustomerById(customer.getCustomerId())!=null) {
			if(customer1 !=null) {
				return new ResponseEntity<Customer>(customer1,HttpStatus.OK);
			}else {
				throw new UpdationErrorException("Cannot Update Email");
			}
		}else {
			throw new IdNotFoundException("Customer not found");
		}
	}
	
	@PutMapping("/update/phone")
	//Update phone Number of Customers
	public ResponseEntity<Customer> updatePhoneNameOfCustomer(@RequestBody Customer customer)throws IdNotFoundException {
		Customer customer1=icustomerService.updatePhoneNumberOfCustomer(customer.getAddress().getPhone(), customer.getCustomerId());
		if(icustomerService.getCustomerById(customer.getCustomerId())!=null) {
			if(customer1 !=null) {
				return new ResponseEntity<Customer>(customer1,HttpStatus.OK);
			}else {
				throw new UpdationErrorException("Cannot Update Phone Number");
			}
		}else {
			throw new IdNotFoundException("Customer not found");
		}
	}
	
	@PutMapping("/customers/{customerId}/store")
	//Assign store to a Customer
    public ResponseEntity<String> updateCustomerStore(@RequestParam("customerId") int customerId, @RequestBody Store store) {
       
		 Customer assignedCustomer=  icustomerService.assignStoreToCustomer(customerId, store.getStoreId());
            if (assignedCustomer != null) {
            return ResponseEntity.ok("Store assigned successfully to customer.");
            }else {
            throw new IdNotFoundException("Customer not found");
            }
        }
    
@PutMapping("/customers/{customerId}/address")
//Assign Address to a Customer
public ResponseEntity<Customer> assignAddressToCustomer(
        @RequestParam("customerId") byte customerId,
        @RequestBody Address address) {
    
    Customer assignedCustomer = icustomerService.assignAddressToCustomer(customerId, address);
    
    if (assignedCustomer != null) {
        return ResponseEntity.ok(assignedCustomer);
    } else {
    	
    	throw new IdNotFoundException("Customer not found");
        
    }
}
}



