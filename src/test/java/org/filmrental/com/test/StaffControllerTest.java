package org.filmrental.com.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.filmrental.com.controller.StaffRestController;
import org.filmrental.com.dao.IStaffDao;
import org.filmrental.com.dao.IStoreDao;
import org.filmrental.com.dto.FindAssignAddressToStaff;
import org.filmrental.com.dto.FindBy;
import org.filmrental.com.dto.SearchByName;
import org.filmrental.com.dto.SearchByNames;
import org.filmrental.com.dto.SearchByPhone;
import org.filmrental.com.dto.UpdateBy;
import org.filmrental.com.model.Address;
import org.filmrental.com.model.City;
import org.filmrental.com.model.Country;
import org.filmrental.com.model.Staff;
import org.filmrental.com.model.Store;
import org.filmrental.com.service.StaffServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class StaffControllerTest {

    @Mock
    private IStaffDao istaffdao;
    
    @Mock
    private IStoreDao istoredao;

   

    @InjectMocks
    private StaffServiceImpl staffserviceimpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    
    
//    @Test
//    public void testCreateStaff() {
//        // Create a staff object
//        Staff staff = new Staff();
//        staff.setStaffId((byte) 1);
//        staff.setFirstName("John");
//        staff.setLastName("Doe");
//        Address address = new Address();
//       City city = new City();
//       city.setCity("Chennai");
//       city.setCountry(country);
//        address.setDistrict(null);
//        address.setAddressId((short) 1);
//        staff.setAddress(address);
//        staff.setEmail("john.doe@example.com");
//        Store store = new Store();
//        store.setStoreId((byte) 1);
//        staff.setStores(store);
//        staff.setActive(1);
//        staff.setPicture(null);
//        Country count =new Country();
//        count.setCountry("India");
//        
//        
//        
//        // Mock the behavior of istaffdao.save() method to return the staff object
//        when(istaffdao.save(any(Staff.class))).thenReturn(staff);
//
//        // Call the addStaff method of StaffServiceImpl
//        Staff result = staffserviceimpl.addStaff(staff);
//
//        // Assert that the result matches the expected staff object
//        assertThat(result).isEqualTo(staff);
//
//        // Verify that the istaffdao.save() method was called exactly once with any Staff object
//        verify(istaffdao, times(1)).save(any(Staff.class));
//    }
    
//    @Test
//    public void testCreateStaff() {
//        // Create a staff object
//        Staff staff = new Staff();
//        staff.setStaffId((byte) 1);
//        staff.setFirstName("John");
//        staff.setLastName("Doe");
//        
//        // Create a country object
//        Country country = new Country();
//        country.setCountry("India");
//        
//        // Create a city object
//        City city = new City();
//        city.setCity("Chennai");
//        city.setCountry(country);
//        
//        // Create an address object
//        Address address = new Address();
//        address.setAddressId((short) 1);
//        address.setCity(city);
//        
//        staff.setAddress(address);
//        staff.setEmail("john.doe@example.com");
//        
//        Store store = new Store();
//        store.setStoreId((byte) 1);
//        staff.setStores(store);
//        staff.setActive(1);
//        staff.setPicture(null);
//
//        // Mock the behavior of istaffdao.save() method to return the staff object
//        when(istaffdao.save(any(Staff.class))).thenReturn(staff);
//
//        // Call the addStaff method of StaffServiceImpl
//        Staff result = staffserviceimpl.addStaff(staff);
//
//        // Assert that the result matches the expected staff object
//        assertThat(result).isEqualTo(staff);
//
//        // Verify that the istaffdao.save() method was called exactly once with any Staff object
//        verify(istaffdao, times(1)).save(any(Staff.class));
//    }


    
      @Test
      public void testSearchStaffByFirstName() {
    	//Create a staff object
        Staff staff1 = new Staff();
       String firstName = "John";
       staff1.setStaffId((byte) 1);
      staff1.setFirstName("John");
      staff1.setLastName("Doe");
       Staff staff2 = new Staff();
      staff2.setStaffId((byte) 2);
      staff2.setFirstName("John");
       staff2.setLastName("Smith");
       List<SearchByName> staffList = new ArrayList<>();
       staffList.add(new SearchByName(staff1.getStaffId(),staff1.getFirstName(),staff1.getLastName(),staff1.getEmail(),staff1.getAddress()));
       when(istaffdao.findByFirstName("John")).thenReturn(staffList);
       // Call the getStaffByFirstName method of StaffServiceImpl
       List<SearchByName> result = staffserviceimpl.getStaffByFirstName("John");
       assertThat(result).isEqualTo(staffList);
       //result matches the expected staff object
       verify(istaffdao, times(1)).findByFirstName(firstName);
    		assertEquals(staffList,result);
   }
   
   
     @Test
     public void testSearchStaffByLastName() {
    //Create a staff object
      String lastName = "Doe";
      Staff staff1 = new Staff();
      staff1.setStaffId((byte) 1);
      staff1.setFirstName("John");
      staff1.setLastName("Doe");
     Staff staff2 = new Staff();
     staff2.setStaffId((byte) 2);
      staff2.setFirstName("Jane");
     staff2.setLastName("Doe");
     List<SearchByName> staffList = new ArrayList<>();
     staffList.add(new SearchByName(staff1.getStaffId(),staff1.getFirstName(),staff1.getLastName(),staff1.getEmail(),staff1.getAddress()));
     when(istaffdao.findByLastName("Doe")).thenReturn(staffList);
     // Call the getStaffBylasttName method of StaffServiceImpl
      List<SearchByName> result = staffserviceimpl.getStaffByLastName("Doe");
     assertThat(result).isEqualTo(staffList);
     //result matches the expected staff object
      verify(istaffdao, times(1)).findByLastName(lastName);
   }
    
   
    @Test
       public void testSearchStaffByEmail() {
       String email = "john.doe@example.com";
       //Create a staff object
        Staff staff1 = new Staff();
        staff1.setStaffId((byte) 1);
        staff1.setFirstName("John");
        staff1.setLastName("Doe");
        staff1.setEmail("john.doe@example.com");
        List<SearchByName> staffList = new ArrayList<>();
        staffList.add(new SearchByName(staff1.getStaffId(),staff1.getFirstName(),staff1.getLastName(),staff1.getEmail(), staff1.getAddress()));
        when(istaffdao.findByEmail(email)).thenReturn(staffList);
        // Call the getStaffByEmailName method of StaffServiceImpl
        List<SearchByName> result = staffserviceimpl.getStaffByEmail(email);
        assertThat(result).isEqualTo(staffList); 
        //result matches the expected staff object
       verify(istaffdao, times(1)).findByEmail(email);
    }

        @Test
	    public void testSearchStaffByCity() {
	        String city = "New York";
	        //Create a staff object
	        Staff staff1 = new Staff();
	        staff1.setStaffId((byte) 1);
	        staff1.setFirstName("John");
	        staff1.setLastName("Doe");
	        Staff staff2 = new Staff();
	        staff2.setStaffId((byte) 2);
	        staff2.setFirstName("Jane");
	        staff2.setLastName("Smith");
	        List<FindBy> staffList = new ArrayList<>();
	        staffList.add(new FindBy(staff1.getStaffId(),staff1.getFirstName(),staff1.getLastName(), staff1.getAddress()));
	        when(istaffdao.findByAddress_City_City(city)).thenReturn(staffList);//change city here for fail
	        // Call the getStaffByCity method of StaffServiceImpl
	        List<FindBy> result = staffserviceimpl.getStaffByCity(city);
	        assertThat(result).isEqualTo(staffList);
	        //result matches the expected staff object
	        verify(istaffdao, times(1)).findByAddress_City_City(city);
	    }
       
    @Test
    public void testSearchStaffByCountry() {
        String country = "USA";
        //Create a staff object
       Staff staff1 = new Staff();
        staff1.setStaffId((byte) 1);
        staff1.setFirstName("John");
        staff1.setLastName("Doe");
        Staff staff2 = new Staff();
        staff2.setStaffId((byte) 2);
        staff2.setFirstName("Jane");
       staff2.setLastName("Smith");
       List<FindBy> staffList = new ArrayList<>();
       staffList.add(new FindBy(staff1.getStaffId(),staff1.getFirstName(),staff1.getLastName(), staff1.getAddress()));
       when(istaffdao.findByAddressCityCountryCountry(country)).thenReturn(staffList); 
       // Call the getStaffByCountry method of StaffServiceImpl
       List<FindBy> result = staffserviceimpl.searchStaffByCountry(country);
        assertThat(result).isEqualTo(staffList); 
        //result matches the expected staff object
      verify(istaffdao, times(1)).findByAddressCityCountryCountry(country); 
   }
    
    @Test
    public void testSearchStaffByPhone() {
       String phoneNumber = "1234567890";
       //Create a staff object
        Staff staff1 = new Staff();
        staff1.setStaffId((byte) 1);
        staff1.setFirstName("John");
       staff1.setLastName("Doe");
       SearchByPhone staffList1 = (new SearchByPhone(staff1.getStaffId(),staff1.getFirstName(),staff1.getLastName(), staff1.getEmail(),staff1.getPassword(),staff1.getAddress()));
       // Call the getStaffByPhone method of StaffServiceImpl
        when(istaffdao.findByAddressPhone("1234567890")).thenReturn(staffList1);
        SearchByPhone result = staffserviceimpl.searchStaffByPhone("1234567890");
        assertThat(result).isEqualTo(staffList1);  
        verify(istaffdao, times(1)).findByAddressPhone(phoneNumber); 
      //result matches the expected staff object
    
    }
    
    @Test
    public void updateFirstNameTest() {
        // Create a staff object
        Staff staff1 = new Staff();
        staff1.setFirstName("John");
        byte staffId = 1;
        String newFirstName = "Jane";

        // Mocking the behavior of istaffdao
        when(istaffdao.findById(staffId)).thenReturn(staff1);

        // Call the updateStaffFirstName method
        staffserviceimpl.updateStaffFirstName(newFirstName, staffId);

        // Verify that findById method is called with the correct staffId
        verify(istaffdao, times(1)).findById(staffId);

        // Verify that the first name of the staff object has been updated
        assertEquals(newFirstName, staff1.getFirstName());
    }


 
    
    @Test
    void updateLastNameTest() {
        // Create a staff object
        Staff staff1 = new Staff();
        staff1.setLastName("Smith");
        byte staffId = 1;
        String newLastName = "Johnson"; // New last name

        
        when(istaffdao.findById(staffId)).thenReturn(staff1);

        // Call the updateStaffLastName method of StaffService
        staffserviceimpl.updateStaffLasttName(newLastName, staffId);

        // Verify that findById method of StaffDao is called with the correct staffId
        verify(istaffdao, times(1)).findById(staffId);

        // Assert that the last name of the staff is updated correctly
        assertEquals(newLastName, staff1.getLastName(), "Last name should be updated");
    }


    

    @Test
    void updateEmailTest() {
        // Arrange
        Staff staff1 = new Staff();
        staff1.setEmail("Smith@gmail.com");
        byte staffId = 3;
        String newEmail = "Smithy@gmail.com";
        when(istaffdao.findById(staffId)).thenReturn(staff1);
        staffserviceimpl.updateStaffEmail(newEmail, staffId);
        assertEquals(newEmail, staff1.getEmail());
        
        // Verify that findById method of istaffdao is called with the correct staffId
        verify(istaffdao, times(1)).findById(staffId);
    }
   
	@Test
    void updatePhoneTest() {
        Staff staff1 = new Staff();// Create a mock  object 
        staff1.setStaffId((byte)3);
        Address address = new Address();// Create a mock object
        address.setPhone("667666776"); 
        staff1.setAddress(address);
        when(istaffdao.findById((byte) 3)).thenReturn((staff1)); //mock the behavior of staffdao    
        String newPhoneNumber = "123455666";//updated number
        staffserviceimpl.updateStaffPhone(newPhoneNumber, (byte) 3);
        assertEquals(newPhoneNumber, staff1.getAddress().getPhone());//check the new number with old number
    }
    


    

    
    @Test
    void assignAddressToStaffTest() {
    //create mock object
        Staff staff = new Staff();
        staff.setStaffId((byte)1);
        staff.setFirstName("John");
        staff.setLastName("Doe");
        // create mock object
        Address address = new Address();
        address.setAddress("123 Main St");
        address.setPostalCode("10001");
        // Set the address to the staff
        staff.setAddress(address);
        
        
        when(istaffdao.findById((byte) 1)).thenReturn(staff);
        
        // Create a new address to assign
        Address newAddress = new Address();
        newAddress.setAddress("456 Elm St");
        newAddress.setPostalCode("90001");

        staffserviceimpl.assignAddressToStaff((byte) 1, newAddress);

        assertEquals(newAddress, staff.getAddress());
    }
   
	

    
    @Test
    void assignStoretoStaffTest() {
        Staff staff = new Staff();// Create a mock  object 
        staff.setStaffId((byte) 3);
        staff.setFirstName("John");
        staff.setLastName("Doe");
        when(istaffdao.findById((byte) 3)).thenReturn(staff);//mock the behavior of staffdao 
        Store store = new Store();// Create a mock object
        store.setStoreId((byte) 1);
        Staff updatedStaff = staffserviceimpl.assignStoreToStaff((byte) 3, store);
        assertEquals(staff.getStaffId(), updatedStaff.getStaffId());
        assertEquals(staff.getFirstName(), updatedStaff.getFirstName());
        assertEquals(staff.getLastName(), updatedStaff.getLastName());// equals or not
      
    }
    	


    	  
    	
   }
    
    


    


        
        



