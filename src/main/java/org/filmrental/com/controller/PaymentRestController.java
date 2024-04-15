package org.filmrental.com.controller;


import java.math.BigDecimal;
import java.util.List;

import org.filmrental.com.dto.DatewiseCummulativeRevenue;
import org.filmrental.com.dto.FilmRevenue;
import org.filmrental.com.dto.StoreRevenue;
import org.filmrental.com.exception.DataNotFoundException;
import org.filmrental.com.exception.IdNotFoundException;
import org.filmrental.com.exception.NegativeValueException;
import org.filmrental.com.model.Payment;
import org.filmrental.com.service.IPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/payment/add")
@RestController
public class PaymentRestController {
	
	@Autowired
    IPaymentService paymentService;
	
	
	//Add payments details 
	
	@PostMapping
	public ResponseEntity<Payment> createPayment(@RequestBody Payment payment){
		Payment payment2= paymentService.addPayment(payment);
		
		if(payment == null || payment.getPaymentId()==0) {
			throw new DataNotFoundException("Record Creation Error");
		}else {
		return new ResponseEntity("Record Created Successfully", HttpStatus.OK);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<Payment>>getAllPayment() {
		List<Payment>payment=paymentService.getAllPayment();
		
		return new ResponseEntity<List<Payment>>(payment,HttpStatus.OK);
				
	}
	
	// Get cumulative revenue of all stores date wise
	
	@GetMapping("/revenue/datewise")
	public ResponseEntity<List<DatewiseCummulativeRevenue>> dateWiseCummulativeAmount() {
		List<DatewiseCummulativeRevenue> revenue=paymentService.dateWiseCummulativeAmount();
		for (DatewiseCummulativeRevenue revenue1 : revenue) {
	        if (revenue1.revenue().compareTo(BigDecimal.ZERO) < 0) {
	            throw new NegativeValueException("Cummulative Revenue is negative");
	        }
		
		}
	        	
	    		return new ResponseEntity<List<DatewiseCummulativeRevenue>>(revenue, HttpStatus.OK);
	    }
	

	// Get cumulative revenue of all films across all stores
	
	@GetMapping("/revenue/filmwise")
	public ResponseEntity<List<FilmRevenue>> filmWiseCummulativeRevenue() {
		List<FilmRevenue> revenue=paymentService.filmWiseCummulativeRevenue();
		for (FilmRevenue revenue1 : revenue) {
	        if (revenue1.revenue().compareTo(BigDecimal.ZERO) < 0) {
	            throw new NegativeValueException("Cummulative Revenue is negative");
	        }
	    }
		return new ResponseEntity<List<FilmRevenue>>(revenue,HttpStatus.OK);

		
	}

	// Get cumulative revenue of a store date wise
	
	@GetMapping("/revenue/datewise/store/{id}")
	public ResponseEntity<List<DatewiseCummulativeRevenue>> dateWiseCummulativeRevenueOfStore(@PathVariable("id") int storeId) {
		List<DatewiseCummulativeRevenue> payment=paymentService.dateWiseCummulativeRevenueOfStore(storeId);
		if(payment.isEmpty() || payment == null) {
			throw new IdNotFoundException("Store Id not found");
		}else {

		return new ResponseEntity<List<DatewiseCummulativeRevenue>>(payment,HttpStatus.OK);
		}
	}

	// Get cumulative revenue of a film store wise
	
	@GetMapping("/revenue/store/film/{id}")
	public ResponseEntity<List<StoreRevenue>> storeWiseCummulativeRevenueOfFilm(@PathVariable("id") int filmId) {
		List<StoreRevenue> payment=paymentService.storeWiseCummulativeRevenueOfFilm(filmId);

		if(payment.isEmpty() || payment == null) {
			throw new IdNotFoundException("Film Id not found");
		}else {

		return new ResponseEntity<List<StoreRevenue>>(payment,HttpStatus.OK);
		}
	}
	
	// Get cumulative revenue of all films by store
	
	@GetMapping("/revenue/film/store/{id}")
	public ResponseEntity<List<FilmRevenue>> filmWiseCummulativeRevenueOfStore(@PathVariable("id") int storeId){
		List<FilmRevenue> payment=paymentService.filmWiseCummulativeRevenueOfStore(storeId);

		if(payment.isEmpty() || payment == null) {
			throw new IdNotFoundException("Store Id not found");
		}else {

		return new ResponseEntity<List<FilmRevenue>>(payment,HttpStatus.OK);
		}
	}


	

}
