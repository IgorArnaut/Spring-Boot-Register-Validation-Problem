package com.example.realestate.mappers;

import org.springframework.stereotype.Component;

import com.example.realestate.dtos.listings.ApartmentDTO;
import com.example.realestate.models.Apartment;

@Component
public class ApartmentMapper implements Mapper<Apartment, ApartmentDTO> {
	@Override
	public Apartment toEntity(ApartmentDTO apartmentDTO) {
		return Apartment.builder()
			.floor(apartmentDTO.getFloor())
			.area(apartmentDTO.getArea())
			.numOfRooms(apartmentDTO.getNumOfRooms())
			.price(apartmentDTO.getPrice())
			.state(apartmentDTO.getState())
			.heating(apartmentDTO.getHeating())
			.equipment(apartmentDTO.getEquipment())
			.build();
	}

	@Override
	public ApartmentDTO toDTO(Apartment apartment) {
		return ApartmentDTO.builder()
			.floor(apartment.getFloor())
			.area(apartment.getArea())
			.numOfRooms(apartment.getNumOfRooms())
			.price(apartment.getPrice())
			.state(apartment.getState())
			.heating(apartment.getHeating())
			.equipment(apartment.getEquipment())
			.build();
	}
}
