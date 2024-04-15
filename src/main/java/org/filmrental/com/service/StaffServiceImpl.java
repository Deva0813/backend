package org.filmrental.com.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.filmrental.com.dao.IAddressDao;
import org.filmrental.com.dao.ICityDao;
import org.filmrental.com.dao.ICountryDao;
import org.filmrental.com.dao.IStaffDao;
import org.filmrental.com.dao.IStoreDao;
import org.filmrental.com.dto.FindAssignAddressToStaff;
import org.filmrental.com.dto.FindBy;
import org.filmrental.com.dto.SearchByName;
import org.filmrental.com.dto.SearchByNames;
import org.filmrental.com.dto.SearchByPhone;
import org.filmrental.com.dto.StaffDetails;
import org.filmrental.com.dto.UpdateBy;
import org.filmrental.com.exception.DataNotFoundException;
import org.filmrental.com.model.Address;
import org.filmrental.com.model.City;
import org.filmrental.com.model.Country;
import org.filmrental.com.model.Staff;
import org.filmrental.com.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StaffServiceImpl implements IStaffService {
	
	@Autowired
	private IStaffDao staffDao;
	

	@Autowired
	private IStoreDao  storeDao;
	


	// Add a new staff member with address
    @Transactional
    @Override
    public Staff addStaff(Staff staff) {        
    	return staffDao.save(staff);
      
    }
    
	 
	// Retrieve staff members by first name
	 @Override
	    public List<SearchByName> getStaffByFirstName(String fname) {
	        List<SearchByName> staffList = staffDao.findByFirstName(fname);
	        return staffList;
	    }


	// Retrieve staff members by last name
	 @Override
	    public List<SearchByName> getStaffByLastName(String lastName) {
	    	  List<SearchByName> staffList = staffDao.findByLastName(lastName);
	        return staffList;
	    }

	 // Retrieve staff members by email
	 
	

	// Retrieve staff members by city 
	@Override
	public List<FindBy> getStaffByCity(String city) {
		
		return staffDao.findByAddress_City_City(city);
	}
	
	// Search staff members by country

	@Override
	public List<FindBy> searchStaffByCountry(String country) {
	    List<FindBy> staffList = staffDao.findByAddressCityCountryCountry(country);
	    return staffList;
	}
	
	// Search staff member by phone number
	@Override
	public SearchByPhone searchStaffByPhone(String Phone) {
		SearchByPhone stafflist = staffDao.findByAddressPhone(Phone);
		return stafflist;
	}


	// Retrieve a staff member by ID
	@Override
	public Staff getStaffById(byte id) {
		
		return staffDao.findById(id);
	}

	// Update the last name of a staff member
	@Transactional
	@Override
	public UpdateBy updateStaffLasttName(String LastName, byte id) {
	
		 Staff staff = getStaffById(id);
		    if (staff != null) {
		        staff.setLastName(LastName);
		        staff.setLastUpdate(LocalDateTime.now());
		        Staff savedStaff =staffDao.save(staff);
		        if(savedStaff !=null) {
		        return new UpdateBy(savedStaff.getStaffId(), savedStaff.getFirstName(), savedStaff.getLastName(),savedStaff.getEmail(),savedStaff.getPassword(),savedStaff.getLastUpdate(),savedStaff.getAddress());         
		        }
		    } else {
		        return null;
		    }
			return null;
	
	
	}

	// Update the first name of a staff member
	@Transactional
	@Override
	public UpdateBy updateStaffFirstName(String firstName, byte id) {
		Staff staff = getStaffById(id);
	    if (staff != null) {
	        staff.setFirstName(firstName);
	        staff.setLastUpdate(LocalDateTime.now());
	        Staff savedStaff =staffDao.save(staff);
	        if(savedStaff !=null) {
	        return new UpdateBy(savedStaff.getStaffId(), savedStaff.getFirstName(), savedStaff.getLastName(),savedStaff.getEmail(),savedStaff.getPassword(),savedStaff.getLastUpdate(),savedStaff.getAddress());   
	        }
	    } else {
	        return null;
	    }
		return null;
	}
	
	// Update the email of a staff member

	@Transactional
	@Override
	public UpdateBy updateStaffEmail(String email, byte id) {
		Staff staff = getStaffById(id);
	    if (staff != null) {
	        staff.setEmail(email);
	        staff.setLastUpdate(LocalDateTime.now());
	        Staff savedStaff =staffDao.save(staff);
	        if(savedStaff !=null) {
	        return new UpdateBy(savedStaff.getStaffId(), savedStaff.getFirstName(), savedStaff.getLastName(),savedStaff.getEmail(),savedStaff.getPassword(),savedStaff.getLastUpdate(),savedStaff.getAddress());  
	        }
	    } else {
	        return null;
	    }
		return null;
	}

	
	    
	   


	 // Assign a store to a staff member
	@Override
	public Staff assignStoreToStaff(byte staffId, Store store) {
		
		Staff staff = staffDao.findById(staffId);
	    	Integer storeId = store.getStoreId();
	    	if (storeId != null) {
	    		store=storeDao.save(store);
	    		staff.setStores(store);
	            staffDao.save(staff);
	    	}
	         
	    return staff;
	
	    }
	
	
	// Update the phone number of a staff member
	// if update number fails changes made  entities  will also be rolled back.
	@Transactional
	@Override
	public SearchByPhone updateStaffPhone(String phone, byte id) {
		Staff staff =staffDao.findById(id);
	    if (staff != null) {
	        staff.getAddress().setPhone(phone);
	        staff.setLastUpdate(LocalDateTime.now());
	        Staff savedStaff =staffDao.save(staff);
	        if(savedStaff!= null) {
	        return new SearchByPhone(savedStaff.getStaffId(), savedStaff.getFirstName(), savedStaff.getLastName(),savedStaff.getPassword(),savedStaff.getEmail(),savedStaff.getAddress());
	        }
	    } else {
	        return null;
	    }
		return null;
	}

	@Override
	public Staff updateStaff(Staff staff) {
		
		return staffDao.save(staff);
	}

	
	@Transactional
	@Override
	public FindAssignAddressToStaff assignAddressToStaff(byte id, Address address) {
	    Staff staff = staffDao.findById(id);
	    if (staff != null) {
	        
	        if (address != null) {
	            // Set the address for the staff
	            staff.setAddress(address);
	            // Save the staff with the updated address
	            Staff savedStaff = staffDao.save(staff);
	            
	            // Check if saving was successful
	            if (savedStaff != null) {
	                // Create and return FindAssignAddressToStaff object
	                return new FindAssignAddressToStaff(savedStaff.getStaffId(), savedStaff.getFirstName(), savedStaff.getLastName(), address.getAddress(), address.getAddress2(), address.getDistrict(), address.getPostalCode(), address.getPhone());
	            }
	        } else {
	           return null;
	        }
	    } else {
	    	return null;
	      
	    }
	    return null;
	}


	@Override
	public Staff loginStaff(String email, String password) {
		// TODO Auto-generated method stub
		return  staffDao.loginStaff(email,password);
	}


	@Override
	public Staff getAllStaff(byte id) {

		return  staffDao.findById(id);
	}


	@Override
	public List<SearchByName> getStaffByEmail(String Email) {
		List<SearchByName> staffList = staffDao.findByEmail(Email);
	    return staffList;

	}


	@Override
	public List<SearchByNames> getStaffByFirstNames(String firstName) {
		// TODO Auto-generated method stub
		return null;
	}


	
	
	

	


//	@Override
//	public Optional<byte[]> getStaffPictureById(Long id) {
//	
//		return staffDao.findPictureById(id);
//	}
//	
	
	

	}
	
	
	 
	
