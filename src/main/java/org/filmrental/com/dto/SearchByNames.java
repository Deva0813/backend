package org.filmrental.com.dto;

import java.sql.Blob;

public record SearchByNames(Byte staffId,String firstName,String lastName,String email,Blob picture) {

}
