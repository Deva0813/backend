package org.filmrental.com.service;

import java.util.List;

import org.filmrental.com.dao.IInventoryDao;
import org.filmrental.com.dao.IStoreDao;
import org.filmrental.com.dto.Count;
import org.filmrental.com.dto.InventoryCount;
import org.filmrental.com.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements IInventoryService{
	
	@Autowired
	private IInventoryDao inventoryDao;
	
//	@Autowired
//	private IFilmDao filmDao;
	


	//@Autowired
	//private IStoreDao storeDao;


	@Override
	public Inventory addInventory(Inventory inventory) {
		// TODO Auto-generated method stub
		return inventoryDao.save(inventory);
	}

	@Override
	public List<Count> getInventoryCountOfAllFilms() {
		// TODO Auto-generated method stub
		return inventoryDao.findInventoryCountOfAllFilms();


	}

	@Override
	public List<InventoryCount> getInventoryCountByFilm(int filmId) {
		// TODO Auto-generated method stub
		return inventoryDao.findInventoryCountByFilm(filmId);
	}

	@Override
	public List<Count> getInventoryCountByStore(int storeId) {
		List<Count> list = inventoryDao.findInventoryCountByStore(storeId);
	
		
		return inventoryDao.findInventoryCountByStore(storeId);
	}

	@Override
	public List<InventoryCount> getInventoryCountByFilmAndStore(int filmId, int storeId) {
		List<InventoryCount> list = inventoryDao.findInventoryCountByFilmAndStore(filmId,storeId);
		
		return inventoryDao.findInventoryCountByFilmAndStore(filmId,storeId);
	}
	
	
	
	

}
