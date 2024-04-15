package org.filmrental.com.controller;

import java.util.List;

import org.filmrental.com.dto.Count;
import org.filmrental.com.dto.InventoryCount;
import org.filmrental.com.exception.DataNotFoundException;
import org.filmrental.com.exception.IdNotFoundException;
import org.filmrental.com.model.Film;
import org.filmrental.com.model.Inventory;
import org.filmrental.com.service.IInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/inventory")
@RestController
public class InventoryRestController {
	
	@Autowired
    IInventoryService inventoryService;
	
	//Add inventory details 
	
	@PostMapping("/add")
	public ResponseEntity<Inventory> createInventory(@RequestBody Inventory inventory){
		Inventory inventory2= inventoryService.addInventory(inventory);
		if(inventory2 == null || inventory2.getInventoryId()==0) {
			throw new DataNotFoundException("Record Creation Error");
		}else {
		return new ResponseEntity("Record Created Successfully", HttpStatus.OK);
		}
	}
	
	
	//Get inventory count of all films in all stores
	@GetMapping("/films")
    public ResponseEntity<List<Count>> getInventoryCountOfAllFilms() {
        List<Count> inventory = inventoryService.getInventoryCountOfAllFilms();
        if(inventory.isEmpty() || inventory == null) {
			throw new DataNotFoundException("Film List is empty");
		}else{
		return new ResponseEntity<List<Count>>(inventory, HttpStatus.OK);
        }

    }
	
    //Get inventory count of all films by a store
	
	@GetMapping("/store/{storeId}")
  public ResponseEntity<List<Count>>getInventoryCountByStore(@PathVariable int storeId) {
      List<Count> inventory = inventoryService.getInventoryCountByStore(storeId);
      if(inventory.isEmpty() || inventory == null) {
			throw new IdNotFoundException("Store Id not found");
		}else {
	  		return new ResponseEntity<List<Count>>(inventory, HttpStatus.OK);
		}
     }
	
	
	// Get the inventory count of film in all stores
	
	@GetMapping("/film/{filmId}")
    public ResponseEntity<List<InventoryCount>> getInventoryCountByFilm(@PathVariable int filmId) {
        List<InventoryCount> inventory = inventoryService.getInventoryCountByFilm(filmId);
        if(inventory.isEmpty() || inventory == null) {
			throw new IdNotFoundException("Film Id not found");
		}else{
		return new ResponseEntity<List<InventoryCount>>(inventory, HttpStatus.OK);
        }

    }
	
	// Get inventory count of a film in a store 
	
	@GetMapping("/film/{filmId}/store/{storeId}")
    public ResponseEntity<List<InventoryCount>> getInventoryCountByFilmAndStore(@PathVariable int filmId, @PathVariable int storeId) {
        List<InventoryCount> inventory = inventoryService.getInventoryCountByFilmAndStore(filmId, storeId);
        if(inventory.isEmpty() || inventory == null) {
			throw new IdNotFoundException("Film Id and Store Id not found");
		}else{
 		return new ResponseEntity<List<InventoryCount>>(inventory, HttpStatus.OK);
		}
    }
	
	
	
	






}
