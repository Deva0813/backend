package org.filmrental.com.service;

import java.util.List;

import org.filmrental.com.dto.Count;
import org.filmrental.com.dto.InventoryCount;
import org.filmrental.com.model.Inventory;

public interface IInventoryService {
	
public Inventory addInventory(Inventory inventory);
	
	List<Count> getInventoryCountByStore(int storeId);
    
	List<Count> getInventoryCountOfAllFilms();
	
	List<InventoryCount> getInventoryCountByFilmAndStore(int filmId, int storeId);

    List<InventoryCount> getInventoryCountByFilm(int filmId);



}
