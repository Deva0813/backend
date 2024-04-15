package org.filmrental.com.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.filmrental.com.dao.IAddressDao;
import org.filmrental.com.dao.IStaffDao;
import org.filmrental.com.dao.IStoreDao;
import org.filmrental.com.dto.AddressStoreDetails;
import org.filmrental.com.dto.CustomerDetails;
import org.filmrental.com.dto.ManagerDetails;
import org.filmrental.com.dto.StaffDetails;
import org.filmrental.com.dto.StoreDetails;
import org.filmrental.com.model.Address;
import org.filmrental.com.model.City;
import org.filmrental.com.model.Country;
import org.filmrental.com.model.Customer;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.Staff;
import org.filmrental.com.model.Store;
import org.filmrental.com.service.StoreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class StoreControllerTest {

    @Mock
    private IStoreDao iStoreDao; // Mocking the DAO dependency
    @Mock
    private IAddressDao iaddressdao;
    @Mock
    private IStaffDao iStaffDao;
    
    @InjectMocks
    private StoreServiceImpl storeService; // Mocked service
    
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    //testing for addstore
    @Test
    public void testAddStore() {
        Store store = new Store();
        store.setAddress(null);
        store.setCustomer(null);
        store.setInventory(null);
        store.setLastUpdate(null);
        Staff managerStaff = new Staff();
        managerStaff.setStaffId((byte) 4);
        managerStaff.setFirstName("Johns");
        managerStaff.setLastName("Doej");

        store.setManagerStaff(managerStaff);
      
       
        
        when(iStoreDao.save(any(Store.class))).thenReturn(store);
        
        Store result = storeService.addStore(store);
        assertEquals(store, result);
       
    }
    
    
    //testing for retrive store by phonenumber
    @Test
    public void testGetStoreByPhoneNumber() {
        String phone = "7207310109";
        Address address=new Address();
        address.setPhone(phone);
        address.setAddress("12 ABC street");
        address.setAddress2("gandhi nagar");
        address.setAddressId((short) 1);
        address.setCity(null);
        address.setCustomer(null);
        
        
        

        // Create an instance of AddressStoreDetails using the record constructor
        AddressStoreDetails store = new AddressStoreDetails(1,address);

        // Create a list containing the store instance
        List<AddressStoreDetails> expectedStoreList = List.of(store);

        // Mock the behavior of iStoreDao.findByAddressPhone to return the created store list
        when(iStoreDao.findByAddressPhone("7207310109")).thenReturn(expectedStoreList);

        // Call the method under test
        List<AddressStoreDetails> retrievedStoreList = storeService.getStoreByPhoneNumber(phone);

        // Assert that the returned list is equal to the expected list
        assertEquals(expectedStoreList, retrievedStoreList);
    }


    
    //testing for retrive store by city
    @Test
    public void testGetStoresByCity() {
        String city = "New York";
     // Create an instance of AddressStoreDetails using the record constructor
        AddressStoreDetails store = new AddressStoreDetails(1, new Address(null, city, city, city, city, city, null, null, null, null, null));

        // Create a list containing the store instance
        List<AddressStoreDetails> expectedStoreList = List.of(store);

        // Mock the behavior of iStoreDao.findByAddressCityCity to return the created store list
        when(iStoreDao.findByAddressCityCity("New York")).thenReturn(expectedStoreList);

        // Call the method under test
        List<AddressStoreDetails> retrievedStoreList = storeService.getStoresByCity(city);

        // Assert that the returned list is equal to the expected list
        assertEquals(expectedStoreList, retrievedStoreList);

        

        

        
        

      
    }
    
    //testing for retrive stores by country
    @Test
    public void testGetStoresByCountry() {
        String countryName = "USA";

        // Create a City with the given country name
        City city = new City();
        city.setCity("Any City");
        
        Country country = new Country();
        country.setCountry(countryName);
        city.setCountry(country);

        // Create an Address associated with the city
        Address address = new Address();
        address.setCity(city);

        // Create an AddressStoreDetails using the record constructor
        AddressStoreDetails store = new AddressStoreDetails(1, address);

        // Create a list containing the store instance
        List<AddressStoreDetails> expectedStoreList = List.of(store);

        // Mock the behavior of iStoreDao.findByAddressCityCountryCountry to return the created store list
        when(iStoreDao.findByAddressCityCountryCountry(countryName)).thenReturn(expectedStoreList);

        // Call the method under test
        List<AddressStoreDetails> retrievedStoreList = storeService.getStoresByCountry(countryName);

        // Assert that the returned list is equal to the expected list
        assertEquals(expectedStoreList, retrievedStoreList);

        // Verify that the repository method was called with the correct country name
        verify(iStoreDao, times(1)).findByAddressCityCountryCountry(countryName);
    }

    
    @Test
    public void testAssignAddressToStore() {
        Integer storeId = 1;
        Address address = new Address();

        Store store = new Store();
        store.setStoreId(storeId);

        when(iStoreDao.findByStoreId(1)).thenReturn(store);

        // Call the method under test
        AddressStoreDetails addressStoreDetails = storeService.assignAddressToStore(storeId, address);

        // Verify that the address is assigned to the store
        assertEquals(address, store.getAddress());

        // Verify that the returned AddressStoreDetails object contains the correct storeId and address
       // assertEquals(storeId, addressStoreDetails.storeId());
        assertEquals(address, addressStoreDetails.address());
    }

    
    //updating phone number for a particular store
    
  //updating phone number for a particular store
    @Test
    public void testUpdateStorePhoneNumber() {
        // Mocking the store and its address
        Store store = new Store();
        Address address = new Address();
        store.setStoreId(1); // Set storeId to 1
        store.setAddress(address);
 
        // Mocking the ManagerStaff object
        Staff managerStaff = new Staff();
        store.setManagerStaff(managerStaff);
 
        // Mocking the return value of findByStoreId method
        when(iStoreDao.findByStoreId(1)).thenReturn(store);
 
        // Update phone number, first name, and last name
        String newPhoneNumber = "1234567890";
        String newFirstName = "John";
        String newLastName = "Doe";
 
        // Check if managerStaff is null before setting properties
        if (store.getManagerStaff() != null) {
            store.getManagerStaff().setFirstName(newFirstName);
            store.getManagerStaff().setLastName(newLastName);
        }
 
        store.getAddress().setPhone(newPhoneNumber); // Update the phone number
 
        // Mocking the save operation
        when(iStoreDao.save(store)).thenReturn(store); // Return the modified store object
 
        // Call the method to be tested
        StoreDetails expectedStoreDetailsDTO = new StoreDetails(1, newFirstName, newLastName, null, null, newPhoneNumber);
        StoreDetails updatedStoreDetailsDTO = storeService.updateStorePhoneNumber(1, newPhoneNumber);
 
        // Verify that the store phone number, first name, and last name have been updated
        assertEquals(expectedStoreDetailsDTO, updatedStoreDetailsDTO);
    }


    @Test
    public void testGetCustomersByStoreId() {
        Store store = new Store();
        store.setStoreId(1);

        List<CustomerDetails> expectedCustomerList = new ArrayList<>();
        CustomerDetails customer1 = new CustomerDetails((short) 1, "John", "Doe", "john@example.com", (byte) 1);
        expectedCustomerList.add(customer1);

        when(iStoreDao.findCustomersByStoreId(store.getStoreId())).thenReturn(expectedCustomerList);

        List<CustomerDetails> result = storeService.getCustomersByStoreId(store.getStoreId());

        assertEquals(expectedCustomerList, result);
    }

    @Test
    public void getManagerAndAddressDetails() {
        // Mock data
        Staff managerStaff = new Staff();
        managerStaff.setFirstName("John");
        managerStaff.setLastName("Doe");
        managerStaff.setEmail("john.doe@example.com");

        Address address = new Address();
        address.setAddress("123 Main St");
        
        City city = new City();
        city.setCity("City");
        address.setCity(city);
        
        address.setPhone("1234567890");

        ManagerDetails expectedManagerDetails = new ManagerDetails("John", "Doe", "john.doe@example.com", "123 Main St", "City", "1234567890");

        // Set up repository mock to return mock data when method is called
        when(iStoreDao.getManagerAndStoreDetails()).thenReturn(List.of(expectedManagerDetails));

        List<ManagerDetails> result = storeService.getManagerAndStoreDetails();

        // Verify that the result contains the expected ManagerDetails object
        assertEquals(1, result.size());
        assertEquals(expectedManagerDetails, result.get(0));

        // Verify that the repository method was called
        verify(iStoreDao, times(1)).getManagerAndStoreDetails();
    }

    @Test
    void testGetManagerDetailsOfAStore() {
        // Mock data
        Integer storeId = 1;
        StaffDetails expectedStaff = new StaffDetails((byte) 1, "John", "Doe", "john.doe@example.com", "johndoe", "password123");

        // Mock the behavior of the DAO
        when(iStoreDao.getManagerDetailsOfAStore(storeId)).thenReturn(expectedStaff);

        // Call the method to be tested
        StaffDetails actualStaff = storeService.getManagerDetailsOfAStore(storeId);

        // Verify that the method returns the expected result
        assertEquals(expectedStaff, actualStaff);

        // Verify that the DAO method was called
        verify(iStoreDao, times(1)).getManagerDetailsOfAStore(storeId);
    }

   
    @Test
    void testGetAllStaffByStoreId() {
        // Mock data
        Integer storeId = 1;
        List<StaffDetails> expectedStaffList = new ArrayList<>();
        
        // First staff
        StaffDetails staff1 = new StaffDetails((byte) 1, "John", "Doe", "john.doe@example.com", "johndoe", "password123");
        expectedStaffList.add(staff1);
        
        // Second staff
        StaffDetails staff2 = new StaffDetails((byte) 2, "Ram", "Dev", "ram.smith@example.com", "ramdev", "password123");
        expectedStaffList.add(staff2);

        // Mock the behavior of the DAO
        when(iStoreDao.getStaffsByStoreId(storeId)).thenReturn(expectedStaffList);

        // Call the method to be tested
        List<StaffDetails> actualStaffList = storeService.getAllStaffByStoreId(storeId);

        // Verify that the method returns the expected result
        assertEquals(expectedStaffList.size(), actualStaffList.size());
        for (int i = 0; i < expectedStaffList.size(); i++) {
            assertEquals(expectedStaffList.get(i).firstName(), actualStaffList.get(i).firstName());
            assertEquals(expectedStaffList.get(i).lastName(), actualStaffList.get(i).lastName());
            assertEquals(expectedStaffList.get(i).email(), actualStaffList.get(i).email());
            assertEquals(expectedStaffList.get(i).username(), actualStaffList.get(i).username());
            assertEquals(expectedStaffList.get(i).password(), actualStaffList.get(i).password());
        }

        // Verify that the DAO method was called
        verify(iStoreDao, times(1)).getStaffsByStoreId(storeId);
    }
    
    
    //testing for assigning manager to a store
    @Test
    void assignManagerToStore() {
        // Arrange
        Byte managerStaffId = 1;
        Integer storeId = 123;
        Staff staff = new Staff();
        Store store = new Store();
        // Mock dependencies
        
        when(iStaffDao.findById(managerStaffId)).thenReturn(Optional.of(staff));
       
        when(iStoreDao.findByStoreId(storeId)).thenReturn(store);
        
        // Act
        Store assignedStore = storeService.assignManagerToStore(managerStaffId, storeId);

        // Assert or further test logic
        // ...
        assertEquals(store, assignedStore);
    }
    
}



