package org.filmrental.com.service;

import java.util.List;
import java.util.Optional;

import org.filmrental.com.dao.IAddressDao;
import org.filmrental.com.dto.FindAssignAddressToStaff;
import org.filmrental.com.dto.FindBy;
import org.filmrental.com.dto.SearchByName;
import org.filmrental.com.dto.SearchByNames;
import org.filmrental.com.dto.SearchByPhone;
import org.filmrental.com.dto.StaffDetails;
import org.filmrental.com.dto.UpdateBy;
import org.filmrental.com.model.Address;
import org.filmrental.com.model.City;
import org.filmrental.com.model.Country;
import org.filmrental.com.model.Staff;
import org.filmrental.com.model.Store;

public interface IStaffService {
	
	 public Staff addStaff(Staff staff);
	 
	 
	 public List<SearchByName> getStaffByFirstName(String firstName);
	 
	public List<SearchByNames> getStaffByFirstNames(String firstName);
	
	public List<SearchByName> getStaffByLastName(String lastName);	
	
	public List<SearchByName> getStaffByEmail(String Email);
	
	public List<FindBy> getStaffByCity(String city) ;
	
	public List<FindBy>  searchStaffByCountry(String country);
	
	public SearchByPhone  searchStaffByPhone(String Phone);
	
	public UpdateBy updateStaffFirstName(String firstName, byte id);
	
	public UpdateBy updateStaffEmail(String email, byte id);
	
	// public Staff  getStaffByAddressId(byte addressId);
	
	//public Staff updateStaffPhoneNumber(String phoneNumber, short id);

	public FindAssignAddressToStaff assignAddressToStaff(byte id, Address address) ;
	
	public UpdateBy updateStaffLasttName(String LastName, byte id);
	
	public Staff getStaffById(byte id);
	
	public SearchByPhone  updateStaffPhone(String phone, byte id);
	
	public Staff assignStoreToStaff(byte staffId, Store store);
	
	public Staff updateStaff(Staff staff);

	public Staff loginStaff(String email, String password);
	
	public Staff getAllStaff(byte id);
	
	//public void StaffService(IAddressDao addressDao);
	
	//public Optional<byte[]> getStaffPictureById(Long id);
	
	

	
	

    
  
	


}


