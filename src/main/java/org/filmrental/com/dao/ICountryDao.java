package org.filmrental.com.dao;

import org.filmrental.com.model.City;
import org.filmrental.com.model.Country;
import org.springframework.data.repository.CrudRepository;

public interface ICountryDao  extends CrudRepository<Country, Short> {

	
	

}
