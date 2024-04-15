package org.filmrental.com.dto;

import org.filmrental.com.model.Customer;

public record CustomerDetails(Short customerId,String firstName,String lastName,String email,Byte active) {

}

