package org.filmrental.com.service;

import java.util.List;

import org.filmrental.com.dto.DatewiseCummulativeRevenue;
import org.filmrental.com.dto.FilmRevenue;
import org.filmrental.com.dto.StoreRevenue;
import org.filmrental.com.model.Payment;

public interface IPaymentService {
	
	public Payment addPayment(Payment payment);
	
	
	public List<Payment>getAllPayment();
	
	List<DatewiseCummulativeRevenue> dateWiseCummulativeAmount();

	List<FilmRevenue> filmWiseCummulativeRevenue();
	
	List<DatewiseCummulativeRevenue> dateWiseCummulativeRevenueOfStore(int storeId);

	List<StoreRevenue> storeWiseCummulativeRevenueOfFilm(int filmId);

		List<FilmRevenue> filmWiseCummulativeRevenueOfStore(int storeId);


}
