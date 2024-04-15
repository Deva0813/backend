package org.filmrental.com.dto;

import org.filmrental.com.model.Address;


public record FindBy(Byte staffId,String firstName,String LastName ,Address address ) {

}
