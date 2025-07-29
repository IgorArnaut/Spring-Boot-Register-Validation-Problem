package com.example.realestate.services;

import java.util.List;

import com.example.realestate.dtos.listings.AddressDTO;
import com.example.realestate.models.Address;

public interface AddressService {
	List<String> allCities();

	Address findOrCreate(AddressDTO addressDTO);

	void update(long id, AddressDTO addressDTO);

	AddressDTO toDTO(Address address);
}
