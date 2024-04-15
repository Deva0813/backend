package org.filmrental.com.controller;

import java.util.List;



import org.filmrental.com.dto.AddressStoreDetails;
import org.filmrental.com.dto.CustomerDetails;
import org.filmrental.com.dto.ManagerDetails;
import org.filmrental.com.dto.StaffDetails;
import org.filmrental.com.dto.StoreDetails;

import org.filmrental.com.exception.DataNotFoundException;
import org.filmrental.com.exception.PostException;
import org.filmrental.com.exception.UpdationErrorException;
import org.filmrental.com.model.Address;
import org.filmrental.com.model.Customer;
import org.filmrental.com.model.Staff;
import org.filmrental.com.model.Store;
import org.filmrental.com.service.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/store")
@RestController
public class StoreRestController {
	@Autowired
	private IStoreService istoreservice;
	
	//creating store
	
	@PostMapping("/post")
	public ResponseEntity<String> createStore(@RequestBody Store store)throws PostException{
		
	        Store store2 = istoreservice.addStore(store);
	        
	        if(store2 == null)
	            throw new PostException("Store Creation Error!");
	        
	        return new ResponseEntity<>("Record created successfully", HttpStatus.CREATED);
	    
	}
	
	//get stores by city name
	
	@GetMapping("/city/{city}")
	public ResponseEntity<List<AddressStoreDetails>> getStoresByCity(@PathVariable("city") String city) throws DataNotFoundException {
		List<AddressStoreDetails> stores = istoreservice.getStoresByCity(city);
		if(stores == null || stores.isEmpty())
			throw new DataNotFoundException("store not found");
		
		return new ResponseEntity<List<AddressStoreDetails>>(stores, HttpStatus.OK);
	}
	
	//get stores by country name
	
	@GetMapping("/country/{country}")
	public ResponseEntity<List<AddressStoreDetails>> getStoresByCountry(@PathVariable("country") String country)throws DataNotFoundException {
		List<AddressStoreDetails> stores = istoreservice.getStoresByCountry(country);
		if(stores == null || stores.isEmpty())
			throw new DataNotFoundException("store not found");
		
		return new ResponseEntity<List<AddressStoreDetails>>(stores, HttpStatus.OK);
	}
	
	//get all staff details by store id
		@GetMapping("/staff/{storeId}")
	   
		public ResponseEntity<List<StaffDetails>> getAllStaffByStoreId(@PathVariable("storeId") Integer storeId) {
			List<StaffDetails> staffList = istoreservice.getAllStaffByStoreId(storeId);
			if(staffList==null || staffList.isEmpty()) {
				return new ResponseEntity("staff not found",HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<StaffDetails>>(staffList, HttpStatus.OK);
		}
		

		//get stores by phonenumber
		
		
		@GetMapping("/phone/{phone}")
		public ResponseEntity<List<AddressStoreDetails>> getStoreByPhoneNumber(@PathVariable("phone") String phone)throws DataNotFoundException {
			List<AddressStoreDetails> stores = istoreservice.getStoreByPhoneNumber(phone);
			if(stores == null || stores.isEmpty())
				throw new DataNotFoundException("store not found");
			
			return new ResponseEntity<List<AddressStoreDetails>>(stores, HttpStatus.OK);
		}
		
		
		//update phone number by storeid
		
		@PutMapping("/update/{storeId}/{phone}")
	   
		public ResponseEntity<StoreDetails> updateStorePhoneNumber(@PathVariable("storeId") Integer storeId,@PathVariable("phone") String phone) throws UpdationErrorException{
			istoreservice.updateStorePhoneNumber(storeId, phone);
			 // Call the service method to update the store's phone number
		    StoreDetails updatedStoreDetails = istoreservice.updateStorePhoneNumber(storeId, phone);
		    
		    // Check if the store was not found
		    if (updatedStoreDetails == null) {
		        throw new UpdationErrorException("Store not found");
		    }
		    
		    // Return the updated store details
		    return new ResponseEntity<>(updatedStoreDetails, HttpStatus.OK);
		}
		
		//assign address to a store
		@PutMapping("{storeId}/address")
	   
		public ResponseEntity<AddressStoreDetails> assignAddressToStore(@PathVariable("storeId") Integer storeId, @RequestBody Address address)throws UpdationErrorException {
			AddressStoreDetails addressStoreDetails=istoreservice.assignAddressToStore(storeId, address);
			Store store = istoreservice.getStoreById(storeId);
			
			if(store==null)
				throw new UpdationErrorException("store not found");
			
			return new ResponseEntity<AddressStoreDetails>(addressStoreDetails, HttpStatus.OK);
		}
		
		
		//Display all Customers of a Store
		
		@GetMapping("/customer/{storeId}")
	    
		public ResponseEntity<List<CustomerDetails>> getCustomersByStoreId(@PathVariable("storeId") Integer storeId)throws DataNotFoundException {
			List<CustomerDetails> customers = istoreservice.getCustomersByStoreId(storeId);
			if(customers==null || customers.isEmpty() )
				throw new DataNotFoundException("customers not found");
			
			return new ResponseEntity<List<CustomerDetails>>(customers, HttpStatus.OK);
		}
		//assign manager to a store
		@PutMapping("{storeId}/manager/{manager_staff_id}")
	   
		public ResponseEntity<Store> assignManagerToStore(
				@PathVariable("storeId") Integer storeId,@PathVariable("manager_staff_id") Byte manager_staff_id)throws DataNotFoundException  {
			Store store = istoreservice.assignManagerToStore(manager_staff_id,storeId);
			if(store == null) {
				throw new UpdationErrorException("Updation Error to assign staff to store");
			}else {
				return new ResponseEntity<Store>(store,HttpStatus.OK);
			}
		
		}
		//get manager details for that store id
		
      @GetMapping("/manager/{storeId}")
	    
		public ResponseEntity<StaffDetails> getManagerDetailsOfAStore(@PathVariable("storeId") Integer storeId)throws DataNotFoundException {
    	  StaffDetails staff = istoreservice.getManagerDetailsOfAStore(storeId);
			if(staff==null)
				throw new DataNotFoundException("staffs not found");
			
			return new ResponseEntity<StaffDetails>(staff, HttpStatus.OK);
		}
      
      //get all manager details
      @GetMapping("/managers")
	    
		public ResponseEntity<List<ManagerDetails>> getManagerAndStoreDetails()throws DataNotFoundException {
    	  List<ManagerDetails> details = istoreservice.getManagerAndStoreDetails();
			if(details==null || details.isEmpty())
				throw new DataNotFoundException("staffs not found");
			
			return new ResponseEntity<List<ManagerDetails>>(details, HttpStatus.OK);
		}


}




