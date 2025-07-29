package com.example.realestate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.realestate.dtos.listings.ApartmentDTO;
import com.example.realestate.mappers.ApartmentMapper;
import com.example.realestate.models.Apartment;
import com.example.realestate.models.Building;
import com.example.realestate.models.Item;
import com.example.realestate.repositories.ApartmentRepository;
import com.example.realestate.repositories.ListingRepository;
import com.example.realestate.services.ApartmentService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ApartmentServiceImpl implements ApartmentService {
	@Autowired
	private ApartmentRepository apartmentRepository;
	@Autowired
	private ListingRepository listingRepository;

	@Autowired
	private ApartmentMapper mapper;

	@Override
	public Apartment findOrCreate(Building building, List<Item> items, ApartmentDTO apartmentDTO) {
		Apartment apartment = apartmentRepository.find(
			apartmentDTO.getFloor(),
			apartmentDTO.getArea(),
			apartmentDTO.getNumOfRooms(),
			apartmentDTO.getPrice(),
			apartmentDTO.getState(),
			apartmentDTO.getHeating(),
			apartmentDTO.getEquipment(),
			building);

		if (apartment != null)
			return apartment;

		apartment = mapper.toEntity(apartmentDTO);
		apartment.setBuilding(building).getItems().addAll(items);
		return apartmentRepository.save(apartment);
	}

	@Override
	public void update(long id, List<Item> items, ApartmentDTO apartmentDTO) {
		Apartment apartment = apartmentRepository.findById(id)
			.orElseThrow(() -> new EntityNotFoundException("Stan sa šifrom " + id + " ne postoji"));
		apartment.setFloor(apartmentDTO.getFloor())
			.setArea(apartmentDTO.getArea())
			.setNumOfRooms(apartmentDTO.getNumOfRooms())
			.setPrice(apartmentDTO.getPrice())
			.setState(apartmentDTO.getState())
			.setHeating(apartmentDTO.getHeating())
			.setEquipment(apartmentDTO.getEquipment())
			.getItems().clear();
		apartment.getItems().addAll(items);
		apartmentRepository.save(apartment);
	}

	@Override
	public void delete(long id) {
		if (listingRepository.countByApartmentId(id) == 0)
			apartmentRepository.deleteById(id);
	}

	@Override
	public ApartmentDTO toDTO(Apartment apartment) {
		return mapper.toDTO(apartment);
	}
}
