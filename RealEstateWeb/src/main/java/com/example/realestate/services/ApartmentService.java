package com.example.realestate.services;

import java.util.List;

import com.example.realestate.dtos.listings.ApartmentDTO;
import com.example.realestate.models.Apartment;
import com.example.realestate.models.Building;
import com.example.realestate.models.Item;

public interface ApartmentService {
	Apartment findOrCreate(Building building, List<Item> items, ApartmentDTO apartmentDTO);

	void update(long id, List<Item> items, ApartmentDTO apartmentDTO);

	void delete(long id);

	ApartmentDTO toDTO(Apartment apartment);
}
