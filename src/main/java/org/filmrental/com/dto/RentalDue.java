package org.filmrental.com.dto;

import java.time.LocalDateTime;

import org.filmrental.com.model.Customer;
import org.filmrental.com.model.Rental;

public record RentalDue(Short customerId,String firstName,String lastName,String email,Byte active,LocalDateTime createDate) {

	
}
