package org.filmrental.com.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.filmrental.com.dao.IPaymentDao;
import org.filmrental.com.dto.DatewiseCummulativeRevenue;
import org.filmrental.com.dto.FilmRevenue;
import org.filmrental.com.dto.StoreRevenue;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.Payment;
import org.filmrental.com.service.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class PaymentControllerTest {
	
	
	@Mock
	private IPaymentDao paymentDao;

	@InjectMocks
	private PaymentServiceImpl paymentServiceImpl;
	
	@BeforeEach
	void setUp() {
	    MockitoAnnotations.openMocks(this);
	}
	
	// Testing for Add Payment
	
	@Test
    void AddPaymentTest() {
		Payment payment = new Payment();
		payment.setPaymentId(1); 
        when(paymentDao.save(any(Payment.class))).thenReturn(payment);
        Payment addedPayment = paymentServiceImpl.addPayment(payment);
        assertEquals(payment.getPaymentId(), addedPayment.getPaymentId());
    }
	
	// Testing for cumulative revenue of all stores date wise
	
	@Test
    public void DateWiseCummulativeAmountTest() {
        // Create sample data
        List<DatewiseCummulativeRevenue> testData = new ArrayList<>();
        testData.add(new DatewiseCummulativeRevenue(LocalDate.of(2024, 4, 1), BigDecimal.valueOf(100.00)));
        testData.add(new DatewiseCummulativeRevenue(LocalDate.of(2024, 4, 2), BigDecimal.valueOf(150.00)));
        // Mock the behavior of the paymentDao
        Mockito.when(paymentDao.dateWiseCummulativeAmount()).thenReturn(testData);

        // Call the service method
        List<DatewiseCummulativeRevenue> result = paymentServiceImpl.dateWiseCummulativeAmount();

        // Assert the result
        assertEquals(2, result.size());
        assertEquals(LocalDate.of(2024, 4, 1), result.get(0).paymentDate());
        assertEquals(BigDecimal.valueOf(100.00), result.get(0).revenue());
        assertEquals(LocalDate.of(2024, 4, 2), result.get(1).paymentDate());
        assertEquals(BigDecimal.valueOf(150.00), result.get(1).revenue());
    }

	
	// Testing for cumulative revenue of all films across all stores
	
	@Test
	void filmWiseCummulativeRevenueTest() {

		Film filmA = new Film("Film A");
	    Film filmB = new Film("Film B");
	    BigDecimal revenueA = new BigDecimal("500.00");
	    BigDecimal revenueB = new BigDecimal("750.00");

	    FilmRevenue filmARevenue = new FilmRevenue(filmA, revenueA);
	    FilmRevenue filmBRevenue = new FilmRevenue(filmB, revenueB);

    List<FilmRevenue> filmData = List.of(filmARevenue, filmBRevenue);

    // Mock the behavior of paymentDao.filmWiseCummulativeRevenue()
    when(paymentDao.filmWiseCummulativeRevenue()).thenReturn(filmData);

    // Call the service method that uses filmWiseCummulativeRevenue()
    List<FilmRevenue> result = paymentServiceImpl.filmWiseCummulativeRevenue();

    // Assert the results
    assertEquals(filmData.size(), result.size());
    for (int i = 0; i < filmData.size(); i++) {
        assertEquals(filmData.get(i).film(), result.get(i).film()); // Film name
        assertEquals(filmData.get(i).revenue(), result.get(i).revenue()); // Cumulative revenue
    }
	}
	
	
	// Testing for cumulative revenue of a store date wise
	
	@Test
    void dateWiseCummulativeRevenueOfStoreTest() {
		int storeId = 1;
	    
	    // Sample data
	    LocalDate revenueDate1 = LocalDate.of(2024, 1, 1);
	    BigDecimal amount1 = new BigDecimal("100.00");
	    LocalDate revenueDate2 = LocalDate.of(2024, 1, 2);
	    BigDecimal amount2 = new BigDecimal("150.00");

	    // Mocking the behavior of paymentDao.dateWiseCummulativeRevenueOfStore()
	    List<DatewiseCummulativeRevenue> expectedRevenueList = new ArrayList<>();
	    DatewiseCummulativeRevenue revenueRecord1 = new DatewiseCummulativeRevenue(revenueDate1, amount1);
	    DatewiseCummulativeRevenue revenueRecord2 = new DatewiseCummulativeRevenue(revenueDate2, amount2);
	    expectedRevenueList.add(revenueRecord1);
	    expectedRevenueList.add(revenueRecord2);
	    when(paymentDao.dateWiseCummulativeRevenueOfStore(storeId)).thenReturn(expectedRevenueList);

	    // Call the service method
	    List<DatewiseCummulativeRevenue> result = paymentServiceImpl.dateWiseCummulativeRevenueOfStore(storeId);

	    // Assert the results
	    assertEquals(expectedRevenueList.size(), result.size());
	    for (int i = 0; i < expectedRevenueList.size(); i++) {
	        assertEquals(expectedRevenueList.get(i).paymentDate(), result.get(i).paymentDate());
	        assertEquals(expectedRevenueList.get(i).revenue(), result.get(i).revenue());
	    }
        
	}
	
	
	// Testing for cumulative revenue of all films by store
	
	@Test
    void filmWiseCummulativeRevenueOfStoreTest() {
        int storeId = 1;
        
        Film film1 = new Film("Film 1");
        Film film2 = new Film("Film 2");


        // Mocking the behavior of paymentDao.filmWiseCummulativeRevenueOfStore()
        List<FilmRevenue> expectedRevenueList = new ArrayList<>();
        FilmRevenue revenue1 = new FilmRevenue(film1, new BigDecimal("100.00"));
        FilmRevenue revenue2 = new FilmRevenue(film2, new BigDecimal("150.00"));
        expectedRevenueList.add(revenue1);
        expectedRevenueList.add(revenue2);
        when(paymentDao.filmWiseCummulativeRevenueOfStore(storeId)).thenReturn(expectedRevenueList);

        // Calling the service method
        List<FilmRevenue> actualRevenueList = paymentServiceImpl.filmWiseCummulativeRevenueOfStore(storeId);

        // Verifying the result
        assertEquals(expectedRevenueList.size(), actualRevenueList.size());
        for (int i = 0; i < expectedRevenueList.size(); i++) {
            FilmRevenue expectedRevenue = expectedRevenueList.get(i);
            FilmRevenue actualRevenue = actualRevenueList.get(i);
            assertEquals(expectedRevenue.film(), actualRevenue.film());
            assertEquals(expectedRevenue.revenue(), actualRevenue.revenue());
        }

        // Verifying that paymentDao.filmWiseCummulativeRevenueOfStore() was called with the correct parameter
        verify(paymentDao).filmWiseCummulativeRevenueOfStore(storeId);
    }

	
	// Testing for cumulative revenue of a film store wise 
	
	@Test
    void storeWiseCummulativeRevenueOfFilmTest() {
		int filmId = 1;
        
        Film store1 = new Film("Store 1");
        Film store2 = new Film("Store 2");


        // Mocking the behavior of paymentDao.filmWiseCummulativeRevenueOfStore()
        List<StoreRevenue> expectedRevenueList = new ArrayList<>();
        StoreRevenue revenue1 = new StoreRevenue(1, new BigDecimal("100.00")); // Assuming store ID is 1
        StoreRevenue revenue2 = new StoreRevenue(2, new BigDecimal("150.00")); // Assuming store ID is 2
        expectedRevenueList.add(revenue1);
        expectedRevenueList.add(revenue2);
        when(paymentDao.storeWiseCummulativeRevenueOfFilm(filmId)).thenReturn(expectedRevenueList);

        // Calling the service method
        List<StoreRevenue> actualRevenueList = paymentServiceImpl.storeWiseCummulativeRevenueOfFilm(filmId);

        // Verifying the result
        assertEquals(expectedRevenueList.size(), actualRevenueList.size());
        for (int i = 0; i < expectedRevenueList.size(); i++) {
            StoreRevenue expectedRevenue = expectedRevenueList.get(i);
            StoreRevenue actualRevenue = actualRevenueList.get(i);
            assertEquals(expectedRevenue.storeId(), actualRevenue.storeId());
            assertEquals(expectedRevenue.revenue(), actualRevenue.revenue());
        }
	        
	}
	
	

	
	
	
}

			
	
	
	


