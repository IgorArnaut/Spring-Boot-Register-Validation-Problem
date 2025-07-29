package com.example.realestate.dtos.listings;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class ListingFormDTO {
	@Valid
	private AddressDTO address = AddressDTO.builder().build();
	@Valid
	private BuildingDTO building = BuildingDTO.builder().build();
	@Valid
	private ApartmentDTO apartment = ApartmentDTO.builder().build();
	@NotEmpty(message = "Izaberite bar jedan predmet")
	private List<Long> items = new ArrayList<>();
	@Valid
	private TermsDTO terms = TermsDTO.builder().build();
	@Valid
	private ListingDTO listing = ListingDTO.builder().build();
}
