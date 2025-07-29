package com.example.realestate.services.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.realestate.dtos.listings.AddressDTO;
import com.example.realestate.mappers.AddressMapper;
import com.example.realestate.models.Address;
import com.example.realestate.repositories.AddressRepository;
import com.example.realestate.services.AddressService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AddressServiceImpl implements AddressService {
	@Autowired
	private AddressRepository repository;
	@Autowired
	private AddressMapper mapper;

	@Override
	public List<String> allCities() {
		return Arrays.asList(
			"Beograd",
			"Bor",
			"Valjevo",
			"Vranje",
			"Vršac",
			"Zaječar",
			"Zrenjanin",
			"Jagodina",
			"Kikinda",
			"Kragujevac",
			"Kraljevo",
			"Kruševac",
			"Leskovac",
			"Loznica",
			"Niš",
			"Novi Pazar",
			"Novi Sad",
			"Pančevo",
			"Pirot",
			"Požarevac",
			"Priština",
			"Prokuplje",
			"Smederevo",
			"Sombor",
			"Sremska Mitrovica",
			"Subotica",
			"Užice",
			"Čačak",
			"Šabac");
	}

	@Override
	public Address findOrCreate(AddressDTO addressDTO) {
		Address address = repository.findByCityAndStreetAndStreetNum(
			addressDTO.getCity(),
			addressDTO.getStreet(),
			addressDTO.getStreetNum());
		return address != null ? address : repository.save(mapper.toEntity(addressDTO));
	}

	@Override
	public void update(long id, AddressDTO addressDTO) {
		Address address = repository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Adresa sa šifrom " + id + " ne postoji"));
		repository.save(address
			.setCity(addressDTO.getCity())
			.setStreet(addressDTO.getStreet())
			.setStreetNum(addressDTO.getStreetNum()));
	}

	@Override
	public AddressDTO toDTO(Address address) {
		return mapper.toDTO(address);
	}
}
