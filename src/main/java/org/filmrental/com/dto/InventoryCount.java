package org.filmrental.com.dto;

import org.filmrental.com.model.Address;

public record InventoryCount(Address storeAddress, Long count) {
	public InventoryCount(String storeAddress, int count) {
        this(new Address(storeAddress), (long) count); // Call the canonical constructor
		 //this(storeAddress, Long.valueOf(count));
    }

	
}
