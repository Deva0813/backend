package org.filmrental.com.dto;

import java.sql.Blob;

import org.filmrental.com.model.Address;

public record SearchByName(Byte staffId,String firstName,String lastName,String email,Address address,Blob picture) {

}
