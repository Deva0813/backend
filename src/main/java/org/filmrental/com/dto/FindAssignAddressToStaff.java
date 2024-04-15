package org.filmrental.com.dto;

import org.filmrental.com.model.Address;
import org.filmrental.com.model.City;
import org.filmrental.com.model.Country;


public record FindAssignAddressToStaff(Byte staffId , String firstName, String lastName,String address, String address2,String district,String postalCode,String phone) {

}
