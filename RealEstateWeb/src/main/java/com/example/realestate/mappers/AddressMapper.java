package com.example.realestate.mappers;

import org.springframework.stereotype.Component;

import com.example.realestate.dtos.listings.AddressDTO;
import com.example.realestate.models.Address;

@Component
public class AddressMapper implements Mapper<Address, AddressDTO> {
	@Override
	public Address toEntity(AddressDTO addressDTO) {
		return Address.builder()
			.city(addressDTO.getCity())
			.street(addressDTO.getStreet())
			.streetNum(addressDTO.getStreetNum())
			.build();
	}

	@Override
	public AddressDTO toDTO(Address address) {
		return AddressDTO.builder()
			.city(address.getCity())
			.street(address.getStreet())
			.streetNum(address.getStreetNum())
			.build();
	}
}
