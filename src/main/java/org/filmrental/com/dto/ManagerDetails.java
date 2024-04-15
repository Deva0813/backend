package org.filmrental.com.dto;

import org.filmrental.com.model.Address;
import org.filmrental.com.model.Store;

public record ManagerDetails(String firstName,String lastName,String email,String address,String city,String phone) {
	

}
