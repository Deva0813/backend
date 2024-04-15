package org.filmrental.com.service;

import java.util.List;

import org.filmrental.com.model.Store;
import org.springframework.data.repository.CrudRepository;
import org.filmrental.com.dto.AddressStoreDetails;
import org.filmrental.com.dto.CustomerDetails;
import org.filmrental.com.dto.ManagerDetails;
import org.filmrental.com.dto.StaffDetails;
import org.filmrental.com.dto.StoreDetails;
import org.filmrental.com.model.Address;
import org.filmrental.com.model.Customer;
import org.filmrental.com.model.Staff;
import org.filmrental.com.model.Store;

public interface IStoreService {
	public Store addStore(Store store);
	public List<AddressStoreDetails> getStoresByCity(String city);
	public List<AddressStoreDetails> getStoresByCountry(String country);

	public List<StaffDetails> getAllStaffByStoreId(Integer storeId);
	List<AddressStoreDetails> getStoreByPhoneNumber(String phone) ;
	StoreDetails updateStorePhoneNumber(Integer storeId, String phone) ;
	
	Store getStoreById(Integer id);
	AddressStoreDetails assignAddressToStore(Integer storeId, Address address);
	List<CustomerDetails> getCustomersByStoreId(Integer storeId);
	Store assignManagerToStore(Byte managerStaffId, Integer storeId);
	StaffDetails getManagerDetailsOfAStore(Integer storeId);
	List<ManagerDetails> getManagerAndStoreDetails();
	
	
}
