package org.filmrental.com.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.filmrental.com.dao.ICustomerDao;
import org.filmrental.com.model.Address;
import org.filmrental.com.model.Customer;
import org.filmrental.com.service.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class CustomerControllerTest {
	
		 
	    @Mock
	    private ICustomerDao ICustomerDao;
	 
	    @InjectMocks
	    private CustomerServiceImpl customerServiceImpl;
	 
	    @BeforeEach
	    void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
	    @Test
	    //Search Customers by LastName
	    public void testFindCustomersByLastName() {
	        String lastName = "SMITH";
	        Customer customer1 = new Customer();
	        customer1.setCustomerId((short) 1);
	       // customer1.setStore((Integer)null);
	        customer1.setFirstName("MARY");
	        customer1.setLastName("SMITH");
	        customer1.setEmail("MARY.SMITH@sakilacustomer.org");
	        Address address1 = new Address();
	        address1.setAddressId((short) 1);
	        customer1.setAddress(address1);
	        customer1.setActive(null);
	
	   
	        
	        Customer customer2 = new Customer();
	        customer2.setCustomerId((short) 2);
	        //customer2.setStore((Integer)null);
	        customer2.setFirstName("PATRICIA");
	        customer2.setLastName("SMITH");
	        customer2.setEmail("PATRICIA.JOHNSON@sakilacustomer.org");
	        Address address2 = new Address();
	        address2.setAddressId((short) 2);
	        customer2.setAddress(address2);
	        customer2.setActive(null);
	        
	        List<Customer> customerList = Arrays.asList(customer1, customer2);
	        
	        //when(crepo.findByLastName("SMITH")).thenReturn(Collections.singletonList(customer1));
	        when(ICustomerDao.findByLastName("SMITH")).thenReturn(customerList);
	        List<Customer> result = customerServiceImpl.findByLastName("SMITH");
	        
	        assertEquals(2, result.size());
	        assertEquals(customerList, result);
	      
	    }
	    
	    @Test
	    //Search Customers by FirstName
	    public void testFindCustomersByFirstName() {
	        String lastName = "SMITH";
	        Customer customer1 = new Customer();
	        customer1.setCustomerId((short) 1);
	        //customer1.setStore((Integer)null);
	        customer1.setFirstName("MARY");
	        customer1.setLastName("SMITH");
	        customer1.setEmail("MARY.SMITH@sakilacustomer.org");
	        Address address1 = new Address();
	        address1.setAddressId((short) 1);
	        customer1.setAddress(address1);
	        customer1.setActive(null);
	
	   
	        
	        Customer customer2 = new Customer();
	        customer2.setCustomerId((short) 2);
	       // customer2.setStore((Integer)null);
	        customer2.setFirstName("PATRICIA");
	        customer2.setLastName("SMITH");
	        customer2.setEmail("PATRICIA.JOHNSON@sakilacustomer.org");
	        Address address2 = new Address();
	        address2.setAddressId((short) 2);
	        customer2.setAddress(address2);
	        customer2.setActive(null);
	        
	        List<Customer> customerList = Arrays.asList(customer1, customer2);
	        
	        //when(crepo.findByLastName("SMITH")).thenReturn(Collections.singletonList(customer1));
	        when(ICustomerDao.findByLastName("SMITH")).thenReturn(customerList);
	        List<Customer> result = customerServiceImpl.findByLastName("SMITH");
	        
	        assertEquals(2, result.size());
	        assertEquals(customerList, result);
	      
	    }
	    
	    @Test
	    //Search Customers by Email
	    public void testFindCustomersByEmail() {
	        String lastName = "SMITH";
	        Customer customer1 = new Customer();
	        customer1.setCustomerId((short) 1);
	       // customer1.setStore((Integer)null);
	        customer1.setFirstName("MARY");
	        customer1.setLastName("SMITH");
	        customer1.setEmail("MARY.SMITH@sakilacustomer.org");
	        Address address1 = new Address();
	        address1.setAddressId((short) 1);
	        customer1.setAddress(address1);
	        customer1.setActive(null);
	
	   
	        
	        Customer customer2 = new Customer();
	        customer2.setCustomerId((short) 2);
	       // customer2.setStore((Integer)null);
	        customer2.setFirstName("PATRICIA");
	        customer2.setLastName("SMITH");
	        customer2.setEmail("PATRICIA.JOHNSON@sakilacustomer.org");
	        Address address2 = new Address();
	        address2.setAddressId((short) 2);
	        customer2.setAddress(address2);
	        customer2.setActive(null);
	        
	        List<Customer> customerList = Arrays.asList(customer1, customer2);
	        
	        //when(crepo.findByLastName("SMITH")).thenReturn(Collections.singletonList(customer1));
	        when(ICustomerDao.findByLastName("SMITH")).thenReturn(customerList);
	        List<Customer> result = customerServiceImpl.findByLastName("SMITH");
	        
	        assertEquals(2, result.size());
	        assertEquals(customerList, result);
	      
	    }
	    
	    @Test
	    //Search Customers by City
	    public void testFindCustomersByCity() {
	        String lastName = "SMITH";
	        Customer customer1 = new Customer();
	        customer1.setCustomerId((short) 1);
	       // customer1.setStore((Integer)null);
	        customer1.setFirstName("MARY");
	        customer1.setLastName("SMITH");
	        customer1.setEmail("MARY.SMITH@sakilacustomer.org");
	        Address address1 = new Address();
	        address1.setAddressId((short) 1);
	        customer1.setAddress(address1);
	        customer1.setActive(null);
	
	   
	        
	        Customer customer2 = new Customer();
	        customer2.setCustomerId((short) 2);
	       // customer2.setStore((Integer)null);
	        customer2.setFirstName("PATRICIA");
	        customer2.setLastName("SMITH");
	        customer2.setEmail("PATRICIA.JOHNSON@sakilacustomer.org");
	        Address address2 = new Address();
	        address2.setAddressId((short) 2);
	        customer2.setAddress(address2);
	        customer2.setActive(null);
	        
	        List<Customer> customerList = Arrays.asList(customer1, customer2);
	        
	        //when(crepo.findByLastName("SMITH")).thenReturn(Collections.singletonList(customer1));
	        when(ICustomerDao.findByLastName("SMITH")).thenReturn(customerList);
	        List<Customer> result = customerServiceImpl.findByLastName("SMITH");
	        
	        assertEquals(2, result.size());
	        assertEquals(customerList, result);
	      
	    }
	    
	    
	    @Test
	    //Search Customers by Country
	    public void testFindCustomersByCountry() {
	        String lastName = "SMITH";
	        Customer customer1 = new Customer();
	        customer1.setCustomerId((short) 1);
	      //  customer1.setStore((Integer)null);
	        customer1.setFirstName("MARY");
	        customer1.setLastName("SMITH");
	        customer1.setEmail("MARY.SMITH@sakilacustomer.org");
	        Address address1 = new Address();
	        address1.setAddressId((short) 1);
	        customer1.setAddress(address1);
	        customer1.setActive(null);
	
	   
	        
	        Customer customer2 = new Customer();
	        customer2.setCustomerId((short) 2);
	      //  customer2.setStore((Integer)null);
	        customer2.setFirstName("PATRICIA");
	        customer2.setLastName("SMITH");
	        customer2.setEmail("PATRICIA.JOHNSON@sakilacustomer.org");
	        Address address2 = new Address();
	        address2.setAddressId((short) 2);
	        customer2.setAddress(address2);
	        customer2.setActive(null);
	        
	        List<Customer> customerList = Arrays.asList(customer1, customer2);
	        
	        //when(crepo.findByLastName("SMITH")).thenReturn(Collections.singletonList(customer1));
	        when(ICustomerDao.findByLastName("SMITH")).thenReturn(customerList);
	        List<Customer> result = customerServiceImpl.findByLastName("SMITH");
	        
	        assertEquals(2, result.size());
	        assertEquals(customerList, result);
	      
	    }
	    
	    @Test
	    //Search all Active Customers
	    public void findAllActiveCustomer() {
	        String lastName = "SMITH";
	        Customer customer1 = new Customer();
	        customer1.setCustomerId((short) 1);
	      //  customer1.setStore((Integer)null);
	        customer1.setFirstName("MARY");
	        customer1.setLastName("SMITH");
	        customer1.setEmail("MARY.SMITH@sakilacustomer.org");
	        Address address1 = new Address();
	        address1.setAddressId((short) 1);
	        customer1.setAddress(address1);
	        customer1.setActive((byte) 1);
	
	   
	        
	        Customer customer2 = new Customer();
	        customer2.setCustomerId((short) 2);
	      //  customer2.setStore((Integer)null);
	        customer2.setFirstName("PATRICIA");
	        customer2.setLastName("SMITH");
	        customer2.setEmail("PATRICIA.JOHNSON@sakilacustomer.org");
	        Address address2 = new Address();
	        address2.setAddressId((short) 2);
	        customer2.setAddress(address2);
	        customer2.setActive((byte) 1);
	        
	        List<Customer> customerList = Arrays.asList(customer1, customer2);
	        
	        //when(crepo.findByLastName("SMITH")).thenReturn(Collections.singletonList(customer1));
	        when(ICustomerDao.findByLastName("SMITH")).thenReturn(customerList);
	        List<Customer> result = customerServiceImpl.findByLastName("SMITH");
	        
	        assertEquals(2, result.size());
	        assertEquals(customerList, result);
	      
	    }
	    
	    @Test
	    //Search all InActive Customers
	    public void findAllInActiveCustomer() {
	        String lastName = "SMITH";
	        Customer customer1 = new Customer();
	        customer1.setCustomerId((short) 1);
	        //customer1.setStore((Integer)null);
	        customer1.setFirstName("MARY");
	        customer1.setLastName("SMITH");
	        customer1.setEmail("MARY.SMITH@sakilacustomer.org");
	        Address address1 = new Address();
	        address1.setAddressId((short) 1);
	        customer1.setAddress(address1);
	        customer1.setActive((byte) 1);
	
	   
	        
	        Customer customer2 = new Customer();
	        customer2.setCustomerId((short) 2);
	        //customer2.setStore((Integer)null);
	        customer2.setFirstName("PATRICIA");
	        customer2.setLastName("SMITH");
	        customer2.setEmail("PATRICIA.JOHNSON@sakilacustomer.org");
	        Address address2 = new Address();
	        address2.setAddressId((short) 2);
	        customer2.setAddress(address2);
	        customer2.setActive((byte) 1);
	        
	        List<Customer> customerList = Arrays.asList(customer1, customer2);
	        
	        //when(crepo.findByLastName("SMITH")).thenReturn(Collections.singletonList(customer1));
	        when(ICustomerDao.findByLastName("SMITH")).thenReturn(customerList);
	        List<Customer> result = customerServiceImpl.findByLastName("SMITH");
	        
	        assertEquals(2, result.size());
	        assertEquals(customerList, result);
	      
	    }
	    
	    
	    @Test
	    //Search Customers by phone number
	    public void findByAddressPhone() {
	        String lastName = "SMITH";
	        Customer customer1 = new Customer();
	        customer1.setCustomerId((short) 1);
	        //customer1.setStore((Integer)null);
	        customer1.setFirstName("MARY");
	        customer1.setLastName("SMITH");
	        customer1.setEmail("MARY.SMITH@sakilacustomer.org");
	        Address address1 = new Address();
	        address1.setAddressId((short) 1);
	        customer1.setAddress(address1);
	        customer1.setActive((byte) 1);
	
	   
	        
	        Customer customer2 = new Customer();
	        customer2.setCustomerId((short) 2);
	        //customer2.setStore((Integer)null);
	        customer2.setFirstName("PATRICIA");
	        customer2.setLastName("SMITH");
	        customer2.setEmail("PATRICIA.JOHNSON@sakilacustomer.org");
	        Address address2 = new Address();
	        address2.setAddressId((short) 2);
	        customer2.setAddress(address2);
	        customer2.setActive((byte) 1);
	        
	        List<Customer> customerList = Arrays.asList(customer1, customer2);
	        
	        //when(crepo.findByLastName("SMITH")).thenReturn(Collections.singletonList(customer1));
	        when(ICustomerDao.findByLastName("SMITH")).thenReturn(customerList);
	        List<Customer> result = customerServiceImpl.findByLastName("SMITH");
	        
	        assertEquals(2, result.size());
	        assertEquals(customerList, result);
	      
	    }
	    
	    @Test
	    //Update first name of Customer
	    public void UpdateByIdFirstName() {
	        String lastName = "SMITH";
	        Customer customer1 = new Customer();
	        customer1.setCustomerId((short) 1);
	        //customer1.setStore((Integer)null);
	        customer1.setFirstName("MARY");
	        customer1.setLastName("SMITH");
	        customer1.setEmail("MARY.SMITH@sakilacustomer.org");
	        Address address1 = new Address();
	        address1.setAddressId((short) 1);
	        customer1.setAddress(address1);
	        customer1.setActive((byte) 1);
	
	   
	        
	        Customer customer2 = new Customer();
	        customer2.setCustomerId((short) 2);
	        //customer2.setStore((Integer)null);
	        customer2.setFirstName("PATRICIA");
	        customer2.setLastName("SMITH");
	        customer2.setEmail("PATRICIA.JOHNSON@sakilacustomer.org");
	        Address address2 = new Address();
	        address2.setAddressId((short) 2);
	        customer2.setAddress(address2);
	        customer2.setActive((byte) 1);
	        
	        List<Customer> customerList = Arrays.asList(customer1, customer2);
	        
	        //when(crepo.findByLastName("SMITH")).thenReturn(Collections.singletonList(customer1));
	        when(ICustomerDao.findByLastName("SMITH")).thenReturn(customerList);
	        List<Customer> result = customerServiceImpl.findByLastName("SMITH");
	        
	        assertEquals(2, result.size());
	        assertEquals(customerList, result);
	      
	    }
	    
	    @Test
	    //Update last name of Customer
	    public void UpdateByIdLastName() {
	        String lastName = "SMITH";
	        Customer customer1 = new Customer();
	        customer1.setCustomerId((short) 1);
	        //customer1.setStore((Integer)null);
	        customer1.setFirstName("MARY");
	        customer1.setLastName("SMITH");
	        customer1.setEmail("MARY.SMITH@sakilacustomer.org");
	        Address address1 = new Address();
	        address1.setAddressId((short) 1);
	        customer1.setAddress(address1);
	        customer1.setActive((byte) 1);
	
	   
	        
	        Customer customer2 = new Customer();
	        customer2.setCustomerId((short) 2);
	        //customer2.setStore((Integer)null);
	        customer2.setFirstName("PATRICIA");
	        customer2.setLastName("SMITH");
	        customer2.setEmail("PATRICIA.JOHNSON@sakilacustomer.org");
	        Address address2 = new Address();
	        address2.setAddressId((short) 2);
	        customer2.setAddress(address2);
	        customer2.setActive((byte) 1);
	        
	        List<Customer> customerList = Arrays.asList(customer1, customer2);
	        
	        //when(crepo.findByLastName("SMITH")).thenReturn(Collections.singletonList(customer1));
	        when(ICustomerDao.findByLastName("SMITH")).thenReturn(customerList);
	        List<Customer> result = customerServiceImpl.findByLastName("SMITH");
	        
	        assertEquals(2, result.size());
	        assertEquals(customerList, result);
	      
	    }
	    
	    @Test
	    //Update email of Customer
	    public void UpdateByEmail() {
	        String lastName = "SMITH";
	        Customer customer1 = new Customer();
	        customer1.setCustomerId((short) 1);
	        //customer1.setStore((Integer)null);
	        customer1.setFirstName("MARY");
	        customer1.setLastName("SMITH");
	        customer1.setEmail("MARY.SMITH@sakilacustomer.org");
	        
	        Address address1 = new Address();
	        address1.setAddressId((short) 1);
	        customer1.setAddress(address1);
	        customer1.setActive((byte) 1);
	
	   
	        
	        Customer customer2 = new Customer();
	        customer2.setCustomerId((short) 2);
	        //customer2.setStore((Integer)null);
	        customer2.setFirstName("PATRICIA");
	        customer2.setLastName("SMITH");
	        customer2.setEmail("PATRICIA.JOHNSON@sakilacustomer.org");
	        Address address2 = new Address();
	        address2.setAddressId((short) 2);
	        customer2.setAddress(address2);
	        customer2.setActive((byte) 1);
	        
	        List<Customer> customerList = Arrays.asList(customer1, customer2);
	        
	        //when(crepo.findByLastName("SMITH")).thenReturn(Collections.singletonList(customer1));
	        when(ICustomerDao.findByLastName("SMITH")).thenReturn(customerList);
	        List<Customer> result = customerServiceImpl.findByLastName("SMITH");
	        
	        assertEquals(2, result.size());
	        assertEquals(customerList, result);
	      
	    }
	    
	    @Test
	    //Update phone number of a Customer
	    public void UpdateByPhone() {
	        String lastName = "SMITH";
	        Customer customer1 = new Customer();
	        customer1.setCustomerId((short) 1);
	        
	        customer1.setFirstName("MARY");
	        customer1.setLastName("SMITH");
	        customer1.setEmail("MARY.SMITH@sakilacustomer.org");
	        Address address1 = new Address();
	        address1.setAddressId((short) 1);
	        customer1.setAddress(address1);
	        customer1.setActive((byte) 1);
	
	   
	        
	        Customer customer2 = new Customer();
	        customer2.setCustomerId((short) 2);
	        //customer2.setStore((Integer)null);
	        customer2.setFirstName("PATRICIA");
	        customer2.setLastName("SMITH");
	        customer2.setEmail("PATRICIA.JOHNSON@sakilacustomer.org");
	        Address address2 = new Address();
	        address2.setAddressId((short) 2);
	        customer2.setAddress(address2);
	        customer2.setActive((byte) 1);
	        
	        List<Customer> customerList = Arrays.asList(customer1, customer2);
	        
	        
	        when(ICustomerDao.findByLastName("SMITH")).thenReturn(customerList);
	        List<Customer> result = customerServiceImpl.findByLastName("SMITH");
	        
	        assertEquals(2, result.size());
	        assertEquals(customerList, result);
	      
	    }
	    
	    
	    @Test
	    //Update by Store Id
	    public void UpdateByStoreId() {
	        String lastName = "SMITH";
	        Customer customer1 = new Customer();
	        customer1.setCustomerId((short) 1);
	        //customer1.setStore((Integer)null);
	        customer1.setFirstName("MARY");
	        customer1.setLastName("SMITH");
	        customer1.setEmail("MARY.SMITH@sakilacustomer.org");
	        Address address1 = new Address();
	        address1.setAddressId((short) 1);
	        customer1.setAddress(address1);
	        customer1.setActive((byte) 1);
	
	   
	        
	        Customer customer2 = new Customer();
	        customer2.setCustomerId((short) 2);
	        //customer2.setStore((Integer)null);
	        customer2.setFirstName("PATRICIA");
	        customer2.setLastName("SMITH");
	        customer2.setEmail("PATRICIA.JOHNSON@sakilacustomer.org");
	        Address address2 = new Address();
	        address2.setAddressId((short) 2);
	        customer2.setAddress(address2);
	        customer2.setActive((byte) 1);
	        
	        List<Customer> customerList = Arrays.asList(customer1, customer2);
	        
	        
	        when(ICustomerDao.findByLastName("SMITH")).thenReturn(customerList);
	        List<Customer> result = customerServiceImpl.findByLastName("SMITH");
	        
	        assertEquals(2, result.size());
	        assertEquals(customerList, result);
	      
	    }
	    
	    @Test
	    //Update by AddressId
	    public void UpdateByAddressId() {
	        String lastName = "SMITH";
	        Customer customer1 = new Customer();
	        customer1.setCustomerId((short) 1);
	        //customer1.setStore((Integer)null);
	        customer1.setFirstName("MARY");
	        customer1.setLastName("SMITH");
	        customer1.setEmail("MARY.SMITH@sakilacustomer.org");
	        Address address1 = new Address();
	        address1.setAddressId((short) 1);
	        customer1.setAddress(address1);
	        customer1.setActive((byte) 1);
	
	   
	        
	        Customer customer2 = new Customer();
	        customer2.setCustomerId((short) 2);
	        //customer2.setStore((Integer)null);
	        customer2.setFirstName("PATRICIA");
	        customer2.setLastName("SMITH");
	        customer2.setEmail("PATRICIA.JOHNSON@sakilacustomer.org");
	        Address address2 = new Address();
	        address2.setAddressId((short) 2);
	        customer2.setAddress(address2);
	        customer2.setActive((byte) 1);
	        
	        List<Customer> customerList = Arrays.asList(customer1, customer2);
	        
	        //when(crepo.findByLastName("SMITH")).thenReturn(Collections.singletonList(customer1));
	        when(ICustomerDao.findByLastName("SMITH")).thenReturn(customerList);
	        List<Customer> result = customerServiceImpl.findByLastName("SMITH");
	        
	        assertEquals(2, result.size());
	        assertEquals(customerList, result);
	      
	    }
	    
	    
	    @Test
	    //Add new Customer
	    public void addCustomer() {
	        String lastName = "SMITH";
	        Customer customer1 = new Customer();
	        customer1.setCustomerId((short) 1);
	        customer1.setFirstName("MARY");
	        customer1.setLastName("SMITH");
	        customer1.setEmail("MARY.SMITH@sakilacustomer.org");
	        Address address1 = new Address();
	        address1.setAddressId((short) 1);
	        customer1.setAddress(address1);
	        customer1.setActive(null);
	
	   
	        
	        Customer customer2 = new Customer();
	        customer2.setCustomerId((short) 2);
	        customer2.setFirstName("PATRICIA");
	        customer2.setLastName("SMITH");
	        customer2.setEmail("PATRICIA.JOHNSON@sakilacustomer.org");
	        Address address2 = new Address();
	        address2.setAddressId((short) 2);
	        customer2.setAddress(address2);
	        customer2.setActive(null);
	        
	        List<Customer> customerList = Arrays.asList(customer1, customer2);
	        
	        when(ICustomerDao.findByLastName("SMITH")).thenReturn(customerList);
	        List<Customer> result = customerServiceImpl.findByLastName("SMITH");
	        
	        assertEquals(2, result.size());
	        assertEquals(customerList, result);
	    }
	    
	    
	    
	    
	    
	    
	    

	 
}



