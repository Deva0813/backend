package org.filmrental.com.service;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.filmrental.com.dao.IPaymentDao;
import org.filmrental.com.dao.IRentalDao;
import org.filmrental.com.dto.DatewiseCummulativeRevenue;
import org.filmrental.com.dto.FilmRevenue;
import org.filmrental.com.dto.StoreRevenue;
import org.filmrental.com.model.Payment;
import org.filmrental.com.model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements IPaymentService{
	
	@Autowired
	private IPaymentDao paymentDao;
	
	@Autowired
	private IRentalDao rentalDao;
	

	

	@Override
	public Payment addPayment(Payment payment) {
		// TODO Auto-generated method stub
		Rental rental = payment.getRental();
		rentalDao.save(rental);
		payment.getRental().setRentalId(rental.getRentalId());
		return paymentDao.save(payment);
		
	}
	
	@Override
	public List<Payment> getAllPayment() {
		// TODO Auto-generated method stub
		return (List<Payment>) paymentDao.findAll() ;
	}

	@Override
	public List<DatewiseCummulativeRevenue> dateWiseCummulativeAmount() {
		// TODO Auto-generated method stub
		List<DatewiseCummulativeRevenue> results = paymentDao.dateWiseCummulativeAmount();		
		List<DatewiseCummulativeRevenue> revenue =new ArrayList<>();
		for (DatewiseCummulativeRevenue obj : results) {
	        DatewiseCummulativeRevenue revenue1 = new DatewiseCummulativeRevenue(obj.paymentDate(), obj.revenue());
	        revenue.add(revenue1);
	    }
	    return revenue;
	}

	@Override
	public List<FilmRevenue> filmWiseCummulativeRevenue() {
		// TODO Auto-generated method stub
		return paymentDao.filmWiseCummulativeRevenue();

	}

	@Override
	public List<DatewiseCummulativeRevenue> dateWiseCummulativeRevenueOfStore(int storeId) {
		// TODO Auto-generated method stub
		return paymentDao.dateWiseCummulativeRevenueOfStore(storeId);

	}

	@Override
	public List<FilmRevenue> filmWiseCummulativeRevenueOfStore(int storeId) {
		// TODO Auto-generated method stub
		return paymentDao.filmWiseCummulativeRevenueOfStore(storeId);

	}

	@Override
	public List<StoreRevenue> storeWiseCummulativeRevenueOfFilm(int filmId) {
		// TODO Auto-generated method stub
		return paymentDao.storeWiseCummulativeRevenueOfFilm(filmId);

	}
}
