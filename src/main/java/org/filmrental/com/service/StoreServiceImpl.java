package org.filmrental.com.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.filmrental.com.dao.IAddressDao;
import org.filmrental.com.dao.ICityDao;
import org.filmrental.com.dao.ICountryDao;
import org.filmrental.com.dao.ICustomerDao;
import org.filmrental.com.dao.IStaffDao;
import org.filmrental.com.dao.IStoreDao;
import org.filmrental.com.dto.AddressStoreDetails;
import org.filmrental.com.dto.CustomerDetails;
import org.filmrental.com.dto.ManagerDetails;
import org.filmrental.com.dto.StaffDetails;
import org.filmrental.com.dto.StoreDetails;
import org.filmrental.com.exception.DataNotFoundException;
import org.filmrental.com.exception.IdNotFoundException;
import org.filmrental.com.exception.InvalidAssignmentException;
import org.filmrental.com.model.Address;
import org.filmrental.com.model.City;
import org.filmrental.com.model.Country;
import org.filmrental.com.model.Customer;
import org.filmrental.com.model.Staff;
import org.filmrental.com.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
@Service
public class StoreServiceImpl implements IStoreService{
	@Autowired
	private IStoreDao istoredao;
   @Autowired
   private IStaffDao istaffdao;
   @Autowired
   private IAddressDao iaddressdao;
   @Autowired 
   private ICityDao cityDao;
   
   @Autowired 
   private ICountryDao countryDao;
   
   
   

	//to retrive storeid
	@Override
	public Store getStoreById(Integer id)  {
		Store store = istoredao.findByStoreId(id);

		if (store == null) {
		  return null;
		}

		return store;
	}
	
	
	//adding stores to database
	@Override
	public Store addStore(Store store) {
		// TODO Auto-generated method stub
		Country country = store.getAddress().getCity().getCountry();
    	countryDao.save(country);
    	City city = store.getAddress().getCity();
    	cityDao.save(city);
    	Address address = store.getAddress();
    	iaddressdao.save(address);
    	Staff managerStaff = istaffdao.findStaffWhoAreNotAssignedToAStore(store.getManagerStaff().getStaffId());
    	if(managerStaff != null) {
    		managerStaff.setStores(store);
    		store.setManagerStaff(managerStaff);
    	}else {
    		throw new InvalidAssignmentException("Cant assign the staff to this store already working in another store"); 
    	}
    	
    	return istoredao.save(store);
	}

	
	//get stores by city name
	@Override
	public List<AddressStoreDetails> getStoresByCity(String city) {
		List<AddressStoreDetails> list = istoredao.findByAddressCityCity(city);
		if (!list.isEmpty()) {
			return list;
		}
		else {
	     return null;
		}
	}

	
	//get stores by country name
	@Override
	public List<AddressStoreDetails> getStoresByCountry(String country) {
		List<AddressStoreDetails> list = istoredao.findByAddressCityCountryCountry(country);
		if (!list.isEmpty()) {
			return list;
		}
		else {
	     return null;
		}
	}
	
	
	//assigning address to a store
	@Override
	public AddressStoreDetails assignAddressToStore(Integer storeId, Address address) {
		Store store = istoredao.findByStoreId(storeId);
		
		if (store == null) {
			System.out.println("No store found with the storeId: " + storeId);
		}
		store.setAddress(address);
		istoredao.save(store);
		iaddressdao.save(address);
		AddressStoreDetails addressStoreDetails = new AddressStoreDetails(storeId,address);	      
	    return addressStoreDetails;
	 }

	//get all staffs by store id
	@Override
	public List<StaffDetails> getAllStaffByStoreId(Integer storeId) {
		// TODO Auto-generated method stub
		List<StaffDetails> list = istoredao.getStaffsByStoreId(storeId);
					return list;
	}
	
	
	//get stores by using phone number
	@Override
	public List<AddressStoreDetails> getStoreByPhoneNumber(String phone)  {
		List<AddressStoreDetails> store = istoredao.findByAddressPhone(phone);
		if (store == null) {
			return null;
		}
		return store;
	}

	

	//updating phonenumber by store id
	@Transactional
    @Override
    public StoreDetails updateStorePhoneNumber(Integer storeId, String phone) {
        // Retrieve the store by its ID
        Store store = istoredao.findByStoreId(storeId);
        // Check if the store exists
        if (store != null) {
            // Update the phone number of the store
            store.getAddress().setPhone(phone);

            // Save the updated store
            Store savedStore = istoredao.save(store);
            // Create and return StoreDetails from the updated store information
 
         return new StoreDetails(savedStore.getStoreId(),
 
                                 savedStore.getManagerStaff() != null ? savedStore.getManagerStaff().getFirstName() : null,
 
                                 savedStore.getManagerStaff() != null ? savedStore.getManagerStaff().getLastName() : null,
 
                                 savedStore.getManagerStaff() != null ? savedStore.getManagerStaff().getEmail() : null,
 
                                 savedStore.getAddress().getAddress(),
 
                                 savedStore.getAddress().getPhone());
 
        } else {
            // Return null if the store does not exist
            return null;
        }
    }
 
 
	

	//get customer details by store id
	@Override
	public List<CustomerDetails> getCustomersByStoreId(Integer storeId) {
		List<CustomerDetails> list = istoredao.findCustomersByStoreId(storeId);
		if (!list.isEmpty()) {
			return list;
		} else {
			return null;
		}
	}
//	@Transactional
//	@Override
//	public void assignManagerToStore(byte managerStaffId, Staff manager) {
//		Store store =findStoreByManagerStaffId(managerStaffId);
//		if (store == null) {
//			System.out.println("No store found with the managerStaffId: " + managerStaffId);
//		}
//		manager.setStores(store);
//	}
//
//	@Override
//	public Store findStoreByManagerStaffId(byte managerStaffId) {
//		Staff staff = istaffdao.findByStaffId(managerStaffId);
//
//
//		if (staff == null) {
//			System.out.println("No staff found with the given managerStaffId: " + managerStaffId);
//		}
//
//		byte id = staff.getStaffId();
//
//		Store store = istoredao.findByStoreId(id);
//
//		if (store == null) {
//			System.out.println("No store found with the managerStaffId: " + managerStaffId);
//		}
//
//		return store;
//
//	}
	
	
	//get manager details of a specific store
	@Override
	public StaffDetails getManagerDetailsOfAStore(Integer storeId) {
		// TODO Auto-generated method stub
		StaffDetails staff=istoredao.getManagerDetailsOfAStore(storeId);
		return staff;
	}
	
	//get all manager details
	@Override
	public List<ManagerDetails> getManagerAndStoreDetails(){
		List<ManagerDetails> managerdetails=istoredao.getManagerAndStoreDetails();
		return managerdetails;
	}

	@Transactional
	@Override
	public Store assignManagerToStore(Byte managerStaffId, Integer storeId) {
		Optional<Staff> staff = istaffdao.findById(managerStaffId);
		Store store = istoredao.findByStoreId(storeId);
		if(staff.isPresent()) {
			Staff staff1 = staff.get();
			if(staff1.getStores()==null) {
				store.setManagerStaff(staff1);
				store.setLastUpdate(LocalDateTime.now());
				staff1.setStores(store);
				staff1.setLastUpdate(LocalDateTime.now());
			}else {
				throw new InvalidAssignmentException("Cannot assign the staff since already manager of a store");
			}
		}else {
			throw new IdNotFoundException("Staff Id not found in staff");
		}
		return store;
	}
}



