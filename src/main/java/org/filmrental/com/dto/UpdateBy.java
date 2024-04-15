package org.filmrental.com.dto;

import java.time.LocalDateTime;

import org.filmrental.com.model.Address;

public record UpdateBy(Byte staffId ,String firstName,String lastName,String email,String password,LocalDateTime lastUpdate,Address address) {

	

	
	

}
