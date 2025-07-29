package com.example.realestate.mappers;

import org.springframework.stereotype.Component;

import com.example.realestate.dtos.listings.ListingDTO;
import com.example.realestate.models.Listing;

@Component
public class ListingMapper implements Mapper<Listing, ListingDTO> {
	@Override
	public ListingDTO toDTO(Listing listing) {
		return ListingDTO.builder()
			.title(listing.getTitle())
			.description(listing.getDescription())
			.build();
	}

	@Override
	public Listing toEntity(ListingDTO dto) {
		return null;
	}
}
