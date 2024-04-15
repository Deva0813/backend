

package org.filmrental.com.dao;

import java.util.List;
import java.util.Optional;

import org.filmrental.com.dto.AddressStoreDetails;
import org.filmrental.com.dto.CustomerDetails;
import org.filmrental.com.dto.ManagerDetails;
import org.filmrental.com.dto.StaffDetails;
import org.filmrental.com.model.Address;
import org.filmrental.com.model.Customer;
import org.filmrental.com.model.Staff;
import org.filmrental.com.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IStoreDao extends CrudRepository<Store, Integer> {

	List<AddressStoreDetails> findByAddressCityCity(String city);

	List<AddressStoreDetails> findByAddressCityCountryCountry(String country);


	

	List<AddressStoreDetails> findByAddressPhone(String phone);

	Store findByStoreId(Integer id);

	
	@Query("SELECT new org.filmrental.com.dto.CustomerDetails(c.customerId,c.firstName,c.lastName,c.email,c.active) FROM Customer c JOIN c.store s WHERE s.storeId = :storeId")
	List<CustomerDetails> findCustomersByStoreId(Integer storeId);
	@Query("SELECT new org.filmrental.com.dto.StaffDetails(s.staffId,s.firstName,s.lastName,s.email,s.username,s.password) FROM Staff s JOIN s.store ss WHERE ss.storeId = :storeId")
	List<StaffDetails> getStaffsByStoreId(Integer storeId);
	@Query("SELECT new org.filmrental.com.dto.StaffDetails(s.staffId,s.firstName,s.lastName,s.email,s.username,s.password) FROM Staff s JOIN s.store ss WHERE ss.managerStaff.staffId = s.staffId AND ss.storeId = :storeId")
	StaffDetails getManagerDetailsOfAStore(Integer storeId);
	@Query("SELECT new org.filmrental.com.dto.ManagerDetails(s.managerStaff.firstName,s.managerStaff.lastName,s.managerStaff.email,s.address.address,s.address.city.city,s.address.phone) FROM Store s JOIN s.managerStaff ms WHERE s.managerStaff.staffId = ms.staffId")
	List<ManagerDetails> getManagerAndStoreDetails();

	Store findByManagerStaff(Optional<Staff> staff);
	
	

	

	
	
}
