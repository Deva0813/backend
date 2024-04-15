package org.filmrental.com.dao;

import org.filmrental.com.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressDao extends CrudRepository<Address, Short> {
	
	public Address findByAddress(String address);

}
