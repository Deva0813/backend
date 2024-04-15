package org.filmrental.com.dao;

import java.util.List;

import org.filmrental.com.dto.Count;
import org.filmrental.com.dto.InventoryCount;
import org.filmrental.com.model.Inventory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IInventoryDao extends CrudRepository<Inventory,Integer>{
	
    @Query("select new org.filmrental.com.dto.Count(i.film.title, count(i)) from Inventory i GROUP BY i.film.title")
	List<Count> findInventoryCountOfAllFilms();
	
	
    @Query("select new org.filmrental.com.dto.Count(i.film.title, count(i)) from Inventory i where i.store.storeId = :storeId group by i.film.title")
	List<Count> findInventoryCountByStore(@Param("storeId") int storeId);
	
	@Query("select new org.filmrental.com.dto.InventoryCount(i.store.address, count(i)) from Inventory i where i.film.filmId = :filmId group by i.store.address")
	List<InventoryCount> findInventoryCountByFilm(@Param("filmId") int filmId);
	
	@Query("select new org.filmrental.com.dto.InventoryCount(i.store.address, count(i)) " +
	           "from Inventory i where i.film.filmId = :filmId and i.store.storeId = :storeId " +
	           "group by i.store.address")
	List<InventoryCount> findInventoryCountByFilmAndStore(@Param("filmId") int filmId, @Param("storeId") int storeId);



}
