package com.example.realestate.services;

import com.example.realestate.dtos.listings.BuildingDTO;
import com.example.realestate.models.Address;
import com.example.realestate.models.Building;

public interface BuildingService {
	Building findOrCreate(Address address, BuildingDTO buildingDTO);

	void update(long id, BuildingDTO buildingDTO);

	void delete(long id);

	BuildingDTO toDTO(Building building);
}
