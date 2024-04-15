package org.filmrental.com.dto;

import org.filmrental.com.model.Address;

public record SearchByPhone(Byte staffId, String firstName,String lastName, String email,String password,Address address) {

}
