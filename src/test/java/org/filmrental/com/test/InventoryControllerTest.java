package org.filmrental.com.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.List;

import org.filmrental.com.dao.IInventoryDao;
import org.filmrental.com.dto.Count;
import org.filmrental.com.dto.InventoryCount;
import org.filmrental.com.model.Address;
import org.filmrental.com.model.Inventory;
import org.filmrental.com.service.InventoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class InventoryControllerTest {
	
	@Mock
	private IInventoryDao inventoryDao;

	@InjectMocks
	private InventoryServiceImpl inventoryServiceImpl;
	
	@BeforeEach
	void setUp() {
	    MockitoAnnotations.openMocks(this);
	}
	
	// Testing for add inventory
	
	@Test
    void AddInventoryTest() {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(1); 
        when(inventoryDao.save(any(Inventory.class))).thenReturn(inventory);
        Inventory addedInventory = inventoryServiceImpl.addInventory(inventory);
        assertEquals(inventory.getInventoryId(), addedInventory.getInventoryId());
    }
	
	// Testing for inventory count of all films
	
	@Test
	void InventoryCountOfAllFilmsTest() {
	    Count film = new Count("Film A", 5L);
	    List<Count> films = new ArrayList<>();
	    films.add(film);
	    
	    when(inventoryDao.findInventoryCountOfAllFilms()).thenReturn(films);
	    List<Count> result = inventoryServiceImpl.getInventoryCountOfAllFilms();

	    assertEquals(films.size(), result.size()); 
	    for (int i = 0; i < films.size(); i++) {
	        Count expectedFilmData = films.get(i);
	        Count actualFilmData = result.get(i);
	        assertEquals(expectedFilmData.title(), actualFilmData.title());
	        assertEquals(expectedFilmData.count(), actualFilmData.count());
	    }
	}
		
	// 	Testing for inventory count of all films by a store
	
		@Test
		void getInventoryCountByStoreTest() {
		
			int storeId=1;
			Count film = new Count("Film A", 5L);
        List<Count> films = new ArrayList<>();
        films.add(film);
        
        when(inventoryDao.findInventoryCountByStore(storeId)).thenReturn(films);
        List<Count> result = inventoryServiceImpl.getInventoryCountByStore(storeId);

        assertEquals(films.size(), result.size()); 
        for (int i = 0; i < films.size(); i++) {
        	Count expectedFilmData = films.get(i);
        	Count actualFilmData = result.get(i);
        	assertEquals(expectedFilmData.title(), actualFilmData.title()); // Use accessor methods
            assertEquals(expectedFilmData.count(), actualFilmData.count());
        }
    }
		
		
	// Testing for inventory count of a film in all stores
		
		@Test
		void getInventoryCountByFilmTest() {
		
			int filmId=1;
		    InventoryCount film = new InventoryCount(new Address("Film A"), 5L); // Creating InventoryCount object with Address and Long
        List<InventoryCount> films = new ArrayList<>();
        films.add(film);
        
        when(inventoryDao.findInventoryCountByFilm(filmId)).thenReturn(films);
        List<InventoryCount> result = inventoryServiceImpl.getInventoryCountByFilm(filmId);

        assertEquals(films.size(), result.size()); 
        for (int i = 0; i < films.size(); i++) {
        	InventoryCount expectedFilmData = films.get(i);
        	InventoryCount actualFilmData = result.get(i);
        	assertEquals(expectedFilmData.storeAddress(), actualFilmData.storeAddress()); // Use accessor methods
            assertEquals(expectedFilmData.count(), actualFilmData.count());
        }
    }
	
		
	// Testing for inventory count of a film in a store
		
		@Test
		void getInventoryCountByFilmAndStoreTest() {
		
			int filmId = 1;
		    int storeId = 1;

		    // Create an InventoryCount object using the record's constructor
		    InventoryCount inventoryCount = new InventoryCount(new String("Film A"), 5);
		    
		    List<InventoryCount> films = new ArrayList<>();
		    films.add(inventoryCount);
		    
		    // Mocking behavior of the DAO method
		    when(inventoryDao.findInventoryCountByFilmAndStore(filmId, storeId)).thenReturn(films);
		    
		    List<InventoryCount> result = inventoryServiceImpl.getInventoryCountByFilmAndStore(filmId, storeId);

		    assertEquals(films.size(), result.size()); 
		    for (int i = 0; i < films.size(); i++) {
		        InventoryCount expectedFilmData = films.get(i);
		        InventoryCount actualFilmData = result.get(i);
		        assertEquals(expectedFilmData.storeAddress(), actualFilmData.storeAddress());
		        assertEquals(expectedFilmData.count(), actualFilmData.count());
		    }
	
	
	
	
	}
		
}
	
	
	
	



