package org.filmrental.com.dao;

import java.util.List;

import org.filmrental.com.dto.DatewiseCummulativeRevenue;
import org.filmrental.com.dto.FilmRevenue;
import org.filmrental.com.dto.StoreRevenue;
import org.filmrental.com.model.Payment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentDao extends CrudRepository<Payment,Integer>{
	
	@Query("SELECT NEW org.filmrental.com.dto.DatewiseCummulativeRevenue(p.paymentDate, SUM(p.amount)) " +
		       "FROM Payment p " +
		       "GROUP BY p.paymentDate")
	List<DatewiseCummulativeRevenue> dateWiseCummulativeAmount();

	@Query("SELECT NEW org.filmrental.com.dto.FilmRevenue(p.rental.inventory.film, SUM(p.amount)) " +
		       "FROM Payment p " +
		       "GROUP BY p.rental.inventory.film")
	List<FilmRevenue> filmWiseCummulativeRevenue();
	
	@Query("select NEW org.filmrental.com.dto.DatewiseCummulativeRevenue(p.paymentDate, sum(p.amount)) " +
		       "FROM Payment p JOIN p.staff s JOIN s.store st " +
		       "WHERE st.storeId=:storeId " +
		       "GROUP BY p.paymentDate")
	List<DatewiseCummulativeRevenue> dateWiseCummulativeRevenueOfStore(@Param("storeId") int storeId);

	@Query("SELECT NEW org.filmrental.com.dto.StoreRevenue(s.storeId, SUM(p.amount)) " +
		       "FROM Payment p " +
		       "JOIN p.rental r " +
		       "JOIN r.inventory i " +
		       "JOIN i.store s " +
		       "WHERE i.film.filmId = :filmId " +
		       "GROUP BY s.storeId")
	List<StoreRevenue> storeWiseCummulativeRevenueOfFilm(@Param("filmId") int filmId);
	
	@Query("SELECT NEW org.filmrental.com.dto.FilmRevenue(p.rental.inventory.film, SUM(p.amount)) " +
		       "FROM Payment p JOIN p.staff s JOIN s.store st " +
		       "WHERE st.storeId = :storeId " +
		       "GROUP BY p.rental.inventory.film")
	List<FilmRevenue> filmWiseCummulativeRevenueOfStore(@Param("storeId") int storeId);


	

}
