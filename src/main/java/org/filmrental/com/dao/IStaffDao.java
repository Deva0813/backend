package org.filmrental.com.dao;

import org.filmrental.com.dto.FindBy;
import org.filmrental.com.dto.SearchByName;
import org.filmrental.com.dto.SearchByNames;
import org.filmrental.com.dto.SearchByPhone;
import org.filmrental.com.model.City;
import org.filmrental.com.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IStaffDao extends CrudRepository<Staff, Byte> {
    List<SearchByName> findByLastName(String lastName);
    List<SearchByName> findByFirstName(String firstName);
    
    List<SearchByName> findByEmail(String email);
    
    @Query("SELECT new org.filmrental.com.dto.FindBy(s.staffId,s.firstName,s.lastName,s.address) FROM Staff s WHERE s.address.city.city=:city")
    List<FindBy> findByAddress_City_City(@Param("city") String city);
    
//    @Query("SELECT s.picture FROM Staff s WHERE s.id = :id")
//   Optional<byte[]> findPictureById(byte id);
    
    List<FindBy> findByAddressCityCountryCountry(String country);
    
    public SearchByPhone findByAddressPhone(String phone);
    
    public Staff findById(byte id);
    
    //additional endpoint for finding who were the staffs who are not yet assigned to a store
    @Query("SELECT s from Staff s where s.stores.storeId is null and s.staffId=:staffId")
    public Staff findStaffWhoAreNotAssignedToAStore(Byte staffId);
    
    //list of staffs who were not assigned to store ===> for frontend purpose 
    @Query("SELECT s from Staff s where s.stores.storeId is null")
    public List<Staff> findStaffWhoAreNotAssignedToAStore();
    
    //finding staffs 
    @Query("SELECT s from Staff s where s.active=1 and s.stores.storeName=:storeName")
    public List<Staff> findStaffWhoAreActive(String storeName);
    
	@Query("from Staff c where c.email=:email and "
			+ "c.password=:password")
	public Staff loginStaff(@Param("email")String email,
			@Param("password") String password);
    
}