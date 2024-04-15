package org.filmrental.com.dto;

import org.filmrental.com.model.Staff;

public record StaffDetails(Byte staffId,String firstName,String lastName,String email,String username,String password) {

}
