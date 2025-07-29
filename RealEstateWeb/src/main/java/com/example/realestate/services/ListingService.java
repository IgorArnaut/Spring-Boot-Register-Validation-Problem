package com.example.realestate.services;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import com.example.realestate.dtos.listings.ListingDTO;
import com.example.realestate.models.Apartment;
import com.example.realestate.models.Listing;
import com.example.realestate.models.Terms;

public interface ListingService {
	Page<Listing> findAll(int pageNum, int pageSize);

	Listing findById(long id);

	void create(Apartment apartment, Terms terms, ListingDTO listingDTO);

	void update(long id, ListingDTO listingDTO);

	void delete(long id);

	Page<Listing> filter(Specification<Listing> specification, int pageNum, int pageSize);

	ListingDTO toDTO(Listing listing);
}
